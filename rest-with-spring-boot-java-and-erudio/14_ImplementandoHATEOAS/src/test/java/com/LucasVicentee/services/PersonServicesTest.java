package com.LucasVicentee.services;

import com.LucasVicentee.data.dto.PersonDTO;
import com.LucasVicentee.exception.RequiredObjectIsNullException;
import com.LucasVicentee.model.Person;
import com.LucasVicentee.repository.PersonRepository;
import com.LucasVicentee.unitetests.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    PersonServices service; // Injetando os Mocks no serviço

    @Mock
    PersonRepository repository; // Injetando os Mocks no repositório

    @BeforeEach
    void setUp() { // Mockando os objetos de inputs
        input = new MockPerson();
        MockitoAnnotations.openMocks(this); // Abrindo os Mocks do mockito (Importante)
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person)); // Procura pelo ID
        var result = service.findById(1L);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;

        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn((persisted)); // Salva o usuário
        var result = service.create(dto);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() { // Testa o Person como nullo para verificar a exceção
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        Person persisted = person;

        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person)); // Verifica se tem o ID
        when(repository.save(person)).thenReturn((persisted)); // Retorna o usuário do respectivo ID

        var result = service.update(dto);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() { // Testa o Person como nullo para verificar a exceção
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person)); // Verifica se tem o ID

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne); // Verificando se o personOneado não é null
        assertNotNull(personOne.getId()); // Verificando se o Id não é null
        assertNotNull(personOne.getLinks()); // Verificando se os links não são null

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/2") && link.getType().equals("GET")));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/2") && link.getType().equals("DELETE")));

        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personTwo = people.get(2);

        assertNotNull(personTwo); // Verificando se o personTwoado não é null
        assertNotNull(personTwo.getId()); // Verificando se o Id não é null
        assertNotNull(personTwo.getLinks()); // Verificando se os links não são null

        assertNotNull(personTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));

        assertNotNull(personTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(personTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(personTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(personTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Address Test2", personTwo.getAddress());
        assertEquals("First Name Test2", personTwo.getFirstName());
        assertEquals("Last Name Test2", personTwo.getLastName());
        assertEquals("Male", personTwo.getGender());

        var personThree = people.get(3);

        assertNotNull(personThree); // Verificando se o personThreeado não é null
        assertNotNull(personThree.getId()); // Verificando se o Id não é null
        assertNotNull(personThree.getLinks()); // Verificando se os links não são null

        assertNotNull(personThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/person/v1/3") && link.getType().equals("GET")));

        assertNotNull(personThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));

        assertNotNull(personThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

        assertNotNull(personThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

        assertNotNull(personThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/person/v1/3") && link.getType().equals("DELETE")));

        assertEquals("Address Test3", personThree.getAddress());
        assertEquals("First Name Test3", personThree.getFirstName());
        assertEquals("Last Name Test3", personThree.getLastName());
        assertEquals("Female", personThree.getGender());
    }
}