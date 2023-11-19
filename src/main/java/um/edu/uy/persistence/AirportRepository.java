package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airport;

import java.util.List;

public interface AirportRepository extends CrudRepository<Airport, Long> {

    Airport findByName(String name);

    Airport findByIATA(String IATA);

    List<Airport> findAll();

}