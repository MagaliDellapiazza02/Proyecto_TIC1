package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;

import java.util.Optional;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

    Passenger findByDocument(long document);

    Optional<Passenger> findByMail(String mail);
}
