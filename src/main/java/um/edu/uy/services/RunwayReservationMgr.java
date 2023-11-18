package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.GateReservation;
import um.edu.uy.business.entities.RunwayReservation;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.RunwayReservationRepository;

@Service
public class RunwayReservationMgr {

    @Autowired
    private RunwayReservationRepository runwayReservationRepository;

    public void addRunwayReservation(RunwayReservation runwayReservation) throws EntityAlreadyExists {
        if (runwayReservationRepository.findRunwayReservationByDesignatedFlightAndAirportAndDate(runwayReservation.getDesignatedFlight(), runwayReservation.getAirport(), runwayReservation.getDate()) != null) {
            throw new EntityAlreadyExists();
        }
        runwayReservationRepository.save(runwayReservation);
    }

    public RunwayReservation findRunwayReservation(Flight flight, String iata) {
        return runwayReservationRepository.findRunwayReservationByDesignatedFlightAndAirport_IATA(flight, iata);
    }
}