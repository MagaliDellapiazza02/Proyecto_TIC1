package um.edu.uy.business.entities;

import lombok.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private String originAirportIATA = originAirport.getIATA();

    @ManyToOne // muchos vuelos pueden salir y llegar a un aeropuerto
    private Airport destinyAirport;
    private String destinyAirportIATA = destinyAirport.getIATA();

    //Horarios del Vuelo:
    private Date scheduledDeparture;  //ETD
    private Date scheduledArrival; //ETA
    private Date realDeparture; //ATD
    private Date realArrival; //ATA

    //Estado del Vuelo:
    private String flightState;

    public Flight(Airline airlineOwner, Airport originAirport, Airport destinyAirport, Airplane airplane, Date scheduledDeparture, Date scheduledArrival, String flightNumber) {
        this.airlineOwner = airlineOwner;
        this.originAirport = originAirport;
        this.destinyAirport = destinyAirport;
        this.airplane = airplane;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
    }
}
