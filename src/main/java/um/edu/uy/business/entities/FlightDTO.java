package um.edu.uy.business.entities;

import lombok.*;

import java.util.Date;
//import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private String flightNumber; //lo asigna la aerolinea
    private String airplane;
    private String airlineOwner;
    private String flightIATA;
    private String flightICAO;

    // Detalles de la Ruta:
    private String originAirportIATA;
    private String destinyAirportIATA;

    //Horarios del Vuelo:
    private Date scheduledDeparture;  //ETD
    private Date scheduledArrival; //ETA

    //Estado del Vuelo:
    private String flightState;

}
