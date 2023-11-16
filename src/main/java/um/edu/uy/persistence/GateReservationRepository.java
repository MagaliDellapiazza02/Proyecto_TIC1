package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.GateReservation;

import java.util.List;

public interface GateReservationRepository extends CrudRepository<GateReservation, Long> {

    List<GateReservation> findByAirport_IATAAndFlightConfirmedFalse(String IATA);

    GateReservation findGateReservationByDesignatedFlightAndAirport_IATA(Flight flight, String IATA);

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */

}
