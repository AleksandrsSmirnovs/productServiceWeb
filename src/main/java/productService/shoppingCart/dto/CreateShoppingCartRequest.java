package productService.shoppingCart.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class CreateShoppingCartRequest {

    @NotEmpty
    private String owner_name;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateShoppingCartRequest that = (CreateShoppingCartRequest) o;
        return Objects.equals(owner_name, that.owner_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner_name);
    }

    @Override
    public String toString() {
        return "CreateShoppingCartRequest{" +
                "owner_name='" + owner_name + '\'' +
                '}';
    }
}
