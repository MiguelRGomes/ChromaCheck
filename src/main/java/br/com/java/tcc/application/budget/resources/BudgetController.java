package br.com.java.tcc.application.budget.resources;

import br.com.java.tcc.application.budget.BudgetService;
import br.com.java.tcc.application.people.resources.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget")
@Tag(name = "Budget", description = "EndPoints Gerenciamento de Orçamentos")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de um orçamento respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Orçamentos",
            description = "Ao executar o endpoint irá retornar os dados de todos orçamentos cadastrados")
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> findByCompanyEntity(@RequestParam(name = "companyId") Long companyId) {

        return ResponseEntity.ok().body(budgetService.findByCompanyEntity(companyId));
    }

    @Operation(
            summary = "Registrar Orçamento",
            description = "Ao executar o endpoint irá cadastrar um orçamento a partir dos dados informado")
    @PostMapping
    public ResponseEntity<BudgetResponse> register(@RequestBody BudgetRequest budgetRequest, UriComponentsBuilder uriBuilder){
        BudgetResponse budgetResponse = budgetService.register(budgetRequest);

        URI uri = uriBuilder.path("/budget/{id}").buildAndExpand(budgetResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um orçamento respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetResponse> update(@RequestBody BudgetRequest budgetDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetService.update(id, budgetDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um orçamento respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetService.delete(id));
    }
}
