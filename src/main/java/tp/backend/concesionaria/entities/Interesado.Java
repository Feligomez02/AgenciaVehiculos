package tp.backend.concesionaria.entities;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "interesados")
@Data
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_documento;
    private String documento;
    private String nombre;
    private String apellido;
    private boolean restringido;
    private String nro_licencia;
    private String fecha_vencimiento_licencia;
}
