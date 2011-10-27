package com.tripplanner.domain

import grails.converters.JSON;
import grails.converters.XML

class AirportController {

	def geocoderService

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[airportInstanceList: Airport.list(params), airportInstanceTotal: Airport.count()]
	}

	def create = {
		def airportInstance = new Airport()
		airportInstance.properties = params
		return [airportInstance: airportInstance]
	}

	def save = {
		def results = geocoderService.geocodeAirport(params.iata)
		def airport = new Airport(params + results)
		if(!airport.hasErrors() && airport.save()) {
			flash.message = "Airport ${airport.id} created"
			redirect(action:show,id:airport.id)
		}
		else {
			render(view:'create',model:[airport:airport])
		}
	}

	def show = {
		def airportInstance = Airport.get(params.id)
		if (!airportInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
			redirect(action: "list")
		}
		else {
			[airportInstance: airportInstance]
		}
	}

	def edit = {
		def airportInstance = Airport.get(params.id)
		if (!airportInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [airportInstance: airportInstance]
		}
	}

	def update = {
		def airport = Airport.get( params.id )
		if(airport) {
			def results = geocoderService.geocodeAirport(params.iata)
			airport.properties = params + results
			if(!airport.hasErrors() && airport.save()) {
				flash.message = "Airport ${params.id} updated"
				redirect(action:show,id:airport.id)
			}
			else {
				render(view:'edit',model:[airport:airport])
			}
		}
		else {
			flash.message = "Airport not found with id ${params.id}"
			redirect(action:edit,id:params.id)
		}
	}

	def delete = {
		def airportInstance = Airport.get(params.id)
		if (airportInstance) {
			try {
				airportInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
			redirect(action: "list")
		}
	}

	def getXml = {
		render Airport.findByIata(params.iata) as XML
	}

	def getJson = {
		def airport = Airport.findByIata(params.iata)

		if (!airport) {
			airport = new Airport(iata: params.iata, name: "Not found")
		}

		render airport as JSON
	}

	def geocode = {
		def result = geocoderService.geocodeAirport(params.iata)
		render result as JSON
	}
	
	def map = {
		[airportList: Airport.list()]
	}
}
