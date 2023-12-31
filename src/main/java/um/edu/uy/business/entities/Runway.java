package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "runways")
@Builder

public class Runway {

    @Id
    @GeneratedValue(generator = "runway_ids")
    @GenericGenerator(name = "runway_ids", strategy = "increment")
    private Long id;

    private int runwayNumber;

    @ManyToOne
    private Airport airport;

    public Runway(int runwayNumber) {
        this.runwayNumber = runwayNumber;
    }

    public Runway(int runwayNumber, Airport airport) {
        this.runwayNumber = runwayNumber;
        this.airport = airport;
    }
}