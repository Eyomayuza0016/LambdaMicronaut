package com.coom.ath.util;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathRequest;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathResponse;
import software.amazon.awssdk.services.ssm.paginators.GetParametersByPathIterable;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ParameterStoreUtilTest {

    // Handles empty path parameter
    @Test
    public void testParameterStrore() {
        // Arrange
        String emptyPath = "";
        SsmClient mockSsmClient = mock(SsmClient.class);

        try (MockedStatic<SsmClient> mockedStatic = mockStatic(SsmClient.class)) {
            mockedStatic.when(SsmClient::create).thenReturn(mockSsmClient);

            GetParametersByPathRequest expectedRequest = GetParametersByPathRequest.builder()
                    .path(emptyPath)
                    .recursive(true)
                    .build();

            GetParametersByPathIterable mockIterable = mock(GetParametersByPathIterable.class);
            when(mockSsmClient.getParametersByPathPaginator(any(GetParametersByPathRequest.class)))
                    .thenReturn(mockIterable);

            // Empty response
            GetParametersByPathResponse mockResponse = GetParametersByPathResponse.builder()
                    .parameters(Collections.emptyList())
                    .build();

            when(mockIterable.stream()).thenReturn(Stream.of(mockResponse));

            // Act
            Map<String, String> result = ParameterStoreUtil.getParameters(emptyPath);

        }
    }

}
