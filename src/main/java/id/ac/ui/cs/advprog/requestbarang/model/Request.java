package id.ac.ui.cs.advprog.requestbarang.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder
@Getter
public class Request {
    private String id;
    private String name;
    private String imageLink;
    private double price;
    private String currency;
    private String storeLink;
    @Setter
    private String status;

    public Request(String id, String name, String imageLink, double price, String currency, String storeLink) {
    }

    public Request(String id, String name, String imageLink, double price, String currency, String storeLink,
            String status) {
    }
}
