package id.ac.ui.cs.advprog.requestbarang.service;

import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public Request createAndProcessRequest(String name, String imageLink, double price, String storeLink) {
        Request request = RequestFactory.createRequest(name, imageLink, price, storeLink);
        return request;
    }
}
