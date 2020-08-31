package productService.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import productService.converters.DtoEntityConverter;
import productService.exceptions.ShoppingCartNotFoundException;
import productService.product.domain.ProductEntity;
import productService.product.dto.CreateProductRequest;
import productService.product.dto.ProductResponse;
import productService.product.dto.UpdateProductRequest;
import productService.product.repository.ProductRepository;
import productService.exceptions.ProductNotFoundException;
import productService.shoppingCart.domain.ShoppingCartEntity;
import productService.shoppingCart.repository.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final DtoEntityConverter converter;

    public ProductService(ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, DtoEntityConverter converter) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.converter = converter;
    }

    public ProductResponse save(CreateProductRequest request) {
        return converter.productToResponse(productRepository.save(converter.ProductCreateRequestToEntity(request)));
    }

    public ProductResponse findById(long id) {
        return productRepository.findById(id)
                .map(converter::productToResponse)
                .orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public void deleteProductById(long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(converter::productToResponse).collect(Collectors.toList());
    }

    public void update(UpdateProductRequest request, long id) {
        productRepository.save(converter.ProductUpdateRequestToEntity(request, id));
    }

    public void addProductToCart(long id, long shoppingCartId) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shoppingCartId).orElseThrow(()-> new ShoppingCartNotFoundException("Shopping cart with id " + id + " not found"));
        entity.getShoppingCarts().add(shoppingCartEntity);
        productRepository.save(entity);
    }

}
