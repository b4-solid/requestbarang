package id.ac.ui.cs.advprog.requestbarang.service;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.repository.RequestRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @InjectMocks
    private RequestServiceImpl service;

    @Mock
    private RequestRepository repository;
    @Mock
    private RestTemplate restTemplate;


    private Request request1;
    private Request request2;


    @BeforeEach
    public void setUp() {
        request1 = new Request();
        request1.setId(1L);
        request1.setName("Kaito Kid Figure");
        request1.setHarga(1000000);
        request1.setCurrency("IDR");
        service.addRequest(request1);

        request2 = new Request();
        request2.setId(123L);
        request2.setName("Kaito Kid Keychain");
        request2.setHarga(100000);
        request2.setCurrency("IDR");
        service.addRequest(request2);
    }

    @Test
    public void testAddRequest() {
        // Prepare test data
        Request request = new Request();
        request.setHarga(100);
        request.setCurrency("USD");

        // Mock response from the external API
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("USD", 0.0001); // Mock exchange rate for USD
        mockResponse.put("rates", mockRates);
        when(restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/IDR", Map.class)).thenReturn(mockResponse);

        // Mock repository save method
        when(repository.save(request)).thenReturn(request);

        // Perform the test
        Request savedRequest = service.addRequest(request);

        // Assert the result
        assertEquals(0.01, savedRequest.getHarga(), 0.001); // Expected conversion: 100 * 0.0001 = 0.01 IDR
    }

    @Test
    public void testFindAllRequest() {
        when(repository.findAll()).thenReturn(Arrays.asList(request1, request2));
        List<Request> allRequests = service.findAllRequest();

        assertEquals(2, allRequests.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Request request = new Request(1L, 1L, 10000, "IDR", "pulpen kaito kid", "pulpen sarasa", "image", "toko", false);
        doReturn(Optional.of(request)).when(repository).findById(request.getId());

        Optional<Request> result = service.findById(request.getId());
        assertTrue(result.isPresent());
        assertEquals(request.getId(), result.get().getId());
    }

    @Test
    public void testUpdateRequest() {
        Request request = new Request();
        request.setCurrency("IDR");
        when(repository.save(request)).thenReturn(request);

        Request updatedRequest = service.updateRequest(request);
        updatedRequest.setCurrency("JPY");

        assertEquals(request, updatedRequest);
        verify(repository, times(1)).save(request);
    }

    @Test
    public void testDeleteRequest() {
        Long id = 1L;

        service.deleteRequest(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testConvertToIDR_USD() {
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("USD", 0.0001); // Mock exchange rate for USD
        mockResponse.put("rates", mockRates);
        when(restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/IDR", Map.class)).thenReturn(mockResponse);

        double convertedPrice = service.convertToIDR(100, "USD");
        assertEquals(0.01, convertedPrice, 0.001);
    }

    @Test
    public void testConvertToIDR_JPY() {
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("JPY", 0.0095); // Mock exchange rate for JPY
        mockResponse.put("rates", mockRates);
        when(restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/IDR", Map.class)).thenReturn(mockResponse);

        double convertedPrice = service.convertToIDR(100, "JPY");
        assertEquals(0.95, convertedPrice, 0.001);
    }

}
