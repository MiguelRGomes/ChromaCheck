package br.com.java.tcc.application.services.resources;

import br.com.java.tcc.application.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicesResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(servicesService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServicesResponse>> findAll(){
        return ResponseEntity.ok().body(servicesService.findAll());
    }

    @PostMapping
    public ResponseEntity<ServicesResponse> register(@RequestBody ServicesRequest servicesRequest, UriComponentsBuilder uriBuilder){

        ServicesResponse servicesResponse = servicesService.register(servicesRequest);

        URI uri = uriBuilder.path("/services/{id}").buildAndExpand(servicesResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(servicesResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicesResponse> update(@RequestBody ServicesRequest servicesDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(servicesService.update(id, servicesDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(servicesService.delete(id));
    }
}
