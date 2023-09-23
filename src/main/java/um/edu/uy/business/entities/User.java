package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator="users_ids")
    @GenericGenerator(name="users_ids", strategy = "increment")
    public long id;

    public long document;

    public String name;

    public String address;

    public User() {
    }

    public User(long document, String name, String address) {
        this.document = document;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }
}
