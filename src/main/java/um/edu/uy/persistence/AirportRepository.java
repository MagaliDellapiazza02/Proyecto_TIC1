package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.User;

public interface AirportRepository extends CrudRepository<Airport, Long> {

    Airport findByName(String name);

}
