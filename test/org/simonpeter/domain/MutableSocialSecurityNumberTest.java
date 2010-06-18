package org.simonpeter.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class MutableSocialSecurityNumberTest {

	//@SuppressWarnings("unused")
	private SocialSecurityNumber ssn;
	
	@Test
	public void testThreeArgConstructionOfValidSSN() {
		ssn = new SocialSecurityNumber("123", "45", "6789");
	}
	
	@Test
	public void testAccessors() {
		ssn = new SocialSecurityNumber("123", "45", "6789");
		assertEquals("123",  ssn.getAreaNumber());
		assertEquals("45",   ssn.getGroupNumber());
		assertEquals("6789", ssn.getSerialNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThatAllNullStringsAreRejected() {
		ssn = new SocialSecurityNumber(null, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThatAllEmptyStringsAreRejected() {
		ssn = new SocialSecurityNumber("", "", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThatNonNumericsAreRejected() {
		ssn = new SocialSecurityNumber("ABC", "DE", "FGHI");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThatAreaNumberIsInValidRange1() {
		// Good numbers at either end of the first range
		ssn = new SocialSecurityNumber("733", "45", "6789");
		ssn = new SocialSecurityNumber("750", "45", "6789");
		// Bad number
		ssn = new SocialSecurityNumber("734", "45", "6789");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThatAreaNumberIsInValidRange2() {
		// Good numbers at start of the final range
		ssn = new SocialSecurityNumber("772", "45", "6789");
		// Bad number
		ssn = new SocialSecurityNumber("773", "45", "6789");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThatAllZeroStringsAreRejected() {
		ssn = new SocialSecurityNumber("000", "00", "0000");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThatAreaNumber666IsRejected() {
		ssn = new SocialSecurityNumber("666", "45", "6789");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThatAdvertisingNumbersAreRejected() {
		// Good numbers at either end of the range
		ssn = new SocialSecurityNumber("987", "65", "4319");
		ssn = new SocialSecurityNumber("987", "65", "4330");
		// First advertising number
		ssn = new SocialSecurityNumber("987", "65", "4320");
	}
}
