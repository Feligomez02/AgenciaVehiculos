package tp.backend.concesionaria.controllers;
import tp.backend.concesionaria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tp.backend.concesionaria.services.PruebaService;


@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {
    private final PruebaService pruebaService;


    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }
}