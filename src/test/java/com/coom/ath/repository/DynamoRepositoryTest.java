package com.coom.ath.repository;

import com.coom.ath.model.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static org.mockito.Mockito.*;

class DynamoRepositoryTest {

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<UsuarioEntity> dynamoDbTable;
    private DynamoRepository dynamoRepository;

    @BeforeEach
    void setUp() {
        dynamoDbEnhancedClient = mock(DynamoDbEnhancedClient.class);
        dynamoDbTable = mock(DynamoDbTable.class);

        // Mock explícito para manejar el tipo genérico
        when(dynamoDbEnhancedClient.table(anyString(), any(TableSchema.class)))
                .thenReturn((DynamoDbTable<UsuarioEntity>) dynamoDbTable);

        dynamoRepository = new DynamoRepository(); // Instancia sin parámetros
    }

    @Test
    void testSave() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(); // Reemplaza con datos reales si es necesario

        // Llamar al método a probar
        dynamoRepository.save(usuarioEntity, dynamoDbEnhancedClient, "TestTable");

        // Verificar que se llamó al método putItem con el objeto correcto
        verify(dynamoDbTable, times(1)).putItem(usuarioEntity);
    }
}