package com.tripplanner.domain

class TripController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		if(!params.max) params.max = 10
		[ tripList: Trip.list( params ) ]
	}

	def create = {
		def tripInstance = new Trip()
		tripInstance.properties = params
		return [tripInstance: tripInstance]
	}

	def save = {
		def trip = new Trip(params)
		if(!trip.hasErrors() && trip.save()) {
			flash.message = "Trip ${trip.id} created"
			redirect(action:show,id:trip.id)
		}
		else {
			render(view:'create',model:[trip:trip])
		}
	}

	def show = {
		def tripInstance = Trip.get(params.id)
		if (!tripInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
			redirect(action: "list")
		}
		else {
			[tripInstance: tripInstance]
		}
	}

	def edit = {
		def tripInstance = Trip.get(params.id)
		if (!tripInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [tripInstance: tripInstance]
		}
	}

	def update = {
		def tripInstance = Trip.get(params.id)
		if (tripInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (tripInstance.version > version) {

					tripInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'trip.label', default: 'Trip')]
					as Object[], "Another user has updated this Trip while you were editing")
					render(view: "edit", model: [tripInstance: tripInstance])
					return
				}
			}
			tripInstance.properties = params
			if (!tripInstance.hasErrors() && tripInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'trip.label', default: 'Trip'), tripInstance.id])}"
				redirect(action: "show", id: tripInstance.id)
			}
			else {
				render(view: "edit", model: [tripInstance: tripInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def tripInstance = Trip.get(params.id)
		if (tripInstance) {
			try {
				tripInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
			redirect(action: "list")
		}
	}
}
