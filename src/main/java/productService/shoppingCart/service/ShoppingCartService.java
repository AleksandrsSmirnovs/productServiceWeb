package productService.shoppingCart.service;

import org.springframework.stereotype.Service;
import productService.product.domain.ProductEntity;
import productService.shoppingCart.domain.ShoppingCartEntity;
import productService.shoppingCart.dto.CreateShoppingCartRequest;
import productService.shoppingCart.dto.ShoppingCartResponse;
import productService.shoppingCart.dto.UpdateShoppingCartRequest;
import productService.exceptions.ProductNotFoundException;
import productService.exceptions.ShoppingCartNotFoundException;
import productService.converters.DtoEntityConverter;
import productService.product.repository.ProductRepository;
import productService.shoppingCart.repository.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final DtoEntityConverter converter;

    public ShoppingCartService(ShoppingCartRepository repository, ProductRepository productRepository, DtoEntityConverter converter) {
        this.shoppingCartRepository = repository;
        this.productRepository = productRepository;
        this.converter = converter;
    }

    public ShoppingCartResponse save(CreateShoppingCartRequest request) {
        return converter.shoppingCartToResponse(shoppingCartRepository.save(converter.ShoppingCartCreateRequestToEntity(request)));
    }

    public ShoppingCartResponse findById(long id) {
        return shoppingCartRepository.findById(id)
                .map(converter::shoppingCartToResponse)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found"));
    }

    public void deleteShoppingCartById(long id) {
        if (!shoppingCartRepository.existsById(id)) {
            throw new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found");
        }
        shoppingCartRepository.deleteById(id);
    }

    public List<ShoppingCartResponse> findAll() {
        return shoppingCartRepository.findAll().stream().map(converter::shoppingCartToResponse).collect(Collectors.toList());
    }

    public void update(UpdateShoppingCartRequest request, long id) {
        ShoppingCartEntity entity = shoppingCartRepository.findById(id).orElseThrow(()-> new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found"));
        entity.setProducts(request.getProductList().stream().map(e -> converter.ProductUpdateRequestToEntity(e, e.getId())).collect(Collectors.toList()));
        shoppingCartRepository.save(converter.ShoppingCartUpdateRequestToEntity(request, id));
    }

    public void addProductToCart(long id, long productId) {
        ShoppingCartEntity entity = shoppingCartRepository.findById(id).orElseThrow(() -> new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found"));
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
        entity.getProducts().add(productEntity);
        shoppingCartRepository.save(entity);
    }

}
