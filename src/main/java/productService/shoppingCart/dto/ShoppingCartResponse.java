package productService.shoppingCart.dto;

import productService.product.dto.ProductResponse;

import java.util.List;
import java.util.Objects;

public class ShoppingCartResponse {

    private long id;
    private String owner_name;
    private List<ProductResponse> productList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwnerName(String owner_name) {
        this.owner_name = owner_name;
    }

    public List<ProductResponse> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductResponse> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartResponse that = (ShoppingCartResponse) o;
        return id == that.id &&
                Objects.equals(owner_name, that.owner_name) &&
                Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner_name, productList);
    }

    @Override
    public String toString() {
        return "ShoppingCartResponse{" +
                "id=" + id +
                ", owner_name='" + owner_name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
