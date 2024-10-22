package tp.backend.concesionaria.entities;
import lombok.Data;
import javax.persistence.*;
import tp.backend.concesionaria.entities.*;

@Entity
@Table(name = "modelos")
@Data
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    private Marca marca;

    private String descripcion;
}
