package com.coom.ath.util;

import com.coom.ath.model.HeadersRq;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import static com.coom.ath.constants.HeadersEnum.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaderUtilTest {

    // Method correctly sets Content-Type header from HeadersRq to HttpPost
    @Test
    public void testHeaderUtil() {
        // Arrange
        HeaderUtil headerUtil = new HeaderUtil();
        HttpPost httpPost = new HttpPost("http://test.com");
        HeadersRq headers = new HeadersRq();
        String expectedContentType = "application/json";
        headers.setContentType(expectedContentType);

        // Act
        headerUtil.addEnrollmentHeaders(httpPost, headers);

        // Assert
        assertEquals(expectedContentType, httpPost.getFirstHeader(CONTENT_TYPE.getValue()).getValue());
    }
}