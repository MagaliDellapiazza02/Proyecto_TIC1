package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.FlightPassenger;
import um.edu.uy.business.entities.Passenger;

import java.util.List;

public interface FlightPassengerRepository extends CrudRepository<FlightPassenger, Long> {

    FlightPassenger findFlightPassengerByFlightAndPassenger(Flight flight, Passenger passenger);

    List<FlightPassenger> findByFlight(Flight flight);

    List<FlightPassenger> findFlightPassengerByPassenger(Passenger passenger);
}
