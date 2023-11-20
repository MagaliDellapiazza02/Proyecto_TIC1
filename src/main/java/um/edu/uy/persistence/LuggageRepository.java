package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.Passenger;

import java.util.List;

import java.util.List;
@Component
public interface LuggageRepository extends CrudRepository<Luggage, String> {

    Luggage findByTrackingCode (String trackingCode);

    Luggage findByFlightAndPassenger (Flight flight, Passenger passenger);

    List<Luggage> findAllByFlight(Flight flight);

    List<Luggage> findAllByPassenger(Passenger passenger);

    List<Luggage> findByPassengerId(long id);

}

