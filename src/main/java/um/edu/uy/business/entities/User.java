package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(generator="users_ids")
    @GenericGenerator(name="users_ids", strategy = "increment")
    public long id;

    public long document;

    public String name;

    public String mail;

    public String password;

    public String address;

    public String company;

    public String role;

    public User(long document, String name, String mail, String password, String address, String company, String role) {
        this.document = document;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.company = company;
        this.role = role;
    }

    public User(String password, String mail) {
        this.password = password;
        this.mail = mail;
    }
}
