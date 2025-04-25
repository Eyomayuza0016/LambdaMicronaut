package com.coom.ath.repository;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DynamoMapperRepositoryTest {

    private AutoCloseable closeable;

    @Mock
    private DynamoDbEnhancedClient mockEnhancedClient;

    private DynamoMapperRepository repository;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        repository = new DynamoMapperRepository("us-east-1");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetClient_withMockedClient() {
        // Este test solo verifica que el método se puede llamar y no retorna null
        DynamoDbEnhancedClient client = repository.getClient();
        assertNotNull(client);
    }
}
