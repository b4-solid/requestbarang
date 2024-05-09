import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequestServiceImplTest {

    @Mock
    private RequestRepository repository;

    @InjectMocks
    private RequestServiceImpl service;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    public void testAddRequest() {
        Request request = requests.get(0);
        doReturn(request).when(repository).save(request);

        Request result = service.addRequest(request);
        verify(repository, times(1)).save(request);
        assertEquals(request.getId(), result.getId());
    }

    @Test
    public void testFindAllRequest() {
        List<Request> requests = requests;
        when(repository.findAll()).thenReturn(requests);

        List<Request> allRequests = service.findAllRequest();

        assertEquals(2, allRequests.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Request request = requests.get(1);
        doReturn(request).when(repository).findById(request.getId());

        Request result = service.findById(request.getId());
        assertEquals(request.getId(), result.getId());
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
