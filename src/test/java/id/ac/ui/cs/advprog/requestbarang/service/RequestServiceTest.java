package id.ac.ui.cs.advprog.requestbarang.service;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.service.RequestServiceImpl;
import id.ac.ui.cs.advprog.requestbarang.repository.RequestRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @InjectMocks
    private RequestServiceImpl service;

    @Mock
    private RequestRepository repository;

    private Request request1;
    private Request request2;


    @BeforeEach
    public void setUp() {
        request1 = new Request();
        request1.setId(1L);
        request1.setName("Kaito Kid Figure");
        request1.setHarga(1000000);
        service.addRequest(request1);

        request2 = new Request();
        request2.setId(123L);
        request2.setName("Kaito Kid Keychain");
        request2.setHarga(100000);
        service.addRequest(request2);
    }

    @Test
    public void testAddRequest() {
        Request newRequest = new Request();
        newRequest.setId(456L);
        newRequest.setName("Kaito Kid Poster");
        newRequest.setHarga(50000);

        when(repository.save(newRequest)).thenReturn(newRequest);
        when(repository.findAll()).thenReturn(Arrays.asList(request1, request2, newRequest));

        service.addRequest(newRequest);

        verify(repository).save(newRequest);
        assertEquals(3, service.findAllRequest().size());
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
        Request request = new Request(1L, 1L, 10000, "pulpen kaito kid", "pulpen sarasa", "image", "toko", false);
        doReturn(Optional.of(request)).when(repository).findById(request.getId());

        Optional<Request> result = service.findById(request.getId());
        assertTrue(result.isPresent());
        assertEquals(request.getId(), result.get().getId());
    }

    @Test
    public void testUpdateRequest() {
        Request request = new Request();
        when(repository.save(request)).thenReturn(request);

        Request updatedRequest = service.updateRequest(request);

        assertEquals(request, updatedRequest);
        verify(repository, times(1)).save(request);
    }

    @Test
    public void testDeleteRequest() {
        Long id = 1L;

        service.deleteRequest(id);

        verify(repository, times(1)).deleteById(id);
    }
}
