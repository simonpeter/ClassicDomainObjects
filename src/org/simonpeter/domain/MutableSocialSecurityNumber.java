package org.simonpeter.domain;

import org.simonpeter.test.GuardCondition;

public class MutableSocialSecurityNumber {

	private String _areaNumber;
	private String _groupNumber;
	private String _serialNumber;

	public MutableSocialSecurityNumber() {
		_areaNumber   = "999";
		_groupNumber  = "99";
		_serialNumber = "9999";
	}

	public void   setAreaNumber(String area) {
		GuardCondition.mustNotBeNull(area);
		GuardCondition.mustNotBeEmptyString(area);
		GuardCondition.mustMatchRegex(area,   "[0-9][0-9][0-9]");
		int areaNumber = Integer.parseInt(area);
		GuardCondition.mustFallOutsideRangeInclusive(areaNumber, 734, 749);
		GuardCondition.mustBeLessThan(areaNumber, 773);
		GuardCondition.mustNotBeZero(areaNumber);
		GuardCondition.mustNotBeEqual(areaNumber, 666);
		_areaNumber = area;
	}

	public void   setGroupNumber(String group) {
		GuardCondition.mustNotBeNull(group);
		GuardCondition.mustNotBeEmptyString(group);
		GuardCondition.mustMatchRegex(group,  "[0-9][0-9]");
		int groupNumber = Integer.parseInt(group);
		GuardCondition.mustNotBeZero(groupNumber);
		_groupNumber = group;
	}

	public void   setSerialNumber(String serial) {
		GuardCondition.mustNotBeNull(serial);
		GuardCondition.mustNotBeEmptyString(serial);
		GuardCondition.mustMatchRegex(serial, "[0-9][0-9][0-9][0-9]");
		int serialNumber = Integer.parseInt(serial);
		GuardCondition.mustNotBeZero(serialNumber);
		if (_areaNumber.equals("987") && _groupNumber.equals("65")) {
			GuardCondition.mustFallOutsideRangeInclusive(serialNumber, 4320, 4329);
		}
		_serialNumber = serial;
	}

	public String getAreaNumber()   { return _areaNumber;   }
	public String getGroupNumber()  { return _groupNumber;  }
	public String getSerialNumber() { return _serialNumber; }
}
