package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Airplane;

import java.util.List;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    Airplane findByLicensePlate(String licensePlate);

    List<Airplane> findAllByAirline(Airline airline);
}
