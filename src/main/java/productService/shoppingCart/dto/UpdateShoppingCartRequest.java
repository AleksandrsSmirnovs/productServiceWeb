package productService.shoppingCart.dto;

import productService.product.dto.UpdateProductRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class UpdateShoppingCartRequest {

    @NotNull
    private long id;

    @NotEmpty
    private String owner_name;

    private List<UpdateProductRequest> productList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public List<UpdateProductRequest> getProductList() {
        return productList;
    }

    public void setProductList(List<UpdateProductRequest> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateShoppingCartRequest that = (UpdateShoppingCartRequest) o;
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
        return "UpdateShoppingCartRequest{" +
                "id=" + id +
                ", owner_name='" + owner_name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
