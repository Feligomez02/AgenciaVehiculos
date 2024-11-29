package tp.backend.concesionaria.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VehiculoDTO {
    private Integer id;
    private String patente;
    private ReporteDTO reporteDTO;
}
