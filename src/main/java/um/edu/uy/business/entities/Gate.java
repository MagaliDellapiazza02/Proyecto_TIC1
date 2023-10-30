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
@Table(name = "gates")
@Builder
public class Gate {

    @Id
    @GeneratedValue(generator = "gate_ids")
    @GenericGenerator(name = "gate_ids", strategy = "increment")
    private Long id;

    private int gateNumber;

    private String state;

    @ManyToOne
    private Airport airport;

    public Gate(int gateNumber, String state) {
        this.gateNumber = gateNumber;
        this.state = state;
    }
}
