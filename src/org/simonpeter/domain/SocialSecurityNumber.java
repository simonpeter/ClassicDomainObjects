/*
 * SocialSecurityNumber.java
 */

package org.simonpeter.domain;

import org.simonpeter.test.GuardCondition;
import org.simonpeter.test.GuardedString;

/**
 * Represent a U.S. Social Security Number.
 *
 * @author  Simon Peter Chappell
 * @version 20100623
 */
public class SocialSecurityNumber {

	private final String _areaNumber;
	private final String _groupNumber;
	private final String _serialNumber;
	
	public SocialSecurityNumber(String area, String group, String serial) {
		GuardedString area_g = new GuardedString(area);
		GuardedString group_g = new GuardedString(group);
		GuardedString serial_g = new GuardedString(serial);

		area_g.mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9][0-9]");
		group_g.mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9]");
		serial_g.mustNotBeNullOrBlank().mustMatchRegex("[0-9][0-9][0-9][0-9]");

		// Should be a numeric integer value, so convert to integers
		int areaNumber   = Integer.parseInt(area);
		int groupNumber  = Integer.parseInt(group);
		int serialNumber = Integer.parseInt(serial);
		GuardCondition.mustFallOutsideRangeInclusive(areaNumber, 734, 749);
		GuardCondition.mustBeLessThan(areaNumber, 773);
		GuardCondition.mustNotBeZero(areaNumber);
		GuardCondition.mustNotBeZero(groupNumber);
		GuardCondition.mustNotBeZero(serialNumber);
		GuardCondition.mustNotBeEqual(areaNumber, 666);
		if (areaNumber == 987 && groupNumber == 65) {
			GuardCondition.mustFallOutsideRangeInclusive(serialNumber, 4320, 4329);
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
	 * @see java.util.Object
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
}
