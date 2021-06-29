package org.apache.bookkeeper.bookie.storage.ldb;

import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.apache.bookkeeper.bookie.storage.ldb.ReadCache;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.UnknownHostException;
import java.util.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class myReadCachePutTest {

    private ReadCache cache = null;
    private ByteBuf entry;
    private long ledgerId;
    private long entryId;
    private boolean expected;


    @Parameterized.Parameters
    public static Collection<?> getParameter() {

        return Arrays.asList(new Object[][] {
                //grandezza buffer per numero entry
                { -1, -1, Unpooled.wrappedBuffer(new byte[64]), false},
                { 0, 1, Unpooled.wrappedBuffer(new byte[64]), true},
                { 0, 0, Unpooled.wrappedBuffer(new byte[64]), true}
        });
    }

    @After
    public void after(){
        cache.close();
    }

    @Before
    public void before(){
        this.cache = new ReadCache(UnpooledByteBufAllocator.DEFAULT, 10 * 1024);
    }

    public myReadCachePutTest(long ledgerId, long entryId, ByteBuf entry, boolean expected){
        this.ledgerId = ledgerId;
        this.entryId = entryId;
        this.entry = entry;
        this.expected = expected;
    }



    @Test
    public void put(){
        boolean result;
        try {
            this.cache.put(this.ledgerId, this.entryId, this.entry);
//            assertEquals(1, this.cache.count());
            result = cache.get(this.ledgerId, this.entryId).equals(this.entry);

        }
        catch(Exception e) {
            e.printStackTrace();
            result = false;
        }
        Assert.assertEquals(this.expected, result);
    }

}