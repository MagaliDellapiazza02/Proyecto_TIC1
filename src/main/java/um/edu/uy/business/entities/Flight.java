package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "flights")
public class Flight {

    @Id
    private String flightNumber; //lo asigna la aerolinea

    //Datos básicos de un vuelo:
    @ManyToOne //  muchos vuelos pueden estar asociados con un avión
    private Airplane airplane;

    @ManyToOne // muchos vuelos pueden estar asociados con una aerolinea
    private Airline airlineOwner;
    private String flightIATA;
    private String flightICAO;

    // Detalles de la Ruta:
    @ManyToOne // muchos vuelos pueden salir y llegar a un aeropuerto
    private Airport originAirport;
    private String originAirportIATA;

    @ManyToOne // muchos vuelos pueden salir y llegar a un aeropuerto
    private Airport destinyAirport;
    private String destinyAirportIATA;

    //Horarios del Vuelo:
    private Date scheduledDeparture;  //ETD
    private Date scheduledArrival; //ETA
    private Date realDeparture; //ATD
    private Date realArrival; //ATA

    //Estado del Vuelo:
    private String flightState;
    private boolean originApproved;
    private boolean destinyApproved;

    //Capacidad de asientos y valijas libres
    private int passengersLeft;
    private int luggagesLeft;

    //Lista de pasajeros y equipajes:
    @ManyToMany(mappedBy = "flights", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    @ManyToMany(mappedBy = "flights", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Luggage> luggages;

    @OneToMany(mappedBy = "flight")
    private List<FlightPassenger> flightPassengers = new ArrayList<>();



    public Flight(Airline airlineOwner, Airport originAirport, Airport destinyAirport, Airplane airplane, Date scheduledDeparture, Date scheduledArrival, String flightNumber){
        this.airlineOwner = airlineOwner;
        this.originAirport = originAirport;
        this.destinyAirport = destinyAirport;
        this.airplane = airplane;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;

        this.flightIATA= airlineOwner.getAlnIATA();
        this.flightICAO= airlineOwner.getAlnICAO();

        this.flightState = "Pending";
        this.originApproved = false;
        this.destinyApproved = false;

        this.originAirportIATA = originAirport.getIATA();
        this.destinyAirportIATA = destinyAirport.getIATA();
        this.flightNumber = flightNumber;

        this.passengersLeft = airplane.getSeatCapacity();
        this.luggagesLeft = airplane.getLuggageCapacity();

        this.passengers = new ArrayList<>();
        this.luggages = new ArrayList<>();
    }


    public boolean addPassengerToList(Passenger p) {
        if (passengersLeft > 0) {
            System.out.println("que vien");
            if (passengersExist(p)) {
                return false;
            } else {
                passengers.add(p);
                System.out.println(passengers.isEmpty());
                System.out.println(passengers.get(1).getPassport());
                passengersLeft = passengersLeft - 1;
                return true;
            }
        }
        return false;
    }

    public boolean addLuggageToList(Luggage l) {
        int counter = luggagesLeft - l.getWeight();
        if (counter >= 0) {
            luggages.add(l);
            luggagesLeft = counter;
            return true;
        }
        return false;
    }

    public boolean passengersExist(Passenger p) {
        System.out.println(passengers.size());
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(passengers.get(i).getPassport());
            if (passengers.get(i).getPassport().equals(p.getPassport())) {
                return true;
            }
        }
        return false;
    }
}
