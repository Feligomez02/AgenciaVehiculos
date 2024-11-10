package tp.backend.concesionaria.entities;

import jakarta.persistence.*;
import lombok.Data;

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
