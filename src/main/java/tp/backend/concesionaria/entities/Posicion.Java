package tp.backend.concesionaria.entities;

import lombok.Data;
import javax.persistence.*;
import tp.backend.concesionaria.entities.*;

@Entity
@Table(name = "posiciones")
@Data
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    private Vehiculo vehiculo;

    private String fecha_hora;
    private double latitud;
    private double longitud;
}
