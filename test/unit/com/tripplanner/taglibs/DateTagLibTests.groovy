package com.tripplanner.taglibs

import grails.test.*

class DateTagLibTests extends TagLibUnitTestCase {
	
	def dateTagLib

	protected void setUp() {
		dateTagLib = new DateTagLib()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testThisYear() {
		String expected = Calendar.getInstance().getFieldName(Calendar.YEAR)
		assertEquals("the years don't match", expected, dateTagLib.thisYear())
    }
}
