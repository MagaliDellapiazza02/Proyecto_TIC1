package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Airline {

    @Id
    @GeneratedValue(generator="airlines_ids")
    @GenericGenerator(name="airlines_ids", strategy = "increment")
    private long id;

    private String alnIATA;

    private String alnICAO;

    private String alnName;

    private String alnCountry;

    @OneToMany(mappedBy = "airline", fetch = FetchType.EAGER)
    private List<Airplane> airplanes;

    public Airline(String alnName, String alnIATA, String alnICAO, String alnCountry) {
        this.alnName = alnName;
        this.alnIATA = alnIATA;
        this.alnICAO = alnICAO;
        this.alnCountry = alnCountry;
    }

}