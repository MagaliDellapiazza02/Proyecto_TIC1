package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.RunwayReservation;

import java.util.Date;
@Component
public interface RunwayReservationRepository extends CrudRepository<RunwayReservation, Long> {
    RunwayReservation findRunwayReservationByDesignatedFlightAndAirport_IATA(Flight flight, String iata);

    Iterable<RunwayReservation> findByAirport_IATAAndFlightConfirmedFalse(String IATA);

    RunwayReservationRepository findRunwayReservationByDesignatedFlightAndAirportAndDate(Flight designatedFlight, Airport airport, Date date);
}