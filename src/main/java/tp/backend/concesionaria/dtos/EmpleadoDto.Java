
package tp.backend.concesionaria.dtos;

import lombok.Data;

@Data
public class EmpleadoDto {
    private Long legajo;
    private String nombre;
    private String apellido;
    private Long telefono_contacto;
}