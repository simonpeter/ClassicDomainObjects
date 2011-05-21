/*
 * SocialSecurityNumber.java
 */

package org.simonpeter.domain;

import org.simonpeter.test.GuardedInteger;
import org.simonpeter.test.GuardedString;

/**
 * Represent a U.S. Social Security Number.
 *
 * @author  Simon Peter Chappell
 * @version 20100822
 */
public class SocialSecurityNumber implements Comparable<SocialSecurityNumber> {

	private final String _areaNumber;
	private final String _groupNumber;
	private final String _serialNumber;
	
	public SocialSecurityNumber(String area, String group, String serial) {
		new GuardedString(area).mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9][0-9]");
		new GuardedString(group).mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9]");
		new GuardedString(serial).mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9][0-9][0-9]");
		// Should be a numeric integer value, so convert to integers
		GuardedInteger area_gi = new GuardedInteger(area);
		GuardedInteger group_gi = new GuardedInteger(group);
		GuardedInteger serial_gi = new GuardedInteger(serial);
		
		area_gi.mustNotBeZero().mustBeLessThan(773).mustFallOutsideRangeInclusive(734, 749).mustNotEqual(666);
		group_gi.mustNotBeZero();
		serial_gi.mustNotBeZero();
		if (area_gi.is(987) && group_gi.is(65)) {
			serial_gi.mustFallOutsideRangeInclusive(4320, 4329);
		}

		// Make assignments
		_areaNumber   = area;
		_groupNumber  = group;
		_serialNumber = serial;
	}
	
	public String getAreaNumber()   { return _areaNumber;   }
	public String getGroupNumber()  { return _groupNumber;  }
	public String getSerialNumber() { return _serialNumber; }

	/**
	 * Indicates whether another {@link SocialSecurityNumber} is
	 * "equal to" this one.
	 * <p>
	 * A good description of the requirements for object equality
	 * is given in the Java API document entry for {link Object}.
	 * An exhaustive explanation of the qualities of a good
	 * {@code equals()} method is given in
	 * <cite>Effective Java</cite> by Joshua Bloch.
	 * 
	 * @param obj - the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
	 * @see java.util.Object#equals
	 */
	@Override public boolean equals(Object obj) {
		if (obj == this) { return true; }
		if (!(obj instanceof SocialSecurityNumber)) { return false; }
		SocialSecurityNumber ssn = (SocialSecurityNumber)obj;
		return _areaNumber.equals(ssn.getAreaNumber())
		    && _groupNumber.equals(ssn.getGroupNumber())
		    && _serialNumber.equals(ssn.getSerialNumber());
	}

	/**
	 * Returns a hash code value for the {@code SocialSecurityNumber}.
	 * <p>
	 * This method is supported for the benefit of hashtables, such
	 * as those provided by {@link java.util.Hashtable}.
	 * <p>
	 * A good description of the requirements for object hashcodes is
	 * given in the Java API document entry for {link Object}. An
	 * exhaustive explanation of the qualities of a good {@code hashCode()}
	 * method is given in <cite>Effective Java</cite> by Joshua
	 * Bloch.
	 * 
	 * @return a hash code value for this object.
	 * @see java.util.Object
	 * @see java.util.Hashtable
	 */
	@Override public int hashCode() {
		int result = 17;
		result = 37 * result + _areaNumber.hashCode();
		result = 37 * result + _groupNumber.hashCode();
		result = 37 * result + _serialNumber.hashCode();
		return result;
	}

	/**
	 * Returns the string representation of this U.S. Social Security
	 * Number. The string consists of eleven characters whose format
	 * is "AAA-BB-CCCC", where AAA is the area number, BB is the group
	 * number and CCCC is the serial number.
	 * <p>
	 * All elements of the number are validated to have leading zeroes
	 * where necessary at object creation.
	 * 
	 * @return the string representation of this U.S. Social Security Number.
	 */
	@Override public String toString() {
		return _areaNumber+"-"+_groupNumber+"-"+_serialNumber;
	}

	/**
	 * Returns a negative integer, zero or positive integer as this
	 * object is less than, equal to or greater than the specified
	 * {@link SocialSecurityNumber}.
	 * <p>
	 * This method supports sorting of collections.
	 * <p>
	 * A good description of the requirements for the {@link Comparable}
	 * interface is given in <cite>Effective Java</cite> by Joshua
	 * Bloch.
	 */
	public int compareTo(SocialSecurityNumber that) {
		// Compare area codes.
		if (this._areaNumber.compareTo(that.getAreaNumber()) < 0) { return -1; }
		if (this._areaNumber.compareTo(that.getAreaNumber()) > 0) { return 1; }
		
		// Area codes are equal, so compare group numbers.
		if (this._groupNumber.compareTo(that.getGroupNumber()) < 0) { return -1; }
		if (this._groupNumber.compareTo(that.getGroupNumber()) > 0) { return 1; }
		
		// Group numbers are equal, so compare serial numbers.
		if (this._serialNumber.compareTo(that.getSerialNumber()) < 0) { return -1; }
		if (this._serialNumber.compareTo(that.getSerialNumber()) > 0) { return 1; }

		// All fields are equal.
		return 0;
	}
}
