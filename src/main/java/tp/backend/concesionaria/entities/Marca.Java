package tp.backend.concesionaria.entities;
import lombok.Data;
import javax.persistence.*;
import tp.backend.concesionaria.entities.*;

@Entity
@Table(name = "marcas")
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
