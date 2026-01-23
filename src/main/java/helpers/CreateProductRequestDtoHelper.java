package helpers;

import com.pkrmarthala.productservicecapstone.dtos.CreateProductRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDtoHelper {

    public static CreateProductRequestDto createProductRequestDto() {

        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto();

        createProductRequestDto.setId(1L);
        createProductRequestDto.setName("TestName_1");
        createProductRequestDto.setDescription("TestDescription_1");
        createProductRequestDto.setPrice(10.0);
        createProductRequestDto.setQuantity(4);
        createProductRequestDto.setImageUrl("TestImage_1.url");
        createProductRequestDto.setCategory("TestCategoryName_1");

        return createProductRequestDto;
    }

}
