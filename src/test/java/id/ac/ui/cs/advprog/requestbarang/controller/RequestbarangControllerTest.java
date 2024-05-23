package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import id.ac.ui.cs.advprog.requestbarang.controller.RequestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setId(1L);
        request1.setName("Kaito Kid Figure");
        request1.setHarga(1000000);
        requests.add(request1);

        Request request2 = new Request();
        request2.setId(123L);
        request2.setName("Kaito Kid Keychain");
        request2.setHarga(100000);
        requests.add(request2);

        when(requestService.findById(1L)).thenReturn(Optional.of(request1));
        when(requestService.findById(123L)).thenReturn(Optional.of(request2));
        when(requestService.findAllRequest()).thenReturn(requests);
    }

    @Test
    void createRequest_ReturnsCreatedRequest() throws ExecutionException, InterruptedException {
        Request request = new Request();
        request.setId(1L);
        request.setName("Test Request");
        request.setHarga(1000);

        when(requestService.addRequest(request)).thenReturn(request);

        CompletableFuture<ResponseEntity<Request>> futureResponseEntity = requestController.createRequest(request);
        ResponseEntity<Request> responseEntity = futureResponseEntity.get();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(request, responseEntity.getBody());
    }

    @Test
    void findById_ReturnsFoundRequest() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<Request>> futureResponseEntity = requestController.findById(1L);
        ResponseEntity<Request> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals("Kaito Kid Figure", responseEntity.getBody().getName());
        assertEquals(1000000, responseEntity.getBody().getHarga());
    }

    @Test
    void getAllRequests_ReturnsListOfRequests() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<List<Request>>> futureResponseEntity = requestController.findAllRequests();
        ResponseEntity<List<Request>> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }


}