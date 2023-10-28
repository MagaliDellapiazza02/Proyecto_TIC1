package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.FlightDTO;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.PassengerRepository;
import org.hibernate.annotations.GenericGenerator;


@Service
public class FlightMgr {

    @Autowired
    private FlightRepository flightRepository;


    public void addFlight(Flight flight)
            throws InvalidInformation, EntityAlreadyExists {

        if (flight.getAirlineOwner() == null || "".equals(flight.getAirlineOwner()) ||
                flight.getOriginAirport() == null || "".equals(flight.getOriginAirport()) ||
                flight.getDestinyAirport() == null || "".equals(flight.getDestinyAirport()) ||
                flight.getAirplane() == null || "".equals(flight.getAirplane()) ||
                flight.getScheduledDeparture() == null || "".equals(flight.getScheduledDeparture()) ||
                flight.getScheduledArrival() == null || "".equals(flight.getScheduledArrival()) ||
                flight.getFlightNumber() == null || "".equals(flight.getFlightNumber())
        ) {
            //hacer algo
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si no existe

        if (flightRepository.findOneByFlightNumber(flight.getFlightNumber()) != null) {

            throw new EntityAlreadyExists();
        }

        Flight newFlight = new Flight(flight.getAirlineOwner(), flight.getOriginAirport(), flight.getDestinyAirport(), flight.getAirplane(), flight.getScheduledDeparture(), flight.getScheduledArrival(), flight.getFlightNumber());

        flightRepository.save(newFlight);

    }


    public void validateFlight(){


        /*
        Validar que ambos aeropuertos tengan una gateway y una runway disponible para el horario de salida y de llegada
         */


    }
}
