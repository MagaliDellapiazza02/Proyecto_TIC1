package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Luggage;

public interface LuggageRepository extends CrudRepository<Luggage, String> {

    Airport findByTrackingCode(String trackingCode);

}

