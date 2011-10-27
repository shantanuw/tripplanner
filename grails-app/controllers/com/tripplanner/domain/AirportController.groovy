package com.tripplanner.domain

import grails.converters.JSON;
import grails.converters.XML

class AirportController {
	
	def scaffold = Airport

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
        def airportInstance = new Airport(params)
        if (airportInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'airport.label', default: 'Airport'), airportInstance.id])}"
            redirect(action: "show", id: airportInstance.id)
        }
        else {
            render(view: "create", model: [airportInstance: airportInstance])
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
        def airportInstance = Airport.get(params.id)
        if (airportInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (airportInstance.version > version) {
                    
                    airportInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'airport.label', default: 'Airport')] as Object[], "Another user has updated this Airport while you were editing")
                    render(view: "edit", model: [airportInstance: airportInstance])
                    return
                }
            }
            airportInstance.properties = params
            if (!airportInstance.hasErrors() && airportInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'airport.label', default: 'Airport'), airportInstance.id])}"
                redirect(action: "show", id: airportInstance.id)
            }
            else {
                render(view: "edit", model: [airportInstance: airportInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airport.label', default: 'Airport'), params.id])}"
            redirect(action: "list")
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
}
