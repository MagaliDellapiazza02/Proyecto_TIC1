package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

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

    private String state;

    @ManyToOne
    private Airport airport;

    public Runway(int runwayNumber, String state) {
        this.runwayNumber = runwayNumber;
        this.state = state;
    }
}