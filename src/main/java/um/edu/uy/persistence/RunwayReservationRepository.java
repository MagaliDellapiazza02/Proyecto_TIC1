package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.RunwayReservation;

public interface RunwayReservationRepository extends CrudRepository<RunwayReservation, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */
}
