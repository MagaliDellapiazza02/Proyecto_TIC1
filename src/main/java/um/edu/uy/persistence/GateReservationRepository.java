package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.GateReservation;

import java.util.Date;
import java.util.List;
@Component
public interface GateReservationRepository extends CrudRepository<GateReservation, Long> {

    List<GateReservation> findByAirport_IATAAndFlightConfirmedFalse(String IATA);

    GateReservation findGateReservationByDesignatedFlightAndAirport_IATA(Flight flight, String IATA);

    GateReservation findGateReservationByDesignatedFlightAndAirportAndDate(Flight designatedFlight, Airport airport, Date date);

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */

}
