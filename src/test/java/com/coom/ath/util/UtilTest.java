package com.coom.ath.util;

import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;
import io.micronaut.context.ApplicationContext;
import io.micronaut.serde.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UtilTest {

    private ObjectMapper objectMapperMock;

    @BeforeEach
    void setup() {
        objectMapperMock = mock(ObjectMapper.class);
    }

    @Test
    void testConvertToJson() throws IOException {
        Map<String, AttributeValue> dynamoImage = new HashMap<>();
        AttributeValue attr = new AttributeValue();
        attr.setS("Alexander");
        dynamoImage.put("nombre", attr);

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("nombre", "Alexander");

        ApplicationContext contextMock = mock(ApplicationContext.class);

        try (MockedStatic<ApplicationContext> contextStatic = mockStatic(ApplicationContext.class)) {
            contextStatic.when(ApplicationContext::run).thenReturn(contextMock);
            when(contextMock.getBean(ObjectMapper.class)).thenReturn(objectMapperMock);
            when(objectMapperMock.writeValueAsString(expectedMap)).thenReturn("{\"nombre\":\"Alexander\"}");

            Util.object2String(expectedMap);

            String result = Util.convertToJson(dynamoImage);
            assertEquals("{\"nombre\":\"Alexander\"}", result);
        }
    }

    @Test
    void testString2Object() throws IOException {
        String json = "{\"nombre\":\"Alexander\"}";

        class Persona {
            public String nombre;
        }

        Persona expectedPersona = new Persona();
        expectedPersona.nombre = "Alexander";

        ApplicationContext contextMock = mock(ApplicationContext.class);

        try (MockedStatic<ApplicationContext> contextStatic = mockStatic(ApplicationContext.class)) {
            contextStatic.when(ApplicationContext::run).thenReturn(contextMock);
            when(contextMock.getBean(ObjectMapper.class)).thenReturn(objectMapperMock);
            when(objectMapperMock.readValue(json, Persona.class)).thenReturn(expectedPersona);

            Persona result = (Persona) Util.string2object(json, Persona.class);
            assertEquals("Alexander", result.nombre);
        }
    }

    @Test
    void testObject2String() throws IOException {
        class Persona {
            public String nombre;
        }

        Persona persona = new Persona();
        persona.nombre = "Alexander";

        ApplicationContext contextMock = mock(ApplicationContext.class);

        try (MockedStatic<ApplicationContext> contextStatic = mockStatic(ApplicationContext.class)) {
            contextStatic.when(ApplicationContext::run).thenReturn(contextMock);
            when(contextMock.getBean(ObjectMapper.class)).thenReturn(objectMapperMock);
            when(objectMapperMock.writeValueAsString(persona)).thenReturn("{\"nombre\":\"Alexander\"}");

            String result = Util.object2String(persona);
            assertEquals("{\"nombre\":\"Alexander\"}", result);
        }
    }

    @Test
    void testStringUTF8() {
        String input = "Cattaleya 🧸";
        String result = Util.stringUTF8(input);
        assertEquals(input, result);
    }

    @Test
    void testCreateDatePattern() {
        String pattern = "yyyy-MM-dd";
        String result = Util.createDate(pattern);
        assertNotNull(result);
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    void testCreateDateDefault() {
        String result = Util.createDate();
        assertNotNull(result);
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
    }

    @Test
    void testObject2StringWithNulls() throws IOException {
        class Persona {
            public String nombre = null;
        }

        Persona persona = new Persona();

        ApplicationContext contextMock = mock(ApplicationContext.class);

        try (MockedStatic<ApplicationContext> contextStatic = mockStatic(ApplicationContext.class)) {
            contextStatic.when(ApplicationContext::run).thenReturn(contextMock);
            when(contextMock.getBean(ObjectMapper.class)).thenReturn(objectMapperMock);
            when(objectMapperMock.writeValueAsString(persona)).thenReturn("{\"nombre\":null}");

            String result = Util.object2StringWithNulls(persona);
            assertEquals("{\"nombre\":null}", result);
        }
    }

    @Test
    void testGetEnviromentVariable() {
        String key = "JAVA_HOME"; // O cualquier otra variable que sepas que existe
        String value = Util.getEnviromentVariable(key);
        assertNotNull(value);
    }
}