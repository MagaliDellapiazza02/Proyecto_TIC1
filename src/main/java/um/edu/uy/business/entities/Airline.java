package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.scene.control.TextField;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
    public long id;

    public String alnIATA;

    public String alnICAO;

    public String alnName;

    public String alnCountry;
    public Airline(String alnName, String alnIATA, String alnICAO, String alnCountry) {
        this.alnName = alnName;
        this.alnIATA = alnIATA;
        this.alnICAO = alnICAO;
        this.alnCountry = alnCountry;
    }
}