package productService.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import productService.product.dto.CreateProductRequest;
import productService.product.dto.ProductResponse;
import productService.product.dto.UpdateProductRequest;
import productService.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @Validated @RequestBody UpdateProductRequest request) {
        productService.update(request, id);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Validated @RequestBody CreateProductRequest request, UriComponentsBuilder builder) {
        ProductResponse response = productService.save(request);
        return ResponseEntity.created(
                builder.path("/products/{id}").buildAndExpand(response.getId()).toUri()).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.deleteProductById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/{shoppingCartId}")
    public void addProductToCart(@PathVariable("id") long id, @PathVariable("shoppingCartId") long shoppingCartId) {
        productService.addProductToCart(id, shoppingCartId);
    }
}
