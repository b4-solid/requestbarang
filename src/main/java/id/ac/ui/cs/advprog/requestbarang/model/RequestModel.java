package id.ac.ui.cs.advprog.requestbarang.model;

import jakarta.validation.constraints.*;
import jakarta.validation.*;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "RequestBarang")
public class RequestModel {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "harga")
    @NotNull
    private int harga;

    @Column(name = "currency")
    @NotNull
    private String currency;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "store_link")
    @NotNull
    private String storeLink;

    @Column(name = "status")
    private Boolean status;

    @Builder
    public RequestModel(long id, long productId, String username, int harga, String currency, String name, String deskripsi, String imageLink, String storeLink, Boolean status) {
        this.id = id;
        this.productId = productId;
        this.username = username;
        this.harga = harga;
        this.currency = currency;
        this.name = name;
        this.deskripsi = deskripsi;
        this.imageLink = imageLink;
        this.storeLink = storeLink;
        this.status = status;

        // Manually trigger validation
        validate();
    }

    private void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestModel>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
