package productService.shoppingCart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import productService.shoppingCart.dto.CreateShoppingCartRequest;
import productService.shoppingCart.dto.ShoppingCartResponse;
import productService.shoppingCart.dto.UpdateShoppingCartRequest;
import productService.shoppingCart.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/shopping_carts")
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{id}")
    public ShoppingCartResponse findById(@PathVariable("id") long id) {
        return shoppingCartService.findById(id);
    }

    @GetMapping
    public List<ShoppingCartResponse> findAll() {
        return shoppingCartService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @Validated @RequestBody UpdateShoppingCartRequest request) {
        shoppingCartService.update(request, id);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> createShoppingCart(@Validated @RequestBody CreateShoppingCartRequest request, UriComponentsBuilder builder) {
        ShoppingCartResponse response = shoppingCartService.save(request);
        return ResponseEntity.created(
                builder.path("/shopping_carts/{id}").buildAndExpand(response.getId()).toUri()).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        shoppingCartService.deleteShoppingCartById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/{productId}")
    public void addProductToCart(@PathVariable("id") long id, @PathVariable("productId") long productId) {
        shoppingCartService.addProductToCart(id, productId);
    }
}
