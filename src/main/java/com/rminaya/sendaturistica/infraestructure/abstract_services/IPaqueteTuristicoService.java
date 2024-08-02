package com.rminaya.sendaturistica.infraestructure.abstract_services;

import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoRequest;

public interface IPaqueteTuristicoService extends CrudService<PaqueteTuristicoResponse, PaqueteTuristicoRequest, Integer> {
}
