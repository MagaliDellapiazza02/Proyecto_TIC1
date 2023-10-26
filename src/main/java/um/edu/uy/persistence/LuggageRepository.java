package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.User;

public interface LuggageRepository extends CrudRepository<Luggage, String> {

    Airport findByTrackingCode(String trackingCode);

}

