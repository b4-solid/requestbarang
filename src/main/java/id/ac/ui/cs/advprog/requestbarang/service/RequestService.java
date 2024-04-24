package id.ac.ui.cs.advprog.requestbarang.service;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.requestbarang.factory.RequestFactory;
import id.ac.ui.cs.advprog.requestbarang.model.Request;

@Service
public class RequestService {
    public Request createAndProcessRequest(String name, String imageLink, double price, String currency,
            String storeLink) {
        int counter = 0;
        String requestId = "R" + String.format("%03d", counter++);
        Request request = RequestFactory.createRequest(requestId, name, price, imageLink, currency, storeLink);
        return request;
    }
}
