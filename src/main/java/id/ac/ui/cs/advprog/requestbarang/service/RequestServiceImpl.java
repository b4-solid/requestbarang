package id.ac.ui.cs.advpro.requestbarang.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import id.ac.ui.cs.advpro.requestbarang.models.Request;
import id.ac.ui.cs.advpro.requestbarang.repository.RequestRepository;
import id.ac.ui.cs.advpro.requestbarang.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    //C(reate)
    @Override
    public Request addRequest(Request request) {
        return repository.save(request);
    };

    //R(ead)
    @Override
    public List<Request> findAllRequest() {
        return repository.findAll();
    };

    @Override
    public Optional<Request> findById(Long id) {
        return repository.findById(id);
    };

    //U(pdate)
    @Override
    public Request updateRequest(Request request) {
        return repository.save(request);
    };

    //D(elete)
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    };
}