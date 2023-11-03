package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, String> {

        /**
        * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
        * @param code
        * @return
        */
        Flight findOneByFlightNumber(String code);

        /*@Query
        Flight findOneByFlightNumberAndAirlineOwner(String code, String airlineOwner);
*/
        //Encontrar las pistas libres de un aeropuerto para tal fecha
       /*
       @Query (value="select * from [nombre de la tabla] a where a.first_name= :firstName", nativeQuery=true)
        List<Gate> getAvailableGatesbyDateAndAirport(String originAirport, String scheduledDeparture);
                */

}
