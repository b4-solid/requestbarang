package id.ac.ui.cs.advprog.requestbarang.factory;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;
import jakarta.validation.*;
import java.util.Set;

public class RequestFactoryImpl implements RequestFactory {

    @Override
    public RequestModel createRequestModel(long id, long productId, String username, int harga, String currency, String name, String deskripsi, String imageLink, String storeLink, Boolean status) {
        RequestModel requestModel = RequestModel.builder()
                .id(id)
                .productId(productId)
                .username(username)
                .harga(harga)
                .currency(currency)
                .name(name)
                .deskripsi(deskripsi)
                .imageLink(imageLink)
                .storeLink(storeLink)
                .status(status)
                .build();

        validate(requestModel);

        return requestModel;
    }

    private void validate(RequestModel requestModel) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestModel>> violations = validator.validate(requestModel);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

