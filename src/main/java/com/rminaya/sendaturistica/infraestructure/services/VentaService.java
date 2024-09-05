package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.VentaRequest;
import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.domain.entities.*;
import com.rminaya.sendaturistica.domain.repositories.*;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IVentaService;
import com.rminaya.sendaturistica.infraestructure.mappers.VentaMapper;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final MedioPagoRepository medioPagoRepository;
    private final ServicioRepository servicioRepository;
    private final PaqueteTuristicoRepository paqueteTuristicoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    private VentaMapper ventaMapper;

    @Override
    @Transactional
    public VentaResponse create(VentaRequest ventaRequest) {

        ClienteEntity cliente = this.clienteRepository.findFirstByActivoTrueAndIdCliente(ventaRequest.getIdCliente())
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), ventaRequest.getIdCliente()));

        EmpleadoEntity empleado = this.empleadoRepository.findFirstByActivoTrueAndIdCliente(ventaRequest.getIdEmpleado())
                .orElseThrow(() -> new IdNotFoundException(Tables.empleados.name(), ventaRequest.getIdEmpleado()));

        MedioPagoEntity medioPago = this.medioPagoRepository.findById(ventaRequest.getIdMedioPago())
                .orElseThrow(() -> new IdNotFoundException(Tables.medios_pago.name(), ventaRequest.getIdMedioPago()));

        ServicioEntity servicio = null;
        PaqueteTuristicoEntity paqueteTuristico = null;

        if (ventaRequest.getIdServicio() != null) {
            servicio = this.servicioRepository.findFirstByActivoTrueAndIdServicio(ventaRequest.getIdServicio())
                    .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), ventaRequest.getIdServicio()));
        } else {
            paqueteTuristico = this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(ventaRequest.getIdPaqueteTuristico())
                    .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), ventaRequest.getIdPaqueteTuristico()));
        }

        VentaEntity ventaToPersist = VentaEntity.builder()
                .fechaVenta(LocalDateTime.now())
                .tipo(ventaRequest.getTipo())
                .cliente(cliente)
                .empleado(empleado)
                .medioPago(medioPago)
                .porcentajeComision(medioPago.getPorcentajeComision())
                .servicio(servicio)
                .paqueteTuristico(paqueteTuristico)
                .build();

        VentaEntity ventaPersited = this.ventaRepository.save(ventaToPersist);

        return this.ventaMapper.toVenta(ventaPersited);
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponse read(Integer idVenta) {
        VentaEntity ventaFromDB = this.ventaRepository.findFirstByActivoTrueAndIdVenta(idVenta)
                .orElseThrow(() -> new IdNotFoundException(Tables.ventas.name(), idVenta));
        return this.ventaMapper.toVenta(ventaFromDB);
    }

    @Override
    @Transactional
    public VentaResponse update(VentaRequest ventaRequest, Integer idVenta) {

        ClienteEntity cliente = this.clienteRepository.findFirstByActivoTrueAndIdCliente(ventaRequest.getIdCliente())
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), ventaRequest.getIdCliente()));

        EmpleadoEntity empleado = this.empleadoRepository.findFirstByActivoTrueAndIdCliente(ventaRequest.getIdEmpleado())
                .orElseThrow(() -> new IdNotFoundException(Tables.empleados.name(), ventaRequest.getIdEmpleado()));

        MedioPagoEntity medioPago = this.medioPagoRepository.findById(ventaRequest.getIdMedioPago())
                .orElseThrow(() -> new IdNotFoundException(Tables.medios_pago.name(), ventaRequest.getIdMedioPago()));

        ServicioEntity servicio = null;
        PaqueteTuristicoEntity paqueteTuristico = null;

        if (ventaRequest.getIdServicio() != null) {
            servicio = this.servicioRepository.findFirstByActivoTrueAndIdServicio(ventaRequest.getIdServicio())
                    .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), ventaRequest.getIdServicio()));
        } else {
            paqueteTuristico = this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(ventaRequest.getIdPaqueteTuristico())
                    .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), ventaRequest.getIdPaqueteTuristico()));
        }

        VentaEntity ventaToUpdate = this.ventaRepository.findFirstByActivoTrueAndIdVenta(idVenta)
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), idVenta));

        ventaToUpdate.setFechaVenta(LocalDateTime.now());
        ventaToUpdate.setTipo(ventaRequest.getTipo());
        ventaToUpdate.setCliente(cliente);
        ventaToUpdate.setMedioPago(medioPago);
        ventaToUpdate.setPorcentajeComision(medioPago.getPorcentajeComision());
        ventaToUpdate.setEmpleado(empleado);
        ventaToUpdate.setServicio(servicio);
        ventaToUpdate.setPaqueteTuristico(paqueteTuristico);

        VentaEntity ventaUpdated = this.ventaRepository.save(ventaToUpdate);

        return this.ventaMapper.toVenta(ventaUpdated);
    }

    @Override
    @Transactional
    public void delete(Integer idVenta) {
        VentaEntity ventaToDelete = this.ventaRepository.findFirstByActivoTrueAndIdVenta(idVenta)
                .orElseThrow(() -> new IdNotFoundException(Tables.ventas.name(), idVenta));
        ventaToDelete.setActivo(false);
        this.ventaRepository.save(ventaToDelete);
    }
}
