package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.requests.VentaResponse;
import com.rminaya.sendaturistica.api.models.responses.VentaRequest;

public interface IVentaService extends CrudService<VentaResponse, VentaRequest, Integer> {
}
