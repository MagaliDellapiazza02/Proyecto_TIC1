package um.edu.uy.persistence;

import javafx.collections.ObservableList;
import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airline;

import java.util.List;

public interface AirlineRepository extends CrudRepository<Airline, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */
    Airline findOneByAlnIATA(String code);
    Airline findOneByAlnName(String name);

    List<Airline> findAll();
    List<Airline> getAirlineByAlnIATA(String IATA);
}
