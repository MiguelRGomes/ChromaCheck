package br.com.java.tcc.application.prices.resources;

import br.com.java.tcc.application.prices.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "prices")
@Tag(name = "Prices", description = "EndPoints Gerenciamento de Preços por Medidas")
@RequiredArgsConstructor
public class PricesController {
    private final PricesService pricesService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de um preço por medida respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PricesResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pricesService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Preços por Medidas",
            description = "Ao executar o endpoint irá retornar os dados de todos preços por medidas cadastrados")
    @GetMapping
    public ResponseEntity<List<PricesResponse>> findAll() {
        return ResponseEntity.ok().body(pricesService.findAll());
    }

    @Operation(
            summary = "Registrar Preço por Medida",
            description = "Ao executar o endpoint irá cadastrar um preço por medida a partir dos dados informado")
    @PostMapping
    public ResponseEntity<PricesResponse> register(@RequestBody PricesRequest pricesRequest, UriComponentsBuilder uriBuilder){
        PricesResponse pricesResponse = pricesService.register(pricesRequest);

        URI uri = uriBuilder.path("/prices/{id}").buildAndExpand(pricesResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(pricesResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um preço por medida respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PricesResponse> update(@RequestBody PricesRequest pricesDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pricesService.update(id, pricesDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um preço por medida respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return  ResponseEntity.ok().body(pricesService.delete(id));
    }
}
