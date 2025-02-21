package br.com.fiap.postech.logistics.controller;

import br.com.fiap.postech.logistics.application.usecases.TrackingUseCase;
import br.com.fiap.postech.logistics.interfaces.dtos.TrackingRequest;
import br.com.fiap.postech.logistics.interfaces.rest.TrackingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TrackingControllerTest {

    @Mock
    private TrackingUseCase trackingUseCase;

    private MockMvc mockMvc;

    @Test
    void shouldCallUseCaseOnPost() throws Exception {
        MockitoAnnotations.openMocks(this);
        TrackingController controller = new TrackingController(trackingUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        UUID deliveryId = UUID.randomUUID();
        TrackingRequest request = new TrackingRequest(10.0, 20.0);

        mockMvc.perform(post("/tracking")
                        .param("deliveryId", deliveryId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(trackingUseCase).updateLocation(deliveryId, 10.0, 20.0);
    }
}
