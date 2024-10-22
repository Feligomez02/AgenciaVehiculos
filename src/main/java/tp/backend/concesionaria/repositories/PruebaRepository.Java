package tp.backend.concesionaria.repositories;

import tp.backend.concesionaria.entities.Prueba;
import java.util.List;
import org.springframework.data.repository.CrudRepository;




public interface PruebaRepository extends JpaRepository<Prueba, Long> {
}

