package productService.product.domain;

import productService.shoppingCart.domain.ShoppingCartEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private Integer category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_shopping_cart",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id")
    )
    private List<ShoppingCartEntity> shoppingCarts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ShoppingCartEntity> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCartEntity> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity entity = (ProductEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(category, entity.category) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(price, entity.price) &&
                Objects.equals(discount, entity.discount) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(shoppingCarts, entity.shoppingCarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, price, discount, description, shoppingCarts);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", shoppingCarts=" + shoppingCarts +
                '}';
    }
}
