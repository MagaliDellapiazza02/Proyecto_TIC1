package um.edu.uy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.FlightMgr;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping(value = "/flights")
public class AirportRestController {

    @Autowired
    private AirportMgr airportMgr;

    @PostMapping("/add/{airport}")
    public void addAirport(@PathVariable Airport airport) throws EntityAlreadyExists, InvalidInformation {
        airportMgr.addAirport(airport);
    }

    @GetMapping("/getByName/{name}")
    public Airport getAirportByName(@PathVariable String name) {
        return airportMgr.findByName(name);
    }
}