import com.tripplanner.domain.Airline;

class BootStrap {

    def init = { servletContext ->
		def airline = new Airline(name: "XYZ Airways", url: "http://www.xyzairways.com", notes: "Charges for check-in bags", frequentFlyer: "123456")
		airline.save()
    }
    def destroy = {
    }
}
