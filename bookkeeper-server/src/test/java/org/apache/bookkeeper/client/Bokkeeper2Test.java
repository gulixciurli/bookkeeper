/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.bookkeeper.client;

import static org.apache.bookkeeper.client.BookKeeperClientStats.WRITE_DELAYED_DUE_TO_NOT_ENOUGH_FAULT_DOMAINS;
import static org.apache.bookkeeper.client.BookKeeperClientStats.WRITE_TIMED_OUT_DUE_TO_NOT_ENOUGH_FAULT_DOMAINS;
import static org.apache.bookkeeper.common.concurrent.FutureUtils.result;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import io.netty.util.IllegalReferenceCountException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.bookkeeper.client.AsyncCallback.AddCallback;
import org.apache.bookkeeper.client.AsyncCallback.ReadCallback;
import org.apache.bookkeeper.client.BKException.BKBookieHandleNotAvailableException;
import org.apache.bookkeeper.client.BKException.BKIllegalOpException;
import org.apache.bookkeeper.client.BookKeeper.DigestType;
import org.apache.bookkeeper.client.api.WriteFlag;
import org.apache.bookkeeper.client.api.WriteHandle;
import org.apache.bookkeeper.conf.ClientConfiguration;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.apache.bookkeeper.proto.BookieServer;
import org.apache.bookkeeper.stats.NullStatsLogger;
import org.apache.bookkeeper.stats.StatsLogger;
import org.apache.bookkeeper.test.BookKeeperClusterTestCase;
import org.apache.bookkeeper.test.TestStatsProvider;
import org.apache.bookkeeper.zookeeper.BoundExponentialBackoffRetryPolicy;
import org.apache.bookkeeper.zookeeper.ZooKeeperClient;
import org.apache.bookkeeper.zookeeper.ZooKeeperWatcherBase;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.ConnectionLossException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests of the main BookKeeper client.
 */
public class Bokkeeper2Test extends BookKeeperClusterTestCase {
    private static final Logger LOG = LoggerFactory.getLogger(BookKeeperTest.class);
    private static final long INVALID_LEDGERID = -1L;
    private final DigestType digestType;

    public Bokkeeper2Test() {
        super(4);
        this.digestType = DigestType.CRC32;
    }

    private LedgerHandle createLedgerWithEntries(BookKeeper bk, int numOfEntries)
            throws Exception {
        LedgerHandle lh = bk
                .createLedger(3, 3, digestType, "password".getBytes());

        final AtomicInteger rc = new AtomicInteger(BKException.Code.OK);
        final CountDownLatch latch = new CountDownLatch(numOfEntries);

        final AddCallback cb = new AddCallback() {
            public void addComplete(int rccb, LedgerHandle lh, long entryId,
                                    Object ctx) {
                rc.compareAndSet(BKException.Code.OK, rccb);
                latch.countDown();
            }
        };
        for (int i = 0; i < numOfEntries; i++) {
            lh.asyncAddEntry("foobar".getBytes(), cb, null);
        }
        if (!latch.await(30, TimeUnit.SECONDS)) {
            throw new Exception("Entries took too long to add");
        }
        if (rc.get() != BKException.Code.OK) {
            throw BKException.create(rc.get());
        }
        return lh;
    }

    /**
     * Test that deleting a ledger using bookkeeper client which is closed
     * should throw ClientClosedException.
     */
    @Test
    public void testDeleteLedger() throws Exception {
        BookKeeper bk = new BookKeeper(baseClientConf, zkc);
        LOG.info("Create ledger and add entries to it");
        LedgerHandle lh = createLedgerWithEntries(bk, 100);
        //LedgerHandle lh = bkc.createLedger(DigestType.CRC32, "password".getBytes()); //si crea un LedgerHandle con un ID = 0

        LOG.info("Closing bookkeeper client");
        bk.close();
        try {
            bk.deleteLedger(lh.getId());
            fail("should have failed, client is closed");
        } catch (BKException.BKClientClosedException e) {
            // correct
        }

        // using async, because this could trigger an assertion
        final AtomicInteger returnCode = new AtomicInteger(0);
        final CountDownLatch openLatch = new CountDownLatch(1);

        AsyncCallback.DeleteCallback cb = new AsyncCallback.DeleteCallback(){
            public void deleteComplete(int rc, Object ctx) {
                returnCode.set(rc);
                openLatch.countDown();
            }
        };
        System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        bk.asyncDeleteLedger(50, cb, null);

        LOG.info("Waiting to delete the ledger asynchronously");
        assertTrue("Delete call should have completed",
                openLatch.await(20, TimeUnit.SECONDS));
        assertEquals("Delete should not have succeeded through closed bkclient!",
                BKException.Code.ClientClosedException, returnCode.get());
    }




}
