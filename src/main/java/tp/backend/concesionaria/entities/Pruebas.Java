package tp.backend.concesionaria.entities;

import lombok.Data;
import javax.persistence.*;
import tp.backend.concesionaria.entities.*;

@Entity
@Table(name = "pruebas")
@Data
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_interesado", referencedColumnName = "id")
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "legajo")
    private Empleado empleado;

    private String fecha_hora_inicio;
    private String fecha_hora_fin;
    private String comentarios;
}
