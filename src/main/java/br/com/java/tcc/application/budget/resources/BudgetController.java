package br.com.java.tcc.application.budget.resources;

import br.com.java.tcc.application.budget.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponse>> findAll(){

        return ResponseEntity.ok().body(budgetService.findAll());
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> register(@RequestBody BudgetRequest budgetRequest, UriComponentsBuilder uriBuilder){
        BudgetResponse budgetResponse = budgetService.register(budgetRequest);

        URI uri = uriBuilder.path("/budget/{id}").buildAndExpand(budgetResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetResponse> update(@RequestBody BudgetRequest budgetDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetService.update(id, budgetDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetService.delete(id));
    }
}
