package id.ac.ui.cs.advprog.requestbarang.factory;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestModelFactoryTest {

    private RequestFactory requestFactory;

    @BeforeEach
    void setUp() {
        requestFactory = new RequestFactoryImpl();
    }

    @Test
    void testCreateRequestModel_ValidData() {
        RequestModel requestModel = requestFactory.createRequestModel(
                1L,
                101L,
                "user123",
                200,
                "USD",
                "Kaito Kid Hat",
                "katun",
                "http://example.com/image.jpg",
                "http://example.com/store",
                false
        );

        assertNotNull(requestModel);
        assertEquals(1L, requestModel.getId());
        assertEquals(101L, requestModel.getProductId());
        assertEquals("user123", requestModel.getUsername());
        assertEquals(200, requestModel.getHarga());
        assertEquals("USD", requestModel.getCurrency());
        assertEquals("Kaito Kid Hat", requestModel.getName());
        assertEquals("katun", requestModel.getDeskripsi());
        assertEquals("http://example.com/image.jpg", requestModel.getImageLink());
        assertEquals("http://example.com/store", requestModel.getStoreLink());
        assertFalse(requestModel.getStatus());
    }

    @Test
    public void testCreateRequest_Fail() {
        assertThrows(ConstraintViolationException.class, () -> {
            new RequestModel(
                    1L,
                    123L,
                    "Dummy",
                    100,
                    "IDR",
                    null,
                    "Figur kaito kid keren real",
                    "image.jpg",
                    "store.com",
                    false
            );
        });
    }
}
