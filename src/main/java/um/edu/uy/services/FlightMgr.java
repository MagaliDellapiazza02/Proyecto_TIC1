package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.PassengerRepository;

@Service
public class FlightMgr {
    @Autowired
    private FlightRepository flightRepository;

}
