package tp.backend.concesionaria.dtos;
import lombok.Data;

@Data
public class ModeloDto {
    private int id;
    private MarcaDto marca;
    private String descripcion;
}