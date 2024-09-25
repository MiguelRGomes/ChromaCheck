package br.com.java.tcc.application.budget_products.resources;

import br.com.java.tcc.application.budget_products.BudgetProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget-products")
@Tag(name = "Budget Product", description = "EndPoints Gerenciamento de Produtos do Orçamento")
@RequiredArgsConstructor
public class BudgetProductController {
    private final BudgetProductService budgetProductService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados dos produtos do orçamento respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetProductResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Produtos do Orçamento",
            description = "Ao executar o endpoint irá retornar os dados de todos produtos dos orçamentos cadastrados")
    @GetMapping
    public ResponseEntity<List<BudgetProductResponse>> findByBudgetEntity(@RequestParam(name = "budgetId") Long budgetId) {
        return ResponseEntity.ok().body(budgetProductService.findByBudgetEntity(budgetId));
    }

    @Operation(
            summary = "Registrar Produto no Orçamento",
            description = "Ao executar o endpoint irá cadastrar um produto no orçamento a partir dos dados informado")
    @PostMapping
    public ResponseEntity<BudgetProductResponse> register(@RequestBody BudgetProductRequest budgetProductRequest, UriComponentsBuilder uriBuilder){
        BudgetProductResponse budgetProductResponse = budgetProductService.register(budgetProductRequest);

        URI uri = uriBuilder.path("/budget-products/{id}").buildAndExpand(budgetProductResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetProductResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um produto no orçamento respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetProductResponse> update(@RequestBody BudgetProductRequest budgetProductDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.update(id, budgetProductDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um produto do orçamento respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.delete(id));
    }
}
