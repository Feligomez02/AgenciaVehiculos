package tp.backend.concesionaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.backend.concesionaria.entities.Interesado;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, Long> {

}
