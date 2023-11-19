package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.FlightPassenger;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.EntityNotExists;
import um.edu.uy.persistence.FlightPassengerRepository;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerMgr {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightPassengerRepository flightPassengerRepository;

    public void addPassenger(Passenger p) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(passengerRepository.findByDocument(p.getDocument()) != null || passengerRepository.findPassengerByPassport(p.getPassport()) != null ||  checkIfUserNameExists(p.getMail()) || checkIfUserMailExists(p.getMail())) {
            throw new EntityAlreadyExists();
        }

        passengerRepository.save(p);
    }

    public String getNameByMail(String mail) {
        Passenger p = passengerRepository.findByMail(mail).get();
        if(p != null) {
            return p.getName();
        }
        return "None";
    }

    public boolean checkIfUserNameExists(String mail) {
        Optional<Passenger> passengerOptional = passengerRepository.findByMail(mail);
        return passengerOptional.isPresent();
    }

    public boolean checkIfUserMailExists(String mail) {
        Optional<User> userOptional = userRepository.findByMail(mail);
        return userOptional.isPresent();
    }

    public boolean checkLogIn(String mail, String password) {
        if (!checkIfUserNameExists(mail)) {
            return false;
        }
        Optional<Passenger> pOptional = passengerRepository.findByMail(mail);
        if(pOptional.isPresent()) {
            if(pOptional.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Passenger getPassengerByMail(String mail) {
        Passenger p = passengerRepository.findPassengerByMail(mail);
        return p;
    }

    public Passenger getPassengerByPassport(String passport) {
        Passenger p = passengerRepository.findPassengerByPassport(passport);
        return p;
    }

    public List<Passenger> getPassengersFromFlight(String flightNumber) {
        Flight flight = flightRepository.findOneByFlightNumber(flightNumber);

        List<FlightPassenger> flightPassengers = flightPassengerRepository.findByFlight(flight);
        List<Passenger> passengers = new ArrayList<>();

        for(int i = 0; i < flightPassengers.size(); i++) {
            FlightPassenger flightPassenger = flightPassengers.get(i);
            System.out.println("holaaaaa");
            System.out.println(flightPassenger.getPassenger().getPassport());
            System.out.println(flightPassenger.isCheckedIn());

            if (!flightPassenger.isCheckedIn()) {
                passengers.add(flightPassenger.getPassenger());
            }
        }
        return passengers;
    }

    public void passengerCheckedIn(Flight f, Passenger p) {
        FlightPassenger flightPassenger = flightPassengerRepository.findFlightPassengerByFlightAndPassenger(f, p);
        flightPassenger.setCheckedIn(true);
        flightPassengerRepository.save(flightPassenger);
    }

    public List<Flight> getFlightsFromPassenger(Passenger p) {
        List<FlightPassenger> flightPassengers = flightPassengerRepository.findFlightPassengerByPassenger(p);
        List<Flight> flights = new ArrayList<>();

        for(int i = 0; i < flightPassengers.size(); i++) {
            flights.add(flightPassengers.get(i).getFlight());
        }
        return flights;
    }
}
