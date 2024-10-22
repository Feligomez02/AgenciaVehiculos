package tp.backend.concesionaria.entities;

import lombok.Data;
import javax.persistence.*;
import tp.backend.concesionaria.entities.*;

@Entity
@Table(name = "empleados")
@Data
public class Empleado {

    @Id
    private Long legajo;

    private String nombre;
    private String apellido;
    private String telefono_contacto;
}
