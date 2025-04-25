package com.coom.ath.service;

import com.coom.ath.model.EnrollmentRq;
import com.coom.ath.model.HeadersRq;
import com.coom.ath.model.HttpResponseWrapper;
import com.coom.ath.util.CertificadoUtil;
import com.coom.ath.util.HeaderUtil;
import com.coom.ath.util.Util;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumiServiceTest {

    private ConsumiService consumiService;
    private HeaderUtil headerUtilMock;
    private CloseableHttpClient httpClientMock;
    private CloseableHttpResponse httpResponseMock;

    @BeforeEach
    public void setup() {
        headerUtilMock = mock(HeaderUtil.class);
        httpClientMock = mock(CloseableHttpClient.class);
        httpResponseMock = mock(CloseableHttpResponse.class);

        consumiService = new ConsumiService() {
            {
                this.headerUtil = headerUtilMock;
            }
        };
    }

    @Test
    void testEnrollment() throws Exception {
        // Arrange
        ConsumiService service = new ConsumiService();

        EnrollmentRq req = new EnrollmentRq(); // ← rellena con lo necesario
        HeadersRq headers = new HeadersRq();   // ← rellena con lo necesario
        String uriConnection = "https://mocked-url";

        // Mocks
        CloseableHttpClient httpClientMock = mock(CloseableHttpClient.class);
        CloseableHttpResponse httpResponseMock = mock(CloseableHttpResponse.class);
        StatusLine statusLineMock = mock(StatusLine.class); // NUEVO mock

        when(httpClientMock.execute(any(HttpPost.class))).thenReturn(httpResponseMock);
        when(httpResponseMock.getEntity()).thenReturn(new StringEntity("respuesta", StandardCharsets.UTF_8));
        when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock); // ASIGNA statusLine
        when(statusLineMock.getStatusCode()).thenReturn(200); // ASIGNA statusCode

        // Hack para reemplazar CertificadoUtil.buildClient()
        try (MockedStatic<CertificadoUtil> mocked = Mockito.mockStatic(CertificadoUtil.class)) {
            mocked.when(CertificadoUtil::buildClient).thenReturn(httpClientMock);

            // Act
            HttpResponseWrapper response = service.enrollmentKeyService(req, headers, uriConnection);

            // Assert
            assertEquals("respuesta", response.getResponseBody());
            assertEquals(200, response.getStatusCode());
        }
    }

}
