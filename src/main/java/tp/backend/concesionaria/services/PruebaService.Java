package tp.backend.concesionaria.services;

import tp.backend.concesionaria.entities.Prueba;


@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

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
        dto.setTelefonoContacto(empleado.getTelefonoContacto());
        return dto;
    }

    private Empleado toEmpleadoEntity(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setLegajo(dto.getLegajo());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setTelefonoContacto(dto.getTelefonoContacto());
        return empleado;
    }

    // Mapeo para Interesado
    private InteresadoDto toInteresadoDto(Interesado interesado) {
        InteresadoDto dto = new InteresadoDto();
        dto.setId(interesado.getId());
        dto.setTipoDocumento(interesado.getTipoDocumento());
        dto.setDocumento(interesado.getDocumento());
        dto.setNombre(interesado.getNombre());
        dto.setApellido(interesado.getApellido());
        dto.setRestringido(interesado.isRestringido());
        dto.setNroLicencia(interesado.getNroLicencia());
        dto.setFechaVencimientoLicencia(interesado.getFechaVencimientoLicencia());
        return dto;
    }

    private Interesado toInteresadoEntity(InteresadoDto dto) {
        Interesado interesado = new Interesado();
        interesado.setId(dto.getId());
        interesado.setTipoDocumento(dto.getTipoDocumento());
        interesado.setDocumento(dto.getDocumento());
        interesado.setNombre(dto.getNombre());
        interesado.setApellido(dto.getApellido());
        interesado.setRestringido(dto.isRestringido());
        interesado.setNroLicencia(dto.getNroLicencia());
        interesado.setFechaVencimientoLicencia(dto.getFechaVencimientoLicencia());
        return interesado;
    }

    // Mapeo Vehiculo
    private VehiculoDto toVehiculoDto(Vehiculo vehiculo) {
        VehiculoDto dto = new VehiculoDto();
        dto.setId(vehiculo.getId());
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
        vehiculo.setId(dto.getId());
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setModelo(toModeloEntity(dto.getModelo()));
        return vehiculo;
    }

    // Mapeo Modelo
    private ModeloDto toModeloDto(Modelo modelo) {
        ModeloDto dto = new ModeloDto();
        dto.setId(modelo.getId());
        dto.setDescripcion(modelo.getDescripcion());
        dto.setMarca(toMarcaDto(modelo.getMarca()));
        return dto;
    }

    private Modelo toModeloEntity(ModeloDto dto) {
        Modelo modelo = new Modelo();
        modelo.setId(dto.getId());
        modelo.setDescripcion(dto.getDescripcion());
        modelo.setMarca(toMarcaEntity(dto.getMarca()));
        return modelo;
    }

    // Mapeo Marca
    private MarcaDto toMarcaDto(Marca marca) {
        MarcaDto dto = new MarcaDto();
        dto.setId(marca.getId());
        dto.setNombre(marca.getNombre());
        return dto;
    }

    private Marca toMarcaEntity(MarcaDto dto) {
        Marca marca = new Marca();
        marca.setId(dto.getId());
        marca.setNombre(dto.getNombre());
        return marca;
    }

    // Mapeo Posicion
    private PosicionDto toPosicionDto(Posicion posicion) {
        PosicionDto dto = new PosicionDto();
        dto.setId(posicion.getId());
        dto.setFechaHora(posicion.getFechaHora());
        dto.setLatitud(posicion.getLatitud());
        dto.setLongitud(posicion.getLongitud());
        return dto;
    }

    private Posicion toPosicionEntity(PosicionDto dto) {
        Posicion posicion = new Posicion();
        posicion.setId(dto.getId());
        posicion.setFechaHora(dto.getFechaHora());
        posicion.setLatitud(dto.getLatitud());
        posicion.setLongitud(dto.getLongitud());
        return posicion;
    }
}
