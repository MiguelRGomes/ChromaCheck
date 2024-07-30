package br.com.java.tcc.application.services.resources;

import br.com.java.tcc.application.services.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
@Tag(name = "Services", description = "EndPoints Gerenciamento de Serviços")
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de um serviço respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicesResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(servicesService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Serviços",
            description = "Ao executar o endpoint irá retornar os dados de todos serviços cadastrados")
    @GetMapping
    public ResponseEntity<List<ServicesResponse>> findAll(){
        return ResponseEntity.ok().body(servicesService.findAll());
    }

    @Operation(
            summary = "Registrar Serviço",
            description = "Ao executar o endpoint irá cadastrar um serviço a partir dos dados informado")
    @PostMapping
    public ResponseEntity<ServicesResponse> register(@RequestBody ServicesRequest servicesRequest, UriComponentsBuilder uriBuilder){

        ServicesResponse servicesResponse = servicesService.register(servicesRequest);

        URI uri = uriBuilder.path("/services/{id}").buildAndExpand(servicesResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(servicesResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um serviço respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicesResponse> update(@RequestBody ServicesRequest servicesDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(servicesService.update(id, servicesDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um serviço respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(servicesService.delete(id));
    }
}
