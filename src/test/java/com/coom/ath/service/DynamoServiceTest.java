package com.coom.ath.service;

import com.coom.ath.mapper.UsuarioMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.PersonaEntity;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.repository.DynamoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import static org.mockito.Mockito.*;

public class DynamoServiceTest {

    private DynamoRepository repositoryMock;
    private UsuarioMapper mapperMock;
    private DynamoService dynamoService;
    private DynamoDbEnhancedClient clientMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(DynamoRepository.class);
        mapperMock = mock(UsuarioMapper.class);
        clientMock = mock(DynamoDbEnhancedClient.class);

        // Creamos una subclase anónima para inyectar los mocks
        dynamoService = new DynamoService() {
            {
                this.repository = repositoryMock;
                this.usuarioMapper = mapperMock;
            }
        };
    }

    @Test
    public void testSaveUsuario_callsRepositorySaveWithMappedEntity() {
        // Arrange
        Usuario usuario = new Usuario(); // Este es el DTO que normalmente viene desde el handler
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        // Creamos una PersonaEntity simulada para evitar el NullPointerException
        PersonaEntity persona = new PersonaEntity();
        persona.setNombre("Juan"); // Simulamos que el nombre está presente
        usuarioEntity.setPersona(persona); // Asociamos la persona al usuario

        String tableName = "usuarios-test";

        // Cuando el mapper convierta el DTO a entidad, que devuelva la entidad simulada
        when(mapperMock.mappingUser(usuario)).thenReturn(usuarioEntity);

        // Act
        String result = dynamoService.saveUsuario(clientMock, usuario, tableName);

        // Assert
        verify(mapperMock).mappingUser(usuario);
        verify(repositoryMock).save(usuarioEntity, clientMock, tableName);
        assertEquals("Guardado exitosamente", result);
    }

    @Test
    public void testGetUsuario_returnsCorrectEntity() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setTipI("CC");
        usuario.setNumero("12345");

        String tableName = "usuarios-test";
        String id = "CC_12345";

        UsuarioEntity expectedEntity = new UsuarioEntity();
        PersonaEntity persona = new PersonaEntity();
        persona.setNombre("María");
        expectedEntity.setPersona(persona);

        when(repositoryMock.load(id, id, clientMock, tableName)).thenReturn(expectedEntity);

        // Act
        UsuarioEntity result = dynamoService.getUsuario(clientMock, usuario, tableName);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPersona());
        assertEquals("María", result.getPersona().getNombre());
        verify(repositoryMock).load(id, id, clientMock, tableName);
    }


}
