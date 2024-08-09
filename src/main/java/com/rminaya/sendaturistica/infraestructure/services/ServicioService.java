package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IServicioService;

public class ServicioService implements IServicioService {

    @Override
    public ServicioResponse create(ServicioRequest servicioRequest) {
        return null;
    }

    @Override
    public ServicioResponse read(Integer idServicio) {
        return null;
    }

    @Override
    public ServicioResponse update(ServicioRequest servicioRequest, Integer idServicio) {
        return null;
    }

    @Override
    public void delete(Integer idServicio) {

    }
}
