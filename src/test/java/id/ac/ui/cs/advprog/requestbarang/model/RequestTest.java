package id.ac.ui.cs.advprog.requestbarang.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    Request request;

    @BeforeEach
    void setUp() {
        this.request = new Request("R001", "Kaito Kid Figure", "image.com", 300000, "Rupiah", "dcmk.com");
        this.request.setStatus("Requesting");
    }

    @Test
    void testGetRequestId() {
        assertEquals("R001", this.request.getId());
    }

    @Test
    void testGetRequestName() {
        assertEquals("Kaito Kid Figure", this.request.getName());
    }

    @Test
    void testGetRequestPrice() {
        assertEquals(300000, this.request.getPrice());
    }
}
