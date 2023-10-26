package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Passenger;

public interface FlightRepository extends CrudRepository<Flight, Long> {



}
