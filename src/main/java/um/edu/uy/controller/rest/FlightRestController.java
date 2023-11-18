package um.edu.uy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.services.FlightMgr;

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
}
