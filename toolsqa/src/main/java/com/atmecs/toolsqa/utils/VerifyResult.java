package com.atmecs.toolsqa.utils;

import org.testng.Assert;
import org.testng.Reporter;

public class VerifyResult {


	public boolean verifyBoolean(boolean actual, boolean expected, String message) {
		try {
			Assert.assertEquals(actual, expected);
			//report.info("PASS : " + message + ": " + "ACTUAL : " + actual + " 	" + "EXPECTED :" + expected);
			System.out.println("PASS : " + message + ": " + "ACTUAL : " + actual + " 	" + "EXPECTED :" + expected);
			Reporter.log("PASS : " + message + ": " + "ACTUAL : " + actual + " 	" + "EXPECTED :" + expected);
			return true;
		} catch (AssertionError assertionError) {
			return false;
		}
	}

	public boolean verifyTrue(boolean condition, String message) {
		boolean result = true;
		try {
			Assert.assertTrue(condition);
			//report.info("PASS : " + message);
			System.out.println("PASS : " + message);
			Reporter.log("PASS : " + message);
			result = true;
		} catch (AssertionError assertionError) {

			result = false;
		}
		return result;

	}


}
