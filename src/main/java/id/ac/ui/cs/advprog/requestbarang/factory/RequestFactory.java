package id.ac.ui.cs.advprog.requestbarang.factory;

import id.ac.ui.cs.advprog.requestbarang.model.Request;

public class RequestFactory {
    public static Request createRequest(String name, String imageLink, double price, String currency, String storeLink,
            String status) {
        int counter = 0;
        String requestId = "R" + String.format("%03d", counter++);
        return new Request(requestId, name, imageLink, price, currency, storeLink, status);
    }
}
