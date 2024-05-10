package id.ac.ui.cs.advprog.requestbarang.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import id.ac.ui.cs.advprog.requestbarang.enums.RequestStatus;
import lombok.Builder;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RequestBarang")
public class Request {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "harga")
    @NotNull
    private int harga;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "store_link")
    private String storeLink;

    @Column(name = "status")
    private Boolean status;

}
