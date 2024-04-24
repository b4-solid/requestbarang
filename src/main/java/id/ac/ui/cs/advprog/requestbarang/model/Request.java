package id.ac.ui.cs.advprog.requestbarang.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

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
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
        this.currency = currency;
        this.storeLink = storeLink;
        this.status = "Requesting";
    }

    public Request(String id, String name, String imageLink, double price, String currency, String storeLink,
            String status) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
        this.currency = currency;
        this.storeLink = storeLink;

        String[] statusList = { "Requesting", "On Sale" };
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }

    }
}
