package helpers;

import com.pkrmarthala.productservicecapstone.models.Category;
import com.pkrmarthala.productservicecapstone.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductHelper {

    public static Product createDummyProductForTest(Long id) {

        Product dummyProduct = new Product();
        dummyProduct.setId(id);
        dummyProduct.setName("TestName_" + id);
        dummyProduct.setDescription("TestDescription_" + id);
        dummyProduct.setPrice(10.0 * id);
        dummyProduct.setQuantity(id.intValue() + 3);
        dummyProduct.setImageUrl("TestImage_"+ id + ".url");

        Category dummyCategory = new Category();
        dummyCategory.setId(id);
        dummyCategory.setName("TestCategoryName_" + id);
        dummyCategory.setDescription("TestCategoryDescription_" + id);

        dummyProduct.setCategory(dummyCategory);

        return dummyProduct;

    }

    public static List<Product> createDummyProductListForTest(int count) {

        List<Product> dummyProductList = new ArrayList<>();

        for(long i = 1; i <= count; i++) {
           dummyProductList.add(createDummyProductForTest(i));
        }

        return dummyProductList;
    }

//    public static List<Product> createDummyProductListForTest() {
//
//        List<Product> dummyProductList = new ArrayList<>();
//
//        for(long i = 1; i <= 5; i++) {
//            dummyProductList.add(createDummyProductForTest(i));
//        }
//
//        return dummyProductList;
//    }

}
