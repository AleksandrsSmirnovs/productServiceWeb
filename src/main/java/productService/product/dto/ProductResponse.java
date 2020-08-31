package productService.product.dto;

import productService.shoppingCart.dto.ShoppingCartResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductResponse {

    private Long id;
    private Integer category;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String description;
    private List<Long> shoppingCartIdList;
    private BigDecimal actualPrice;

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

    public List<Long> getShoppingCartIdList() {
        return shoppingCartIdList;
    }

    public void setShoppingCartIdList(List<Long> shoppingCartIdList) {
        this.shoppingCartIdList = shoppingCartIdList;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(shoppingCartIdList, that.shoppingCartIdList) &&
                Objects.equals(actualPrice, that.actualPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, price, discount, description, shoppingCartIdList, actualPrice);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", shoppingCartIdList=" + shoppingCartIdList +
                ", actualPrice=" + actualPrice +
                '}';
    }
}
