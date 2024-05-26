package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<RequestModel> requests = new ArrayList<>();
        RequestModel request1 = new RequestModel();
        request1.setId(1L);
        request1.setName("Kaito Kid Figure");
        request1.setHarga(1000000);
        requests.add(request1);

        RequestModel request2 = new RequestModel();
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
        RequestModel request = new RequestModel();
        request.setId(1L);
        request.setName("Test Request");
        request.setHarga(1000);

        when(requestService.addRequest(request)).thenReturn(request);

        CompletableFuture<ResponseEntity<RequestModel>> futureResponseEntity = requestController.createRequest(request);
        ResponseEntity<RequestModel> responseEntity = futureResponseEntity.get();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(request, responseEntity.getBody());
    }

    @Test
    void findById_ReturnsFoundRequest() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<RequestModel>> futureResponseEntity = requestController.findById(1L);
        ResponseEntity<RequestModel> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals("Kaito Kid Figure", responseEntity.getBody().getName());
        assertEquals(1000000, responseEntity.getBody().getHarga());
    }

    @Test
    void getAllRequests_ReturnsListOfRequests() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<List<RequestModel>>> futureResponseEntity = requestController.findAllRequests();
        ResponseEntity<List<RequestModel>> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void findById_ReturnsNotFound() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<RequestModel>> futureResponseEntity = requestController.findById(999L); // Using a non-existent ID
        ResponseEntity<RequestModel> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateRequest_ReturnsUpdatedRequest() throws ExecutionException, InterruptedException {
        RequestModel updatedRequest = new RequestModel(1L, 1L, "user123",10000, "IDR", "pulpen kaito kid", "pulpen sarasa", "image", "toko", false);
        CompletableFuture<ResponseEntity<RequestModel>> successResponse = CompletableFuture.completedFuture(ResponseEntity.ok(updatedRequest));
        when(requestService.updateRequest(any(RequestModel.class))).thenReturn(updatedRequest);

        CompletableFuture<ResponseEntity<RequestModel>> futureResponseEntity = requestController.updateRequest(updatedRequest.getId(), updatedRequest);
        ResponseEntity<RequestModel> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals("pulpen kaito kid", responseEntity.getBody().getName());
        assertEquals(10000, responseEntity.getBody().getHarga());
    }

    @Test
    void updateRequest_ReturnsNotFound() throws ExecutionException, InterruptedException {
        RequestModel updatedRequest = new RequestModel(999L, 1L, "user123", 3000000, "IDR", "hang glider kaito kid", "bisa terbang", "image", "toko", false);
        when(requestService.updateRequest(any(RequestModel.class))).thenThrow(new IllegalArgumentException());

        CompletableFuture<ResponseEntity<RequestModel>> futureResponseEntity = requestController.updateRequest(updatedRequest.getId(), updatedRequest);
        ResponseEntity<RequestModel> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteRequest_Success() throws ExecutionException, InterruptedException {
        long requestId = 1L;
        CompletableFuture<ResponseEntity<Void>> successResponse = CompletableFuture.completedFuture(ResponseEntity.ok().build());
        doNothing().when(requestService).deleteRequest(requestId);

        CompletableFuture<ResponseEntity<Void>> futureResponseEntity = requestController.deleteRequest(requestId);
        ResponseEntity<Void> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteRequest_Fail() throws ExecutionException, InterruptedException {
        long requestId = 999L;
        doThrow(new RuntimeException()).when(requestService).deleteRequest(requestId);

        CompletableFuture<ResponseEntity<Void>> futureResponseEntity = requestController.deleteRequest(requestId);
        ResponseEntity<Void> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}