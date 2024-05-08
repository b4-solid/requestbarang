package id.ac.ui.cs.advprog.requestbarang.controller;

import id.ac.ui.cs.advprog.requestbarang.model.Request;
import id.ac.ui.cs.advprog.requestbarang.service.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    private MockMvc mockMvc;

    @Test
    public void testCreateRequest() throws Exception {
        when(requestService.createAndProcessRequest(any(), any(), anyDouble(), any(), any()))
                .thenReturn(new Request("Test Request", "testimage.jpg", 100.0, "http://example.com"));

        mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/requests/create")
                        .param("name", "Test Request")
                        .param("imageLink", "testimage.jpg")
                        .param("price", "100.0")
                        .param("storeLink", "http://example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageLink").value("testimage.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storeLink").value("http://example.com"));
    }
}
