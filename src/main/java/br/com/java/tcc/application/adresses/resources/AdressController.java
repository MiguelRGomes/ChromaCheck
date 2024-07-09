package br.com.java.tcc.application.adresses.resources;

import br.com.java.tcc.application.adresses.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/adress")
@RequiredArgsConstructor
public class AdressController {
    private final AdressService adressService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdressResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(adressService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdressResponse>> findAll(){

        return ResponseEntity.ok().body(adressService.findAll());
    }

    @PostMapping
    public ResponseEntity<AdressResponse> register(@RequestBody AdressRequest adressRequest, UriComponentsBuilder uriBuilder){
        AdressResponse adressResponse = adressService.register(adressRequest);

        URI uri = uriBuilder.path("/adress/{id}").buildAndExpand(adressResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(adressResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdressResponse> update(@RequestBody AdressRequest adressDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(adressService.update(id, adressDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(adressService.delete(id));
    }
}
