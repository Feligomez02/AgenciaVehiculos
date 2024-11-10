package tp.backend.concesionaria.entities;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "marcas")
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
