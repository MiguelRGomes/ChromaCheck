package br.com.java.tcc.application.products.resources;

import br.com.java.tcc.application.products.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "Product", description = "EndPoints Gerenciamento de Produtos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(
            summary = "Buscar por ID",
            description = "Ao executar o endpoint irá retornar os dados de um produto respectivo ao id informado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @Operation(
            summary = "Buscar Todos Produtos",
            description = "Ao executar o endpoint irá retornar os dados de todos produtos cadastrados")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @Operation(
            summary = "Registrar Produto",
            description = "Ao executar o endpoint irá cadastrar um produto a partir dos dados informado")
    @PostMapping
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest productRequest, UriComponentsBuilder uriBuilder){

        ProductResponse productResponse = productService.register(productRequest);

        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(productResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(productResponse);
    }

    @Operation(
            summary = "Atualizar por ID",
            description = "Ao executar o endpoint irá atualizar os dados de um produto respectivo ao id informado")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.update(id, productDTO));
    }

    @Operation(
            summary = "Deletar por ID",
            description = "Ao executar o endpoint irá deletar os dados de um produto respectivo ao id informado")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productService.delete(id));
    }
}
