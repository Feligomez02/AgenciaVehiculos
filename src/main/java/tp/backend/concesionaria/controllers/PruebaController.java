package tp.backend.concesionaria.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tp.backend.concesionaria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tp.backend.concesionaria.dtos.*;
import tp.backend.concesionaria.entities.*;
import tp.backend.concesionaria.services.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {
    private final PruebaService pruebaService;


    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @PostMapping("/create")
    public ResponseEntity<PruebaDto> createPrueba(@RequestBody PruebaDto pruebaDto) {
        // Validar que el cliente no esta restringido y la licencia no vencida.
        Long interesadoId = (long) pruebaDto.getInteresado().getId();
        Interesado interesado = pruebaService.getInteresadoById(interesadoId);

        if (interesado.isRestringido()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente restringido para pruebas.");
        }

        LocalDateTime fechaVencimientoLicencia = LocalDateTime.parse(interesado.getFecha_vencimiento_licencia(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (fechaVencimientoLicencia.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La licencia del interesado esta expirada.");
        }

        // Validacion de si el vehiculo esta siendo testeado
        boolean isVehicleBeingTested = pruebaService.estaSiendoTesteado((long) pruebaDto.getVehiculo().getId());
        if (isVehicleBeingTested) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vehiculo actualmente esta siendo testeado");
        }

        // Crear y guardar la prueba
        PruebaDto savedPrueba = pruebaService.savePrueba(pruebaDto);
        return ResponseEntity.ok(savedPrueba);
    }

}