package um.edu.uy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.services.FlightMgr;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping(value = "/flights")
public class FlightRestController {

    @Autowired
    private FlightMgr flightMgr;

    @GetMapping("/originairport/{originApproved}/{originAirportIATA}/{flightState}")
    public List<Flight> getFlightsByOriginAirport(@PathVariable boolean originApproved, @PathVariable String originAirportIATA, @PathVariable String flightState) {
        return flightMgr.getFlightsFromDepartureAirport(originApproved, originAirportIATA, flightState);
    }

    @GetMapping("/destinyairport/{destinyApproved}/{destinyAirportIATA}/{flightState}")
    public List<Flight> getFlightsByDestinyAirport(@PathVariable boolean destinyApproved, @PathVariable String destinyAirportIATA, @PathVariable String flightState) {
        return flightMgr.getFlightsFromArrivalAirport(destinyApproved, destinyAirportIATA, flightState);
    }

    @PostMapping("/add/{flight}")
    public void addFlight(@PathVariable Flight flight) throws EntityAlreadyExists, InvalidInformation {
        flightMgr.addFlight(flight);
    }

    @GetMapping("/validateOnOrigin/{flight}/{originAirport}/{date}")
    public boolean validateOnOrigin(@PathVariable Flight flight, @PathVariable Airport originAirport, @PathVariable Date date) {
        return flightMgr.validateFlightOnOrigin(originAirport, date, flight);
    }

    @GetMapping("/validateOnDestiny/{flight}/{destinyAirport}/{date}")
    public boolean validateOnDestiny(@PathVariable Flight flight, @PathVariable Airport destinyAirport, @PathVariable Date date) {
        return flightMgr.validateFlightOnDestiny(destinyAirport, date, flight);
    }
}
