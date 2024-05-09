import id.ac.ui.cs.advprog.eshop.requestbarang.model.Request;
import id.ac.ui.cs.advprog.eshop.requestbarang.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RequestControllerTest {

    @Mock
    private RequestService requestService;

    private RequestController requestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setRequestId(1L);
        request1.setRequestName("Kaito Kid Figure");
        request1.setRequestHarga(1000000);
        requests.add(request1);

        Request request2 = new Request();
        request2.setRequestId(123L);
        request2.setRequestName("Kaito Kid Keychain");
        request2.setRequestHarga(100000);
        requests.add(request2);

        when(requestService.findById(1L)).thenReturn(request1);
        when(requestService.findById(123L)).thenReturn(request2);
        when(requestService.getAllRequests()).thenReturn(requests);
    }

    @Test
    void createRequest_ReturnsCreatedRequest() throws ExecutionException, InterruptedException {
        Request request = new Request();
        request.setRequestId(1L);
        request.setRequestName("Test Request");
        request.setRequestHarga(1000);

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
        assertEquals(1L, responseEntity.getBody().getRequestId());
        assertEquals("Kaito Kid Figure", responseEntity.getBody().getRequestName());
        assertEquals(1000000, responseEntity.getBody().getRequestHarga());
    }

    @Test
    void getAllRequests_ReturnsListOfRequests() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<List<Request>>> futureResponseEntity = requestController.findAllRequests();
        ResponseEntity<List<Request>> responseEntity = futureResponseEntity.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }


}