package tp.backend.concesionaria.dtos;

import lombok.Data;

@Data
public class PruebaDto {
    private EmpleadoDto empleado;
    private InteresadoDto interesado;
    private VehiculoDto vehiculo;
    private String fecha_hora_inicio;
    private String fecha_hora_fin;
    private String comentarios;
}

