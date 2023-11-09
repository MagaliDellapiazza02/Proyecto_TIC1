package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.GateReservation;

public interface GateReservationRepository extends CrudRepository<GateReservation, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param code
     * @return
     */

}
