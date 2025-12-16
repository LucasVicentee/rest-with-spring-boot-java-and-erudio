package com.LucasVicentee.services;

import com.LucasVicentee.data.dto.BookDTO;
import com.LucasVicentee.exception.RequiredObjectIsNullException;
import com.LucasVicentee.model.Book;
import com.LucasVicentee.repository.BookRepository;
import com.LucasVicentee.unitetests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    BookServices service; // Injetando os Mocks no serviço

    @Mock
    BookRepository repository; // Injetando os Mocks no repositório

    @BeforeEach
    void setUp() { // Mockando os objetos de inputs
        input = new MockBook();
        MockitoAnnotations.openMocks(this); // Abrindo os Mocks do mockito (Importante)
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book)); // Procura pelo ID
        var result = service.findById(1L);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void create() {
        Book book = input.mockEntity(1);
        Book persisted = book;

        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn((persisted)); // Salva o usuário
        var result = service.create(dto);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreateWithNullBook() { // Testa o Book como nullo para verificar a exceção
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        Book persisted = book;

        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book)); // Verifica se tem o ID
        when(repository.save(book)).thenReturn((persisted)); // Retorna o usuário do respectivo ID

        var result = service.update(dto);

        assertNotNull(result); // Verificando se o resultado não é null
        assertNotNull(result.getId()); // Verificando se o Id não é null
        assertNotNull(result.getLinks()); // Verificando se os links não são null

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook() { // Testa o Book como nullo para verificar a exceção
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book)); // Verifica se tem o ID

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne); // Verificando se o bookOneado não é null
        assertNotNull(bookOne.getId()); // Verificando se o Id não é null
        assertNotNull(bookOne.getLinks()); // Verificando se os links não são null

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/2") && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/2") && link.getType().equals("DELETE")));

        assertEquals("Some Author1", bookOne.getAuthor());
        assertEquals(25D, bookOne.getPrice());
        assertEquals("Some Title1", bookOne.getTitle());
        assertNotNull(bookOne.getLaunchDate());

        var bookTwo = books.get(2);

        assertNotNull(bookTwo); // Verificando se o bookTwoado não é null
        assertNotNull(bookTwo.getId()); // Verificando se o Id não é null
        assertNotNull(bookTwo.getLinks()); // Verificando se os links não são null

        assertNotNull(bookTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("GET")));

        assertNotNull(bookTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(bookTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(bookTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(bookTwo.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Some Author2", bookTwo.getAuthor());
        assertEquals(25D, bookTwo.getPrice());
        assertEquals("Some Title2", bookTwo.getTitle());
        assertNotNull(bookTwo.getLaunchDate());

        var bookThree = books.get(3);

        assertNotNull(bookThree); // Verificando se o bookThreeado não é null
        assertNotNull(bookThree.getId()); // Verificando se o Id não é null
        assertNotNull(bookThree.getLinks()); // Verificando se os links não são null

        assertNotNull(bookThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/book/v1/3") && link.getType().equals("GET")));

        assertNotNull(bookThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("GET")));

        assertNotNull(bookThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("POST")));

        assertNotNull(bookThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("/api/book/v1") && link.getType().equals("PUT")));

        assertNotNull(bookThree.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") && link.getHref().endsWith("/api/book/v1/3") && link.getType().equals("DELETE")));

        assertEquals("Some Author3", bookThree.getAuthor());
        assertEquals(25D, bookThree.getPrice());
        assertEquals("Some Title3", bookThree.getTitle());
        assertNotNull(bookThree.getLaunchDate());
    }
}