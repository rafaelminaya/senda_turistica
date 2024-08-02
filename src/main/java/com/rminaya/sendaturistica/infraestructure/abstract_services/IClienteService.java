package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.ClienteRequest;

public interface IClienteService extends CrudService<ClienteResponse, ClienteRequest, Integer> {
}
