package tp.backend.concesionaria.dtos;

import lombok.Data;
import java.util.List;

@Data
public class VehiculoDto{
    private int id;
    private String patente;
    private ModeloDto modelo;

    private List<PosicionDto> posiciones;
}