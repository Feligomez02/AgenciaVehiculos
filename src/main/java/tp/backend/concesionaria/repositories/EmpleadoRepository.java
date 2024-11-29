package tp.backend.concesionaria.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import tp.backend.concesionaria.entities.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@NoArgsConstructor
@Repository
public class EmpleadoRepository {
    @PersistenceContext
    private EntityManager em;

    // Métodos
    public Empleado findByLegajo(Integer legajo){
        try {
            return em.find(Empleado.class, legajo);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Prueba> obtenerPruebasIncidentePorLegajo(Integer legajo) {
        try {
            return em.createQuery("SELECT p FROM Prueba p WHERE p.inicidente = TRUE AND p.empleado.legajo = :legajo", Prueba.class)
                    .setParameter("legajo", legajo)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean existePruebaActivaParaEmpleado(Integer legajo) {
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Prueba p WHERE p.fecha_hora_fin IS NULL AND p.empleado.legajo = :legajo", Long.class)
                    .setParameter("legajo", legajo)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

