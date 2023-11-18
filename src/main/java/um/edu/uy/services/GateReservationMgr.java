package um.edu.uy.services;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.*;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.GateReservationRepository;
import um.edu.uy.persistence.UserRepository;

@Service
public class GateReservationMgr {

    @Autowired
    private GateReservationRepository gateReservationRepository;

    public void addGateReservation(GateReservation gateReservation) throws EntityAlreadyExists {
        if (gateReservationRepository.findGateReservationByDesignatedFlightAndAirportAndDate(gateReservation.getDesignatedFlight(), gateReservation.getAirport(), gateReservation.getDate()) != null) {
            throw new EntityAlreadyExists();
        }
        gateReservationRepository.save(gateReservation);
    }

    public GateReservation findGateReservation(Flight flight, String iata) {
        return gateReservationRepository.findGateReservationByDesignatedFlightAndAirport_IATA(flight, iata);
    }
}