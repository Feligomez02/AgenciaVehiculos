package tp.backend.concesionaria.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patente;

    @ManyToOne
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo")
    private Set<Posicion> posiciones;
}
