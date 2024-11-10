package tp.backend.concesionaria.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;
import tp.backend.concesionaria.dtos.*;
import tp.backend.concesionaria.entities.*;
import tp.backend.concesionaria.repositories.PruebaRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    public PruebaService(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }


    // Método de ejemplo para obtener una Prueba por ID y mapear a PruebaDto
    public PruebaDto getPruebaById(Long id) {
        Prueba prueba = pruebaRepository.findById(id)
                           .orElseThrow(() -> new EntityNotFoundException("Prueba not found"));

        return toPruebaDto(prueba);  // Convertir la entidad a DTO
    }

    public List<PruebaDto> getAllPruebas() {
        return pruebaRepository.findAll().stream()
               .map(this::toPruebaDto)  // Convertir cada entidad a DTO
               .collect(Collectors.toList());
    }

    // Método de ejemplo para guardar una Prueba desde un DTO
    public PruebaDto savePrueba(PruebaDto dto) {
        // Convertir el DTO a una entidad
        Prueba prueba = toPruebaEntity(dto);
        prueba = pruebaRepository.save(prueba);  // Guardar la entidad
        return toPruebaDto(prueba);  // Devolver la entidad guardada como un DTO
    }

    // Mapeos (Entidad -> DTO)
    private PruebaDto toPruebaDto(Prueba prueba) {
        PruebaDto dto = new PruebaDto();
        dto.setId(prueba.getId());
        dto.setEmpleado(toEmpleadoDto(prueba.getEmpleado()));
        dto.setInteresado(toInteresadoDto(prueba.getInteresado()));
        
        VehiculoDto vehiculoDto = toVehiculoDto(prueba.getVehiculo());
        dto.setVehiculo(vehiculoDto);
        dto.setModelo(vehiculoDto.getModelo());
        dto.setMarca(vehiculoDto.getModelo().getMarca());

        // Establecer posiciones
        dto.setPosiciones(prueba.getVehiculo().getPosiciones().stream()
                         .map(this::toPosicionDto).collect(Collectors.toList()));
        return dto;
    }

    // Mapeos (DTO -> Entidad)
    private Prueba toPruebaEntity(PruebaDto dto) {
        Prueba prueba = new Prueba();
        prueba.setEmpleado(toEmpleadoEntity(dto.getEmpleado()));
        prueba.setInteresado(toInteresadoEntity(dto.getInteresado()));
        prueba.setVehiculo(toVehiculoEntity(dto.getVehiculo()));
        // Aquí se establecerían campos adicionales (como fecha_hora_inicio, etc.)
        return prueba;
    }

    public PruebaDto updatePrueba(Long id, PruebaDto pruebaDto){
        Prueba prueba = pruebaRepository.findById(id)
                           .orElseThrow(() -> new EntityNotFoundException("Prueba not found"));

        prueba.setEmpleado(toEmpleadoEntity(pruebaDto.getEmpleado()));
        prueba.setInteresado(toInteresadoEntity(pruebaDto.getInteresado()));
        prueba.setVehiculo(toVehiculoEntity(pruebaDto.getVehiculo()));
        // Aquí se establecerían campos adicionales (como fecha_hora_inicio, etc.)

        prueba = pruebaRepository.save(prueba);
        return toPruebaDto(prueba);
    }

    public void deletePrueba(Long id){
        Prueba prueba = pruebaRepository.findById(id)
                           .orElseThrow(() -> new EntityNotFoundException("Prueba not found"));
        pruebaRepository.delete(prueba);
    }

    
    // Mapeo para Empleado
    private EmpleadoDto toEmpleadoDto(Empleado empleado) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setLegajo(empleado.getLegajo());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setTelefono_contacto(empleado.getTelefono_contacto());
        return dto;
    }

    private Empleado toEmpleadoEntity(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setLegajo(dto.getLegajo());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setTelefono_contacto(dto.getTelefono_contacto());
        return empleado;
    }

    // Mapeo para Interesado
    private InteresadoDto toInteresadoDto(Interesado interesado) {
        InteresadoDto dto = new InteresadoDto();
        dto.setId(Math.toIntExact(interesado.getId()));
        dto.setTipo_documento(interesado.getTipo_documento());
        dto.setDocumento(interesado.getDocumento());
        dto.setNombre(interesado.getNombre());
        dto.setApellido(interesado.getApellido());
        dto.setRestringido(interesado.isRestringido());
        dto.setNro_licencia(Long.valueOf(interesado.getNro_licencia()));
        dto.setFecha_vencimiento_licencia(interesado.getFecha_vencimiento_licencia());
        return dto;
    }

    private Interesado toInteresadoEntity(InteresadoDto dto) {
        Interesado interesado = new Interesado();
        interesado.setId((long) dto.getId());
        interesado.setTipo_documento(dto.getTipo_documento());
        interesado.setDocumento(dto.getDocumento());
        interesado.setNombre(dto.getNombre());
        interesado.setApellido(dto.getApellido());
        interesado.setRestringido(dto.getRestringido());
        interesado.setNro_licencia(String.valueOf(dto.getNro_licencia()));
        interesado.setFecha_vencimiento_licencia(dto.getFecha_vencimiento_licencia());
        return interesado;
    }

    // Mapeo Vehiculo
    private VehiculoDto toVehiculoDto(Vehiculo vehiculo) {
        VehiculoDto dto = new VehiculoDto();
        dto.setId(Math.toIntExact(vehiculo.getId()));
        dto.setPatente(vehiculo.getPatente());
        dto.setModelo(toModeloDto(vehiculo.getModelo()));
        dto.setPosiciones(vehiculo.getPosiciones()
                                  .stream()
                                  .map(this::toPosicionDto)
                                  .collect(Collectors.toList()));
        return dto;
    }

    private Vehiculo toVehiculoEntity(VehiculoDto dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId((long) dto.getId());
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setModelo(toModeloEntity(dto.getModelo()));
        return vehiculo;
    }

    // Mapeo Modelo
    private ModeloDto toModeloDto(Modelo modelo) {
        ModeloDto dto = new ModeloDto();
        dto.setId(Math.toIntExact(modelo.getId()));
        dto.setDescripcion(modelo.getDescripcion());
        dto.setMarca(toMarcaDto(modelo.getMarca()));
        return dto;
    }

    private Modelo toModeloEntity(ModeloDto dto) {
        Modelo modelo = new Modelo();
        modelo.setId((long) dto.getId());
        modelo.setDescripcion(dto.getDescripcion());
        modelo.setMarca(toMarcaEntity(dto.getMarca()));
        return modelo;
    }

    // Mapeo Marca
    private MarcaDto toMarcaDto(Marca marca) {
        MarcaDto dto = new MarcaDto();
        dto.setId(Math.toIntExact(marca.getId()));
        dto.setMarca(marca.getNombre());
        return dto;
    }

    private Marca toMarcaEntity(MarcaDto dto) {
        Marca marca = new Marca();
        marca.setId((long) dto.getId());
        marca.setNombre(dto.getMarca());
        return marca;
    }

    // Mapeo Posicion
    private PosicionDto toPosicionDto(Posicion posicion) {
        PosicionDto dto = new PosicionDto();
        dto.setId(Math.toIntExact(posicion.getId()));
        dto.setFecha_hora(posicion.getFecha_hora());
        dto.setLatitud(posicion.getLatitud());
        dto.setLongitud(posicion.getLongitud());
        return dto;
    }

    private Posicion toPosicionEntity(PosicionDto dto) {
        Posicion posicion = new Posicion();
        posicion.setId((long) dto.getId());
        posicion.setFecha_hora(dto.getFecha_hora());
        posicion.setLatitud(dto.getLatitud());
        posicion.setLongitud(dto.getLongitud());
        return posicion;
    }
}
