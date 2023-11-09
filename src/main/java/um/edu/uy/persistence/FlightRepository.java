package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Flight;

import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, String> {

        /**
        * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
        * @param code
        * @return
        */
        Flight findOneByFlightNumber(String code);

        List<Flight> findByflightState(String flightState);

        List<Flight> findByDestinyApprovedAndDestinyAirportIATAAndFlightState(boolean destinyApproved, String destinyAirportIATA, String flightState);

        List<Flight> findByOriginApprovedAndOriginAirportIATAAndFlightState(boolean originApproved, String originAirportIATA, String flightState);

}
