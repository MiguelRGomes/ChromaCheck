package br.com.java.tcc.application.company.resources;

import br.com.java.tcc.application.company.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
@Tag(name = "Company", description = "EndPoints Gerenciamento de Empresas")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de uma empresa respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(companyService.findById(id));
    }

    @Operation(
            summary = "Buscar empresa por CNPJ e senha",
            description = "Ao executar o endpoint irá retornar os dados de uma empresa com base no CNPJ e senha informados"
    )
    @GetMapping("/login")
    public ResponseEntity<CompanyResponse> findByCnpjAndPassword(
            @RequestParam(name = "cnpj") String cnpj,
            @RequestParam(name = "password") String password
    ) {
        // Implementar a lógica para buscar a empresa com base no CNPJ e na senha
        CompanyResponse companyResponse = companyService.findByCnpjAndPassword(cnpj, password);

        if (companyResponse != null) {
            return ResponseEntity.ok().body(companyResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Operation(
            summary = "Buscar Todas Empresas",
            description = "Ao executar o endpoint irá retornar os dados de todas empresas cadastradas")
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        return ResponseEntity.ok().body(companyService.findAll());
    }

    @Operation(
            summary = "Registrar Empresa",
            description = "Ao executar o endpoint irá cadastrar uma empresa a partir dos dados informado")
    @PostMapping
    public ResponseEntity<CompanyResponse> register(@RequestBody CompanyRequest companyRequest, UriComponentsBuilder uriBuilder){

        CompanyResponse companyResponse = companyService.register(companyRequest);

        URI uri = uriBuilder.path("/company/{id}").buildAndExpand(companyResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(companyResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de uma empresa respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> update(@RequestBody CompanyRequest companyDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(companyService.update(id, companyDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de uma empresa respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(companyService.delete(id));
    }
}
