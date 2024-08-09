package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.api.models.requests.VentaRequest;

public interface IVentaService extends CrudService<VentaResponse, VentaRequest, Integer> {
}
