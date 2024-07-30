package br.com.java.tcc.application.adresses.resources;

import br.com.java.tcc.application.adresses.AdressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/adress")
@Tag(name = "Adress", description = "EndPoints Gerenciamento de Endereços")
@RequiredArgsConstructor
public class AdressController {
    private final AdressService adressService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de um endereço respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AdressResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(adressService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Endereços",
            description = "Ao executar o endpoint irá retornar os dados de todos endereços cadastrados")
    @GetMapping
    public ResponseEntity<List<AdressResponse>> findAll(){

        return ResponseEntity.ok().body(adressService.findAll());
    }

    @Operation(
            summary = "Registrar Endereço",
            description = "Ao executar o endpoint irá cadastrar um endereço a partir dos dados informado")
    @PostMapping
    public ResponseEntity<AdressResponse> register(@RequestBody AdressRequest adressRequest, UriComponentsBuilder uriBuilder){
        AdressResponse adressResponse = adressService.register(adressRequest);

        URI uri = uriBuilder.path("/adress/{id}").buildAndExpand(adressResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(adressResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um endereço respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AdressResponse> update(@RequestBody AdressRequest adressDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(adressService.update(id, adressDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um endereço respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(adressService.delete(id));
    }
}
