package org.apache.bookkeeper.bookie.storage.ldb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.Assert;

public class provaReadCache {
    public static void main(String[] args) {
        ReadCache cache = new ReadCache(UnpooledByteBufAllocator.DEFAULT, 10 * 1024);
        ByteBuf entry = Unpooled.wrappedBuffer(new byte[1024]);
        cache.put(1,0, entry);
        System.out.println(cache.get(1,1));
        System.out.println(cache.get(1,0));
        System.out.println(cache.get(0,0).equals(entry));
        System.out.println(cache.get(0,1).equals(entry));
        /*
        try {
            cache.put(-1, 1, entry);
        }catch(IllegalArgumentException e) {
            System.out.println("provaaaaaaa");
            e.printStackTrace();

        }*/
        try {
            Assert.assertEquals(cache.get(1,1), entry);
        } catch (NullPointerException e) {
            System.out.println("noooo");
            e.printStackTrace();
        }
    }
}
