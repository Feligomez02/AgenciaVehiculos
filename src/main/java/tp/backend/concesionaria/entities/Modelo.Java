package tp.backend.concesionaria.entities;
import jakarta.persistence.*;
import lombok.Data;


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
