package tp.backend.concesionaria.config;

import lombok.Data;

@Data
public class ZonaRestringida {
    private Coordenadas noroeste;
    private Coordenadas sureste;

}