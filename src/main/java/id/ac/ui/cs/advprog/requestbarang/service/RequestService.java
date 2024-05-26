package id.ac.ui.cs.advprog.requestbarang.service;

import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;

public interface RequestService {

    //C(reate)
    RequestModel addRequest(RequestModel request);

    //R(ead)
    List<RequestModel> findAllRequest();
    List<RequestModel> findByUsername(String username);
    Optional<RequestModel> findById(Long id);

    //U(pdate)
    RequestModel updateRequest(RequestModel request);

    //D(elete)
    void deleteRequest(Long id);

    //Currency converter
    public double getRate(String baseCurrency);
}