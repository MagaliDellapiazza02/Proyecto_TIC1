package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Runway;

import java.util.Date;
import java.util.List;

public interface RunwayRepository extends CrudRepository<Runway, Long> {

    @Query(value = "select * from runways where Runwaynumber=:number and airport=:airport", nativeQuery = true)
    Runway findOneByNumberAndAirport(@Param("airport") Airport airport, @Param("number") int number);

    @Query(value = "SELECT * \n" +
            "FROM runways r \n" +
            "WHERE r.airport_id = :airportID \n" +
            "AND r.id NOT IN (SELECT runway_id \n" +
            "                FROM runway_reservation rr \n" +
            "                WHERE rr.date <= :date1 \n" +
            "                AND ADDTIME(rr.date, rr.occupied_time) > :date1)", nativeQuery = true)
    List<Runway> findAvailableOnesByAirportIDAndDate(@Param("airportID") Long airportID, @Param("date1") Date date1);
}