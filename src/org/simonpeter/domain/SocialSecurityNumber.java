package org.simonpeter.domain;

import org.simonpeter.test.GuardCondition;

public class SocialSecurityNumber {

	private final String _areaNumber;
	private final String _groupNumber;
	private final String _serialNumber;
	
	public SocialSecurityNumber(String area, String group, String serial) {
		GuardCondition.mustNotBeNull(area);
		GuardCondition.mustNotBeNull(group);
		GuardCondition.mustNotBeNull(serial);
		GuardCondition.mustNotBeEmptyString(area);
		GuardCondition.mustNotBeEmptyString(group);
		GuardCondition.mustNotBeEmptyString(serial);
		GuardCondition.mustMatchRegex(area,   "[0-9][0-9][0-9]");
		GuardCondition.mustMatchRegex(group,  "[0-9][0-9]");
		GuardCondition.mustMatchRegex(serial, "[0-9][0-9][0-9][0-9]");
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
}
