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
public class MyLedgerEntriesImplCreateTest {
    private final int entryNumber = 1;
    private LedgerEntriesImpl ledgerEntriesImpl;
    private List<LedgerEntry> entryList = Lists.newArrayList();

    // contenuto di ogni entry
    //private final long ledgerId = 1234L;
    //private final long entryId = 5678L;
    //private long entryId;
    private final long length = 9876L;
    private final byte[] dataBytes = "test-ledger-entries-impl".getBytes(UTF_8);
    private final ArrayList<ByteBuf> bufs = Lists.newArrayListWithExpectedSize(entryNumber);
    private boolean expectedResult;


    public MyLedgerEntriesImplCreateTest(boolean expectedResult, List<LedgerEntry> entryList) {
        this.expectedResult = expectedResult;
        this.entryList = entryList;

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        
    }

    public static List<LedgerEntry> createEntryList(long ledgerId, long entryId) {
        List<LedgerEntry> list = Lists.newArrayList();
        list.add(LedgerEntryImpl.create(ledgerId, entryId));
        return list;
    }

    @Parameterized.Parameters
    public static Collection<?> getTestParameters() {
        return Arrays.asList(new Object[][]{

                {false, null},
                {false, Lists.newArrayList()},
                {true, createEntryList(0,0)}

        });

    }

    /**
     *
     * Method: create(List<LedgerEntry> entries)
     *
     */
    @Test
    public void testGetEntry() throws Exception {
        System.out.println("LISTA ===" + entryList);
        boolean result;
        try {
            LedgerEntriesImpl entry = LedgerEntriesImpl.create(entryList);
            result = entryList.get(0).equals(entry.getEntry(0));
            //Assert.assertEquals(entryList.get(0), entry.getEntry(0));

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        Assert.assertEquals(expectedResult, result);
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

