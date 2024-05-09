import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestTest {
    @Test
    public void testNewRequest() {
        Request request = new Request(
                1L,
                123L,
                1000000,
                "Kaito Kid Figure",
                "Figur kaito kid keren real",
                "image.jpg",
                "store.com",
                false
        );

        assertEquals(1L, request.getId());
        assertEquals(123L, request.getProductId());
        assertEquals(1000000, request.getHarga());
        assertEquals("Kaito Kid Figure", request.getName());
        assertEquals("Figur kaito kid keren real", request.getDeskripsi());
        assertEquals("image.jpg", request.getImageLink());
        assertEquals("store.com", request.getStoreLink());
        assertTrue(request.getStatus());
    }

    @Test
    public void testNullName() {
        assertThrows(NullPointerException.class, () -> {
            new Request(
                    1L,
                    123L,
                    100,
                    null, // null name
                    "Figur kaito kid keren real",
                    "image.jpg",
                    "store.com",
                    false
            );
        });
    }
}
