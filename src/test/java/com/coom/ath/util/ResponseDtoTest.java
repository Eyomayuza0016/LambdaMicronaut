package com.coom.ath.util;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseDtoTest {

    // Create a ResponseDto with all fields populated
    @Test
    public void testResponseDto_() {
        // Arrange
        String statusCode = "200";
        String message = "Success";
        List<String> errorDetail = Arrays.asList("Error 1", "Error 2");
        Object result = new HashMap<String, String>() {{ put("key", "value"); }};

        // Act
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(statusCode);
        responseDto.setMessage(message);
        responseDto.setErrorDetail(errorDetail);
        responseDto.setResult(result);

        // Assert
        assertEquals(statusCode, responseDto.getStatusCode());
        assertEquals(message, responseDto.getMessage());
        assertEquals(errorDetail, responseDto.getErrorDetail());
        assertEquals(result, responseDto.getResult());
    }
}
