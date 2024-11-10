package tp.backend.concesionaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.backend.concesionaria.entities.Prueba;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(Long vehiculoId);
}

