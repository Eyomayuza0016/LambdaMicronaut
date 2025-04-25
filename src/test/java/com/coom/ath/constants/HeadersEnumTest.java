package com.coom.ath.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeadersEnumTest {

    @Test
    public void testContentTypeHeaderValue() {
        HeadersEnum header = HeadersEnum.CONTENT_TYPE;

        assertEquals("Content-Type", header.getValue());
    }
}
