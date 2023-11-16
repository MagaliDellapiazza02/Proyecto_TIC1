package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import um.edu.uy.business.entities.Passenger;

import java.util.Optional;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

    Passenger findByDocument(long document);

    Optional<Passenger> findByMail(String mail);

    @Query("SELECT p FROM Passenger p LEFT JOIN FETCH p.luggageList WHERE p.mail = :mail")
    Optional<Passenger> findByMailWithLuggageList(@Param("mail") String mail);
}
