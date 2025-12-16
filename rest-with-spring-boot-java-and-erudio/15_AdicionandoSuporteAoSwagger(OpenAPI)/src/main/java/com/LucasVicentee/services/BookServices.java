package com.LucasVicentee.services;

import com.LucasVicentee.controllers.BookController;
import com.LucasVicentee.data.dto.BookDTO;
import com.LucasVicentee.exception.RequiredObjectIsNullException;
import com.LucasVicentee.exception.ResourceNotFoundException;
import com.LucasVicentee.model.Book;
import com.LucasVicentee.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.LucasVicentee.mapper.ObjectMapper.parseListObjects;
import static com.LucasVicentee.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {
        logger.info("Finding all Books!");

        var persons = parseListObjects(repository.findAll(), BookDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Records Found For This ID!"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Book!");

        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book!");

        Book entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No Records Found For This ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one Book!");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Records Found For This ID!"));
        repository.delete(entity);

        // Não precisa do método addHateoasLinks, pois não retorna nada ao usuário
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
