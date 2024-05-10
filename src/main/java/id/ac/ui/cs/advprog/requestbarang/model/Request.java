package id.ac.ui.cs.advprog.requestbarang.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import id.ac.ui.cs.advprog.requestbarang.enums.RequestStatus;
import lombok.Builder;

@Entity
@Table(name = "RequestBarang")
public class Request {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    @Setter
    @Getter
    private long id;

    @Column(name = "product_id")
    @Setter
    @Getter
    private long productId;

    @Column(name = "harga")
    @NotNull
    @Setter
    @Getter
    private int harga;

    @Column(name = "name")
    @NotNull
    @Setter
    @Getter
    private String name;

    @Column(name = "deskripsi")
    @Setter
    @Getter
    private String deskripsi;

    @Column(name = "image_link")
    @Setter
    @Getter
    private String imageLink;

    @Column(name = "store_link")
    @Setter
    @Getter
    private String storeLink;

    @Column(name = "status")
    @Setter
    @Getter
    private Boolean status;

    public Request(long id, long productId, int harga, String name, String deskripsi, String imageLink, String storeLink,
            Boolean status) {
        this.id = id;
        this.productId = productId;
        this.harga=harga;
        this.name = name;
        this.deskripsi = deskripsi;
        this.imageLink = imageLink;
        this.storeLink = storeLink;
        this.status = status;

    }

}
