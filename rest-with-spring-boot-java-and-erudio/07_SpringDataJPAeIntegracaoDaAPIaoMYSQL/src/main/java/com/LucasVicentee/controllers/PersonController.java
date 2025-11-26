package com.LucasVicentee.controllers;

import com.LucasVicentee.PersonServices;
import com.LucasVicentee.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices(); o @AutoWired substitui a chamada antiga por uma nova, funcionando como a injeção de dependências

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public List<Person> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public Person findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public Person create(@RequestBody Person person) { // @RequestBody recupera os dados de um corpo para não retornar tudo nulo quando for adicionado algum dado
        return service.create(person);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public Person update(@RequestBody Person person) { // @RequestBody recupera os dados de um corpo para não retornar tudo nulo quando for adicionado algum dado
        return service.update(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Especifica o que será realizado dentro do RequestMapping, isto é util quando há mais de um tipo diferente de requisição de API
    public void delete(@PathVariable("id") String id) {
     service.delete(id);
    }
}
