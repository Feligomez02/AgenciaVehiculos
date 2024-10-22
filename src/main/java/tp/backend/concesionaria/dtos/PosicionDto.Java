
package tp.backend.concesionaria.dtos;

import lombok.Data;

@Data
public class PosicionDto {
    private int id;
    private int id_vehiculo;
    private String fecha_hora;
    private double latitud;
    private double longitud;
}