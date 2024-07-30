package br.com.java.tcc.application.budget_services.resources;

import br.com.java.tcc.application.budget_services.BudgetServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget-services")
@Tag(name = "Budget Service", description = "EndPoints Gerenciamento de Serviços do Orçamento")
@RequiredArgsConstructor
public class BudgetServiceController {
    private final BudgetServiceService budgetServiceService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados dos serviços do orçamento respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetServiceResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Serviços do Orçamento",
            description = "Ao executar o endpoint irá retornar os dados de todos serviços dos orçamentos cadastrados")
    @GetMapping
    public ResponseEntity<List<BudgetServiceResponse>> findAll(){

        return ResponseEntity.ok().body(budgetServiceService.findAll());
    }

    @Operation(
            summary = "Registrar Serviço no Orçamento",
            description = "Ao executar o endpoint irá cadastrar um serviço no orçamento a partir dos dados informado")
    @PostMapping
    public ResponseEntity<BudgetServiceResponse> register(@RequestBody BudgetServiceRequest budgetServiceRequest, UriComponentsBuilder uriBuilder){
        BudgetServiceResponse budgetServiceResponse = budgetServiceService.register(budgetServiceRequest);

        URI uri = uriBuilder.path("/budget-services/{id}").buildAndExpand(budgetServiceResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetServiceResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um serviço no orçamento respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetServiceResponse> update(@RequestBody BudgetServiceRequest budgetServiceDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.update(id, budgetServiceDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um serviço do orçamento respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.delete(id));
    }
}
