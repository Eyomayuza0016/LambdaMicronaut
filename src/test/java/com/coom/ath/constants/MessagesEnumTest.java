package com.coom.ath.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesEnumTest {

    @Test
    public void testSuccessResponseValues() {
        MessagesEnum success = MessagesEnum.SUCCESS_RESPONSE;

        assertEquals("2000", success.getCode());
        assertEquals("Success", success.getMessage());
        assertEquals(201, success.getHttpCode());
    }

    @Test
    public void testErrorConnectionSnsValues() {
        MessagesEnum errorSns = MessagesEnum.ERROR_CONNECTION_SNS;

        assertEquals("No se esta generando la conexion con el SNS", errorSns.getCode());
        assertEquals("Error de conexion SNS", errorSns.getMessage());
        assertEquals(502, errorSns.getHttpCode());
    }

    @Test
    public void testDefaultErrorResponseValues() {
        MessagesEnum error = MessagesEnum.DEFAULT_ERROR_RESPONSE;

        assertEquals("5001", error.getCode());
        assertEquals("Servicio no disponible, por favor intente nuevamente", error.getMessage());
        assertEquals(500, error.getHttpCode());
    }

    @Test
    public void testSuccessRedirectResponseValues() {
        MessagesEnum redirect = MessagesEnum.SUCCESS_REDIRECT_RESPONSE;

        assertEquals("3002", redirect.getCode());
        assertEquals("Success Redirect", redirect.getMessage());
        assertEquals(302, redirect.getHttpCode());
    }
}
