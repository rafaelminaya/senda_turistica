package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.requests.ServicioResponse;
import com.rminaya.sendaturistica.api.models.responses.ServicioRequest;

public interface IServicioService extends CrudService<ServicioResponse, ServicioRequest, Integer> {
}
