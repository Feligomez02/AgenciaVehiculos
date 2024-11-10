package tp.backend.concesionaria.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "empleados")
@Data
public class Empleado {

    @Id
    private Long legajo;

    private String nombre;
    private String apellido;
    private Long telefono_contacto;

}

