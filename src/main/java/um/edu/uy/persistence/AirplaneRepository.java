package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.entities.User;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    Airplane findByLicensePlate(String licensePlate);

}
