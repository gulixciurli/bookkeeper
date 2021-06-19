package org.apache.bookkeeper.client;

import com.google.common.net.InetAddresses;
import org.apache.bookkeeper.conf.ClientConfiguration;
import org.apache.bookkeeper.conf.ServerConfiguration;
import org.apache.bookkeeper.meta.zk.ZKMetadataDriverBase;
import org.apache.bookkeeper.util.BookKeeperConstants;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.bookkeeper.util.BookKeeperConstants.AVAILABLE_NODE;
import static org.apache.bookkeeper.util.BookKeeperConstants.READONLY;

public class BookkeeperAdminTest2 {

    private BookKeeperClusterEntity cluster;
    private ZooKeeperCluster zk;
    private boolean expectedResult;
}
