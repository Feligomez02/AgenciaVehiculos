package tp.backend.concesionaria.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PruebaDto {
    private Long id;
    private EmpleadoDto empleado;
    private InteresadoDto interesado;
    private VehiculoDto vehiculo;
    private ModeloDto modelo;
    private MarcaDto marca;
    private List<PosicionDto> posiciones;
    private String fecha_hora_inicio;
    private String fecha_hora_fin;
    private String comentarios;


}

