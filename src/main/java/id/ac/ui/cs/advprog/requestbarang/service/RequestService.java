package id.ac.ui.cs.advprog.requestbarang.service;

import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advpro.requestbarang.models.Request;

public interface RequestService {

    //C(reate)
    Request addRequest(Request request);

    //R(ead)
    List<Request> findAllRequest();
    Optional<Request> findById(Long id);

    //U(pdate)
    Request updateRequest(Request request);

    //D(elete)
    void deleteRequest(Long id);
}