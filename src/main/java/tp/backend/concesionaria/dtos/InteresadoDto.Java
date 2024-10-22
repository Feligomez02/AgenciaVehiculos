

package tp.backend.concesionaria.dtos;

import lombok.Data;

@Data
public class InteresadoDto {
    private int id;
    private String tipo_documento;
    private String documento;
    private String nombre;
    private String apellido;
    private int restringido;
    private Long nro_licencia;
    private String fecha_vencimiento_licencia;
}