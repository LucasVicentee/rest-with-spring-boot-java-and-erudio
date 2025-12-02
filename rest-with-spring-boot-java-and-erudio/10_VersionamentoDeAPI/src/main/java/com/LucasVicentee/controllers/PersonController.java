package com.LucasVicentee.controllers;

import com.LucasVicentee.data.dto.v1.PersonDTO;
import com.LucasVicentee.data.dto.v2.PersonDTOV2;
import com.LucasVicentee.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices(); o @AutoWired substitui a chamada antiga por uma nova, funcionando como a injeção de dependências

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public PersonDTO create(@RequestBody PersonDTO person) { // @RequestBody recupera os dados de um corpo para não retornar tudo nulo quando for adicionado algum dado
        return service.create(person);
    }

    @PostMapping(name = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) { // @RequestBody recupera os dados de um corpo para não retornar tudo nulo quando for adicionado algum dado
        return service.createV2(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public PersonDTO update(@RequestBody PersonDTO person) { // @RequestBody recupera os dados de um corpo para não retornar tudo nulo quando for adicionado algum dado
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}") // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build(); // Retorna o StatusCode correto que é o "204"
    }
}
