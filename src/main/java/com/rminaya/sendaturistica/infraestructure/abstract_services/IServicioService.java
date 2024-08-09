package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;

public interface IServicioService extends CrudService<ServicioResponse, ServicioRequest, Integer> {
}
