package org.apache.bookkeeper.bookie.storage.ldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class myReadCacheGetTest {

    private boolean exists;
    private ReadCache cache = null;
    private ByteBuf entry;
    private long ledgerId;
    private long entryId;
    private boolean expected;


    @Parameterized.Parameters
    public static Collection<?> getParameter() {
        return Arrays.asList(new Object[][] {
                //grandezza buffer per numero entry
                //il terzo valore indica se il valore esiste nel momento della get oppure non esiste
                { 0, -1, true, Unpooled.wrappedBuffer(new byte[64]), true},
                { 0, 1, true, Unpooled.wrappedBuffer(new byte[64]), true},
                { 1, 0, false, Unpooled.wrappedBuffer(new byte[64]), false}
        });
    }

    @After
    public void after(){
        cache.close();
    }

    @Before
    public void before(){
        this.cache = new ReadCache(UnpooledByteBufAllocator.DEFAULT, 10 * 1024);
        this.cache.put(this.ledgerId, this.entryId, this.entry);
    }

    public myReadCacheGetTest(long ledgerId, long entryId, boolean exists, ByteBuf entry, boolean expected){
        this.ledgerId = ledgerId;
        this.entryId = entryId;
        this.entry = entry;
        this.exists = exists;
        this.expected = expected;
    }



    @Test
    public void get(){
        //test per aumentare la coverage, testo qualcosa che non esiste
        boolean result;
        try{
            if(this.exists == false)
                result = this.cache.get(0, 0).equals(this.entry);
            else {
                result = this.cache.get(this.ledgerId, this.entryId).equals(this.entry);
                System.out.println(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        assertEquals(this.expected, result);
    }

}