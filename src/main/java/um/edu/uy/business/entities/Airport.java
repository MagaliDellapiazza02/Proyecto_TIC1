package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    private Long id;

    public String name;

    public String tipo;

    public Airport(Long id, String name, String tipo) {
        this.id = id;
        this.name = name;
        this.tipo = tipo;
    }

    public Airport() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
