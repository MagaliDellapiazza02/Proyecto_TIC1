package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;

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

    //Lista de pasajeros y equipajes:
    @ManyToMany(mappedBy = "flights")
    private List<Passenger> passengers = new LinkedList<>();
    @ManyToMany(mappedBy = "flights")
    private List<Luggage> luggages = new LinkedList<>();



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
    }
}
