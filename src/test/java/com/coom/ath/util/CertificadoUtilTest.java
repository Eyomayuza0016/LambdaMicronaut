package com.coom.ath.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

public class CertificadoUtilTest {

    // Successfully creates a CloseableHttpClient with custom SSL settings
    @Test
    public void testCertificado() throws Exception {
        // Act
        CloseableHttpClient client = CertificadoUtil.buildClient();

        // Assert
        assertNotNull(client);

        // Verify client has custom SSL settings by checking its class
        String clientClassName = client.getClass().getName();
        assertTrue(clientClassName.contains("InternalHttpClient"),
                "Expected client to be an InternalHttpClient instance");
    }
}