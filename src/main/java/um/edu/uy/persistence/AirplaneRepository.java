package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airplane;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    Airplane findByLicensePlate(String licensePlate);

}
