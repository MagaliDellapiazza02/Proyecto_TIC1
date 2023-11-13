package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airline;

public interface AirlineRepository extends CrudRepository<Airline, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */
    Airline findOneByAlnIATA(String code);
    Airline findOneByAlnName(String name);
}
