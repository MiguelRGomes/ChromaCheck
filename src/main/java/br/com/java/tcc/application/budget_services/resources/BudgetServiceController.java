package br.com.java.tcc.application.budget_services.resources;

import br.com.java.tcc.application.budget_services.BudgetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/budget-services")
@RequiredArgsConstructor
public class BudgetServiceController {
    private final BudgetServiceService budgetServiceService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BudgetServiceResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BudgetServiceResponse>> findAll(){

        return ResponseEntity.ok().body(budgetServiceService.findAll());
    }

    @PostMapping
    public ResponseEntity<BudgetServiceResponse> register(@RequestBody BudgetServiceRequest budgetServiceRequest, UriComponentsBuilder uriBuilder){
        BudgetServiceResponse budgetServiceResponse = budgetServiceService.register(budgetServiceRequest);

        URI uri = uriBuilder.path("/budget-services/{id}").buildAndExpand(budgetServiceResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(budgetServiceResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BudgetServiceResponse> update(@RequestBody BudgetServiceRequest budgetServiceDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.update(id, budgetServiceDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(budgetServiceService.delete(id));
    }
}
