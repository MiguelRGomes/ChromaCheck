package br.com.java.tcc.application.people.resources;

import br.com.java.tcc.application.people.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(personService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonResponse> register(@Valid @RequestBody PersonRequest personRequest, UriComponentsBuilder uriBuilder) {

        PersonResponse personResponse = personService.register(personRequest);

        URI uri = uriBuilder.path("/people/{id}").buildAndExpand(personResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(personResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonResponse> update(@RequestBody PersonRequest personDTO, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(personService.update(id,personDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(personService.delete(id));
    }

}