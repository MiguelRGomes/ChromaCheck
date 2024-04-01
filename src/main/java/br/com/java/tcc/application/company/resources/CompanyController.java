package br.com.java.tcc.application.company.resources;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(companyService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        return ResponseEntity.ok().body(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> register(@RequestBody CompanyRequest companyRequest, UriComponentsBuilder uriBuilder){

        CompanyResponse companyResponse = companyService.register(companyRequest);

        URI uri = uriBuilder.path("/company/{id}").buildAndExpand(companyResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(companyResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> update(@RequestBody CompanyRequest companyDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(companyService.update(id, companyDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(companyService.delete(id));
    }
}
