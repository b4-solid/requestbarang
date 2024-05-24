package id.ac.ui.cs.advprog.requestbarang.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;

public class RequestTest {
    @Test
    public void testNewRequest() {
        Request request = new Request(
                1L,
                123L,
                1000000,
                "IDR",
                "Kaito Kid Figure",
                "Figur kaito kid keren real",
                "image.jpg",
                "store.com",
                false
        );

        assertEquals(1L, request.getId());
        assertEquals(123L, request.getProductId());
        assertEquals(1000000, request.getHarga());
        assertEquals("IDR", request.getCurrency());
        assertEquals("Kaito Kid Figure", request.getName());
        assertEquals("Figur kaito kid keren real", request.getDeskripsi());
        assertEquals("image.jpg", request.getImageLink());
        assertEquals("store.com", request.getStoreLink());
        assertFalse(request.getStatus());
    }

    @Test
    public void testNullName() {
        assertThrows(ConstraintViolationException.class, () -> {
            new Request(
                    1L,
                    123L,
                    100,
                    "IDR",
                    null, // null name
                    "Figur kaito kid keren real",
                    "image.jpg",
                    "store.com",
                    false
            );
        });
    }
}
