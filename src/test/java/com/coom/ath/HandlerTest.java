package com.coom.ath;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.coom.ath.model.ParameterStoreDto;
import com.coom.ath.model.Usuario;
import com.coom.ath.repository.DynamoMapperRepository;
import com.coom.ath.repository.ParameterStoreRepository;
import com.coom.ath.service.ConsumiService;
import com.coom.ath.service.DynamoService;
import com.coom.ath.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HandlerTest {

    @Mock
    DynamoService dynamoService;

    @Mock
    ConsumiService consumiService;

    @Mock
    DynamoMapperRepository dynamoMapperRepository;

    @Mock
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Mock
    ParameterStoreRepository parameterRepository;

    @InjectMocks
    Handler handler = new Handler() {
        {
            this.dynamoService = dynamoService;
            this.consumiService = consumiService;
            this.parameterDto = new ParameterStoreDto();
            this.parameterDto.setTabla("tablaTest");
            this.parameterDto.setUrlDynamo("https://api.mocked.com");
            this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        }
    };

    @Test
    void testRedirect_guardar_success() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("123");

        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setBody(Util.object2String(usuario));

        Map<String, String> headers = new HashMap<>();
        headers.put("servicio", "guardar");
        event.setHeaders(headers);

        when(dynamoService.saveUsuario(any(), any(), anyString())).thenReturn("Guardado exitosamente");

        // Act
        Object response = handler.redirect(event);

        // Assert
        assertEquals("Guardado exitosamente", response);
        verify(dynamoService).saveUsuario(any(), any(), eq("tablaTest"));
    }
}
