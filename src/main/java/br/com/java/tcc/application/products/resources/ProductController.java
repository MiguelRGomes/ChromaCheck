package br.com.java.tcc.application.products.resources;

import br.com.java.tcc.application.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest productRequest, UriComponentsBuilder uriBuilder){

        ProductResponse productResponse = productService.register(productRequest);

        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(productResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(productResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.update(id, productDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productService.delete(id));
    }
}
