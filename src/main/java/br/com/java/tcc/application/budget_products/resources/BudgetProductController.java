package br.com.java.tcc.application.budget_products.resources;

import br.com.java.tcc.application.budget_products.BudgetProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget-products")
@RequiredArgsConstructor
public class BudgetProductController {
    private final BudgetProductService budgetProductService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetProductResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BudgetProductResponse>> findAll(){

        return ResponseEntity.ok().body(budgetProductService.findAll());
    }

    @PostMapping
    public ResponseEntity<BudgetProductResponse> register(@RequestBody BudgetProductRequest budgetProductRequest, UriComponentsBuilder uriBuilder){
        BudgetProductResponse budgetProductResponse = budgetProductService.register(budgetProductRequest);

        URI uri = uriBuilder.path("/budget-products/{id}").buildAndExpand(budgetProductResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetProductResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetProductResponse> update(@RequestBody BudgetProductRequest budgetProductDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.update(id, budgetProductDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetProductService.delete(id));
    }
}
