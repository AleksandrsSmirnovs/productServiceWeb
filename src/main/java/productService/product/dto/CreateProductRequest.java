package productService.product.dto;

import productService.shoppingCart.dto.UpdateShoppingCartRequest;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CreateProductRequest {

    @NotEmpty
    @Size(min = 3, max = 32)
    private String name;

    private Integer category;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    @DecimalMin("0")
    @DecimalMax("100")
    private BigDecimal discount;

    @Size(max = 120)
    private String description;

    private List<UpdateShoppingCartRequest> shoppingCartList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public List<UpdateShoppingCartRequest> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<UpdateShoppingCartRequest> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductRequest that = (CreateProductRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(shoppingCartList, that.shoppingCartList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price, discount, description, shoppingCartList);
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", shoppingCartList=" + shoppingCartList +
                '}';
    }
}
