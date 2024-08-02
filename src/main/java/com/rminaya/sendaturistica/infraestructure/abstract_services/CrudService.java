package com.rminaya.sendaturistica.infraestructure.abstract_services;

public interface CrudService<RS, RQ, ID> {

    RS create(RQ rq);
    RS read(ID id);
    RS update(RQ rq, ID id);
    void delete(ID id);
}
