package br.pucminas.lab1.grupo6.folha.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    void deveTratarUserNotFoundCom404() {
        when(request.getRequestURI()).thenReturn("/users/1");

        var response = handler.handleUserNotFound(new UserNotFoundException("nao achou"), request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        var body = response.getBody();
        assertNotNull(body);
        assertEquals(404, body.getStatus());
        assertEquals("Not Found", body.getError());
        assertEquals("nao achou", body.getMessage());
        assertEquals("/users/1", body.getPath());
    }

    @Test
    void deveTratarInvalidCredentialsCom401() {
        when(request.getRequestURI()).thenReturn("/login");

        var response = handler.handleInvalidCredentials(new InvalidCredentialsException("credenciais"), request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        var body = response.getBody();
        assertNotNull(body);
        assertEquals(401, body.getStatus());
        assertEquals("Unauthorized", body.getError());
        assertEquals("credenciais", body.getMessage());
        assertEquals("/login", body.getPath());
    }

    @Test
    void deveTratarInvalidRequestCom400() {
        when(request.getRequestURI()).thenReturn("/folha");

        var response = handler.handleInvalidRequest(new InvalidRequestException("payload"), request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        var body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getStatus());
        assertEquals("Bad Request", body.getError());
        assertEquals("payload", body.getMessage());
        assertEquals("/folha", body.getPath());
    }
}
