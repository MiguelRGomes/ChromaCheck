package br.com.java.tcc.application.prices.resources;

import br.com.java.tcc.application.prices.PricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "prices")
@RequiredArgsConstructor
public class PricesController {
    private final PricesService pricesService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PricesResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pricesService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PricesResponse>> findAll() {
        return ResponseEntity.ok().body(pricesService.findAll());
    }

    @PostMapping
    public ResponseEntity<PricesResponse> register(@RequestBody PricesRequest pricesRequest, UriComponentsBuilder uriBuilder){
        PricesResponse pricesResponse = pricesService.register(pricesRequest);

        URI uri = uriBuilder.path("/prices/{id}").buildAndExpand(pricesResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(pricesResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PricesResponse> update(@RequestBody PricesRequest pricesDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pricesService.update(id, pricesDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return  ResponseEntity.ok().body(pricesService.delete(id));
    }
}
