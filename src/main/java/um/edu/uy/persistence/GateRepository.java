package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Gate;
import um.edu.uy.business.entities.GateReservation;

import java.util.Date;
import java.util.List;

public interface GateRepository extends CrudRepository<Gate, Long> {
    @Query(value = "select * from gates where gateNumber=:number and airport=:airport", nativeQuery = true)
    Gate findOneByNumberAndAirport(@Param("airport") Airport airport, @Param("number") int number);

    @Query(value = "SELECT id \n" +
            "FROM gates g \n" +
            "WHERE g.airport_id = :airportID \n" +
            "AND g.id NOT IN (SELECT gate_id \n" +
            "                FROM gate_reservations gr \n" +
            "                WHERE gr.date <= :date1 \n" +
            "                AND ADDTIME(gr.date, gr.occupied_time) > :date1))", nativeQuery = true)
    List<GateReservation> findAvailableOnesByAirportIDAndDate(@Param("airportID") Long airportID, @Param("date1") Date date1);
}