package um.edu.uy.business.entities;

import jakarta.persistence.*;
import javafx.scene.control.TextField;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "luggage")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Luggage {

    @Id
    @GeneratedValue(generator="luggages_ids")
    @GenericGenerator(name="luggages_ids", strategy = "increment")
    private long id;

    private int weight;

    private String trackingCode;

    private String state;

    @ManyToOne
    private Passenger passenger;

    public Luggage(int weight, String trackingNumber) {
        this.weight = weight;
        this.trackingCode = trackingNumber;
    }
}