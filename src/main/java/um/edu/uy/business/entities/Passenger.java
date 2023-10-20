package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(generator="passengers_ids")
    @GenericGenerator(name="passengers_ids", strategy = "increment")
    public long id;

    public long document;

    public String name;

    public String mail;

    public String password;

    public Passenger(long document, String name, String mail, String password) {
        this.document = document;
        this.name = name;
        this.mail = mail;
        this.password = password;
    }
}
