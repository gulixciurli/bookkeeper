package org.apache.bookkeeper.client.impl;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.bookkeeper.client.api.LedgerEntry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.fail;

/**
 * LedgerEntriesImpl Tester. 
 *
 * @author <Authors name> 
 * @since <pre>lug 1, 2021</pre> 
 * @version 1.0
 */

@RunWith(value = Parameterized.class)
public class MyLedgerEntriesImplGetTest {
    private final int entryNumber = 7;
    private LedgerEntriesImpl ledgerEntriesImpl;
    private final List<LedgerEntry> entryList = Lists.newArrayList();

    // contenuto di ogni entry
    private final long ledgerId = 1234L;
    //private final long entryId = 5678L;
    private long entryId;
    private final long length = 9876L;
    private final byte[] dataBytes = "test-ledger-entries-impl".getBytes(UTF_8);
    private final ArrayList<ByteBuf> bufs = Lists.newArrayListWithExpectedSize(entryNumber);
    private boolean expectedResult;


    public MyLedgerEntriesImplGetTest(boolean expectedResult, long entryId) {
        this.expectedResult = expectedResult;
        this.entryId = entryId;

    }

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < entryNumber; i++) {
            ByteBuf buf = Unpooled.wrappedBuffer(dataBytes);
            bufs.add(buf);

            entryList.add(LedgerEntryImpl.create(ledgerId + i,
                    i,
                    length + i,
                    buf));
        }

        ledgerEntriesImpl = LedgerEntriesImpl.create(entryList);
    }

    @After
    public void tearDown() throws Exception {
        ledgerEntriesImpl.close();
        try {
            ledgerEntriesImpl.getEntry(entryId);
            fail("should fail getEntry after close");
        } catch (NullPointerException e) {
            // expected behavior
        }

        try {
            ledgerEntriesImpl.iterator();
            fail("should fail iterator after close");
        } catch (NullPointerException e) {
            // expected behavior
        }
    }

    @Parameterized.Parameters
    public static Collection<?> getTestParameters() {
        return Arrays.asList(new Object[][]{

                {true, 0}, //true perche' entry id = 0 esiste (infatti nel setup l'indice inizia da i = 0)
                {true, 2}, //true perche' entry id = 2 esiste (infatti nel setup l'indice arriva a  i = 6)
                {false, -1},
                {false, 7},


        });

    }

    /**
     *
     * Method: getEntry(long entryId)
     *
     */
    @Test
    public void testGetEntry() throws Exception {
//TODO: Test goes here...
        try {
            LedgerEntry entry = ledgerEntriesImpl.getEntry(entryId);
            if (ledgerId < entryNumber || ledgerId > 0) {
                Assert.assertEquals(entry, entryList.get((int) entryId));
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            if (entryId == -1) {
                Assert.assertEquals(e.getMessage(), "required index: -1 is out of bounds: [ 0, 6 ].");
            }
            else {
                Assert.assertEquals(e.getMessage(), "required index: 7 is out of bounds: [ 0, 6 ].");
            }
        }
       // Assert.assertEquals();
    }






    /**
     *
     * Method: create(List<LedgerEntry> entries) 
     *
     */
  //  @Test
  //  public void testCreate() throws Exception {
//TODO: Test goes here... 
    }



    /**
     *
     * Method: iterator() 
     *
     */
  //  @Test
   // public void testIterator() throws Exception {
//TODO: Test goes here... 
   // }

    /**
     *
     * Method: close() 
     *
     */
   // @Test
    //public void testClose() throws Exception {
//TODO: Test goes here... 
   // }


    /**
     *
     * Method: recycle() 
     *
     */
   // @Test
   // public void testRecycle() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LedgerEntriesImpl.getClass().getMethod("recycle"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
  //  }

    /**
     *
     * Method: releaseByteBuf() 
     *
     */
    //@Test
    //public void testReleaseByteBuf() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LedgerEntriesImpl.getClass().getMethod("releaseByteBuf"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
 //   }

//}

