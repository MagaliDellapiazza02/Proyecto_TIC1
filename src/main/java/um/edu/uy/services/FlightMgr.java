package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.*;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FlightMgr {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private RunwayRepository runwayRepository;

    @Autowired
    private GateReservationRepository gateReservationRepository;

    @Autowired
    private RunwayReservationRepository runwayReservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightPassengerRepository flightPassengerRepository;


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

        flightRepository.save(flight);

    }


    public boolean validateFlight(Airport originAirport, Airport destinyAirport, Date departureDate, Date arrivalDate, Flight flight) {
        List<Runway> availableRunwaysAtOrigin = runwayRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);
        List<Gate> availableGatesAtOrigin = gateRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);

        List<Gate> availableGatesAtDestiny = gateRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);
        List<Runway> availableRunwaysAtDestiny = runwayRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);

        if (availableGatesAtOrigin.isEmpty() || availableRunwaysAtOrigin.isEmpty() || availableGatesAtDestiny.isEmpty() || availableRunwaysAtDestiny.isEmpty()) {
            return false;

        } else {
            Gate originGate = availableGatesAtOrigin.get(0);
            Gate arrivalGate = availableGatesAtDestiny.get(0);
            Runway originRunway = availableRunwaysAtOrigin.get(0);
            Runway arrivalRunway = availableRunwaysAtDestiny.get(0);

            //creo tiempos predeterminados para cada reserva de puerta y pista
            Time gateReserveTime = new Time(0, 30, 0);
            Time runwayReserveTime = new Time(0, 2, 0);

            //creo las reservas de puerta y pista
            GateReservation originGateReservation = new GateReservation(originAirport, originGate, departureDate, flight, gateReserveTime);
            GateReservation arrivalGateReservation = new GateReservation(destinyAirport, arrivalGate, arrivalDate, flight, gateReserveTime);
            RunwayReservation originRunwayReservation = new RunwayReservation(originAirport, originRunway, departureDate, flight, runwayReserveTime);
            RunwayReservation arrivalRunwayReservation = new RunwayReservation(destinyAirport, arrivalRunway, arrivalDate, flight, runwayReserveTime);

            //guardo las reservas
            gateReservationRepository.save(originGateReservation);
            gateReservationRepository.save(arrivalGateReservation);
            runwayReservationRepository.save(originRunwayReservation);
            runwayReservationRepository.save(arrivalRunwayReservation);
            return true;
        }
    }

    public boolean validateFlightOnOrigin(Airport originAirport, Date departureDate, Flight flight) {
        List<Runway> availableRunwaysAtOrigin = runwayRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);
        List<Gate> availableGatesAtOrigin = gateRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);


        if (availableGatesAtOrigin.isEmpty() || availableRunwaysAtOrigin.isEmpty()) {
            return false;

        } else {
            Gate originGate = availableGatesAtOrigin.get(0);
            Runway originRunway = availableRunwaysAtOrigin.get(0);

            //creo tiempos predeterminados para cada reserva de puerta y pista
            Time gateReserveTime = new Time(0, 30, 0);
            Time runwayReserveTime = new Time(0, 2, 0);

            Date gateReservationTime = new Date(departureDate.getTime() - new Time(0, 30, 0).getTime());

            //creo las reservas de puerta y pista
            GateReservation originGateReservation = new GateReservation(originAirport, originGate, gateReservationTime, flight, gateReserveTime);
            RunwayReservation originRunwayReservation = new RunwayReservation(originAirport, originRunway, departureDate, flight, runwayReserveTime);

            //guardo las reservas
            gateReservationRepository.save(originGateReservation);
            runwayReservationRepository.save(originRunwayReservation);
            return true;
        }
    }

    public boolean validateFlightOnDestiny(Airport destinyAirport, Date arrivalDate, Flight flight) {
        List<Gate> availableGatesAtDestiny = gateRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);
        List<Runway> availableRunwaysAtDestiny = runwayRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);

        if (availableGatesAtDestiny.isEmpty() || availableRunwaysAtDestiny.isEmpty()) {
            return false;

        } else {
            Gate arrivalGate = availableGatesAtDestiny.get(0);
            Runway arrivalRunway = availableRunwaysAtDestiny.get(0);

            //creo tiempos predeterminados para cada reserva de puerta y pista
            Time gateReserveTime = new Time(0, 30, 0);
            Time runwayReserveTime = new Time(0, 2, 0);

            //creo las reservas de puerta y pista
            GateReservation arrivalGateReservation = new GateReservation(destinyAirport, arrivalGate, arrivalDate, flight, gateReserveTime);
            RunwayReservation arrivalRunwayReservation = new RunwayReservation(destinyAirport, arrivalRunway, arrivalDate, flight, runwayReserveTime);

            //guardo las reservas
            gateReservationRepository.save(arrivalGateReservation);
            runwayReservationRepository.save(arrivalRunwayReservation);
            return true;
        }
    }

    public boolean addPassenger(Flight flight, String passport) {
        /*flight.getPassengers().clear();
        flight.setPassengersLeft(150);
        flightRepository.save(flight);

        Passenger p = passengerRepository.findPassengerByPassport(passport);
        p.getFlights().clear();
        passengerRepository.save(p);
        flightRepository.save(flight);
        return true;*/
        Passenger p = passengerRepository.findPassengerByPassport(passport);
        if (p == null || flight.getPassengersLeft() < 1) {
            return false;
        }
        /*
        List<Passenger> passengers = flight.getPassengers();
        List<Flight> flights =  p.getFlights();
        System.out.println(passengers.size());
        int pasLeft = flight.getPassengersLeft();

        if(passengers.contains(p) || flights.contains(flight)){
            return false;
        }


        passengers.add(p);
        */
        int pasLeft = flight.getPassengersLeft();
        FlightPassenger fP = flightPassengerRepository.findFlightPassengerByFlightAndPassenger(flight, p);
        if (fP != null){
            return false;
        }

        FlightPassenger newFlighPassenger = new FlightPassenger(flight, p);

        flight.setPassengersLeft(pasLeft-1);



        //flights.add(flight);

        flightRepository.save(flight);
        flightPassengerRepository.save(newFlighPassenger);
        //passengerRepository.save(p);
        return true;

    }


    public List<Flight> getFlightsFromDepartureAirport(boolean originApproved, String originAirportIATA, String flightState) {
        return flightRepository.findByOriginApprovedAndOriginAirportIATAAndFlightState(originApproved, originAirportIATA, flightState);
    }

    public List<Flight> getFlightsFromArrivalAirport(boolean destinyApproved, String destinyAirportIATA, String flightState) {
        return flightRepository.findByDestinyApprovedAndDestinyAirportIATAAndFlightState(destinyApproved, destinyAirportIATA, flightState);
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findOneByFlightNumber(flightNumber);
    }

}
