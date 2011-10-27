package com.tripplanner.domain

class Trip {
	
	static constraints = {
		airline()
		name()
		city()
		startDate()
		endDate()
		purpose()
		notes()
	}
	
	String name
	String city
	Date startDate
	Date endDate
	String purpose
	String notes
	
	Airline airline
}
