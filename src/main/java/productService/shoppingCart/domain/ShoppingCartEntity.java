package productService.shoppingCart.domain;

import productService.product.domain.ProductEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "owner_name")
    private String ownerName;

    @ManyToMany
    @JoinTable(
            name = "product_shopping_cart",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartEntity entity = (ShoppingCartEntity) o;
        return id == entity.id &&
                Objects.equals(ownerName, entity.ownerName) &&
                Objects.equals(products, entity.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, products);
    }

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", products=" + products +
                '}';
    }
}
