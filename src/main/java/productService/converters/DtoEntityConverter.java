package productService.converters;

import org.springframework.stereotype.Component;
import productService.product.domain.ProductEntity;
import productService.shoppingCart.domain.ShoppingCartEntity;
import productService.product.dto.CreateProductRequest;
import productService.product.dto.ProductResponse;
import productService.product.dto.UpdateProductRequest;
import productService.shoppingCart.dto.CreateShoppingCartRequest;
import productService.shoppingCart.dto.ShoppingCartResponse;
import productService.shoppingCart.dto.UpdateShoppingCartRequest;
import productService.exceptions.ProductNotFoundException;
import productService.exceptions.ShoppingCartNotFoundException;
import productService.product.repository.ProductRepository;
import productService.shoppingCart.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class DtoEntityConverter {

    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public DtoEntityConverter(ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ProductEntity ProductCreateRequestToEntity(CreateProductRequest request) {
        ProductEntity entity = new ProductEntity();
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setDiscount(request.getDiscount());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setShoppingCarts(request.getShoppingCartList().stream().map(e -> ShoppingCartUpdateRequestToEntity(e, e.getId())).collect(Collectors.toList()));
        return entity;
    }

    public ProductEntity ProductUpdateRequestToEntity(UpdateProductRequest request, long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setDiscount(request.getDiscount());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setShoppingCarts(request.getShoppingCartList().stream().map(e -> ShoppingCartUpdateRequestToEntity(e, e.getId())).collect(Collectors.toList()));
        return entity;
    }

    public ProductResponse productToResponse(ProductEntity entity) {
        ProductResponse response = new ProductResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPrice(entity.getPrice());
        response.setDiscount(entity.getDiscount());
        response.setCategory(entity.getCategory());
        response.setDescription(entity.getDescription());
        response.setShoppingCartIdList(entity.getShoppingCarts().stream().map(ShoppingCartEntity::getId).collect(Collectors.toList()));
        response.setActualPrice(entity.getDiscount() == null ? entity.getPrice() : entity.getPrice().subtract(entity.getPrice().multiply(entity.getDiscount()).multiply(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.HALF_EVEN));
        return response;
    }

    public ShoppingCartResponse shoppingCartToResponse(ShoppingCartEntity entity) {
        ShoppingCartResponse response = new ShoppingCartResponse();
        response.setId(entity.getId());
        response.setOwnerName(entity.getOwnerName());
        response.setProductList(entity.getProducts().stream().map(this::productToResponse).collect(Collectors.toList()));
        return response;
    }

    public ShoppingCartEntity ShoppingCartCreateRequestToEntity(CreateShoppingCartRequest request) {
        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setOwnerName(request.getOwner_name());
        entity.setProducts(new ArrayList<>());
        return entity;
    }

    public ShoppingCartEntity ShoppingCartUpdateRequestToEntity(UpdateShoppingCartRequest request, long id) {
        ShoppingCartEntity entity = shoppingCartRepository.findById(id).orElseThrow(()-> new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found"));
        entity.setOwnerName(request.getOwner_name());
        entity.setProducts(request.getProductList().stream().map(e -> ProductUpdateRequestToEntity(e, e.getId())).collect(Collectors.toList()));
        return entity;
    }
}
