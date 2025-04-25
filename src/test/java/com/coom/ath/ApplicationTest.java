package com.coom.ath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    public void test() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Application.main(null);
        });

        // Puedes verificar el mensaje también si quieres
        assert exception.getMessage().contains("AWS_LAMBDA_RUNTIME_API");
    }

}