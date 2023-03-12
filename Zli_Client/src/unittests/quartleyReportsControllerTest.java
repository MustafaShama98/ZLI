package unittests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controllers.users.CeoController.SingleQuartleyReportsController;
import entities.MyFile;

class quartleyReportsControllerTest {

	@Before
	void setUp() throws Exception {
	}

	
	
	/**
	 * Test funcionalty: Test when reciving non empty file, does the method passes it succesfully
	 * input: msgFile=MyFile(2-2015-EAST.txt) with length 7 bytes
	 * Expected result: return true.
	 * 
	 */
	@Test
	void testSuccessefullSendingData() throws Exception{

		MyFile msgFile = new MyFile("2-2015-EAST.txt");
		Random rd = new Random();
		byte[] arr = new byte[7];
	    rd.nextBytes(arr);
	    msgFile.initArray(arr.length);
	    msgFile.setMybytearray(arr);
		assertTrue(SingleQuartleyReportsController.paraseFileBytes(msgFile));
	}
	
	/**
	 * Test funcionalty: Test when reciving  empty file, does the method return false
	 * input: msgFile=MyFile(2-2015-EAST.txt) with length 0 bytes
	 * Expected result: return true.
	 * 
	 */
	@Test
	void testUnsuccessfullSendingEmptyData() throws Exception{
		MyFile msgFile = new MyFile("2-2015-EAST.txt");
		Random rd = new Random();
		byte[] arr = new byte[0];
	    rd.nextBytes(arr);
	    msgFile.initArray(arr.length);
	    msgFile.setMybytearray(arr);
		Assert.assertTrue(SingleQuartleyReportsController.paraseFileBytes(msgFile));
	}
	
	
	
}
