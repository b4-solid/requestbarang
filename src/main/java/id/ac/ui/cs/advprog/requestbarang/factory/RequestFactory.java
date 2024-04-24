package id.ac.ui.cs.advprog.requestbarang.factory;

import id.ac.ui.cs.advprog.requestbarang.model.Request;

public class RequestFactory {
    public static Request createRequest(String name, String imageLink, double price, String storeLink, String status) {
        return new Request(name, imageLink, price, storeLink, status);
    }
}
