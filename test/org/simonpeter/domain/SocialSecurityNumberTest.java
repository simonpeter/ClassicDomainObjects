/*
 * SocialSecurityNumberTest.java
 */

package org.simonpeter.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 * Test the {@link SocialSecurityNumber} domain object.
 *
 * @author  Simon Peter Chappell
 * @version 20100822
 * @see org.simonpeter.domain.SocialSecurityNumber
 */
public class SocialSecurityNumberTest {

	private SocialSecurityNumber ssn;

	@Test public void testThreeArgConstructionOfValidSSN() {
		ssn = new SocialSecurityNumber("123", "45", "6789");
	}

	@Test public void testAccessors() {
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

	@Test public void testReflexiveBehaviorOfEquals() {
		// An object is always equal to itself.
		ssn = new SocialSecurityNumber("123", "45", "6789");
		assertEquals(ssn, ssn);
		assertTrue(ssn.equals(ssn));
	}

	@Test public void testNullBehaviorOfEquals() {
		// An object is never equal to null.
		ssn = new SocialSecurityNumber("123", "45", "6789");
		assertFalse(ssn.equals(null));
	}

	@Test public void testSymmetricalBehaviorOfEquals() {
		// If o1 is equal to o2, then o2 must be equal to o1.
		SocialSecurityNumber s1 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber s2 = new SocialSecurityNumber("123", "45", "6789");
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
	}

	@Test public void testTransitiveBehaviorOfEquals() {
		// If o1 is equal to o2 and o2 is equal to o3,
		// then o3 must be equal to o1.
		SocialSecurityNumber s1 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber s2 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber s3 = new SocialSecurityNumber("123", "45", "6789");
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s3));
		assertTrue(s3.equals(s1));
	}

	@Test public void testUnequalValuesBehaviorOfEquals() {
		// If o1 does not equal o2, then o2 must not equal o1.
		SocialSecurityNumber s1 = new SocialSecurityNumber("111", "11", "1111");
		SocialSecurityNumber s2 = new SocialSecurityNumber("222", "22", "2222");
		assertFalse(s1.equals(s2));
		assertFalse(s2.equals(s1));
	}

	@Test public void testEqualObjectsHaveEqualHashCodes() {
		// Objects that are equal must have equal hashcodes.
		SocialSecurityNumber s1 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber s2 = new SocialSecurityNumber("123", "45", "6789");
		assertEquals(s1.hashCode(), s2.hashCode());
	}

	@Test public void testSameObjectAlwaysSameHashCode() {
		// An unchanged object should always have the same hashcode.
		SocialSecurityNumber ssn = new SocialSecurityNumber("123", "45", "6789");
		int h1 = ssn.hashCode();
		int h2 = ssn.hashCode();
		assertEquals(h1, h2);
	}

	@Test public void testToString() {
		SocialSecurityNumber ssn = new SocialSecurityNumber("123", "45", "6789");
		assertEquals("123-45-6789", ssn.toString());
	}

	@Test public void testCompareTo() {
		SocialSecurityNumber ssn1 = new SocialSecurityNumber("111", "22", "3333");
		SocialSecurityNumber ssn2 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber ssn3 = new SocialSecurityNumber("123", "45", "6789");
		SocialSecurityNumber ssn4 = new SocialSecurityNumber("444", "55", "6666");
		assertEquals(-1, ssn1.compareTo(ssn4));
		assertEquals(0, ssn2.compareTo(ssn3));
		assertEquals(0, ssn3.compareTo(ssn2));
		assertEquals(1, ssn4.compareTo(ssn1));
	}
}
