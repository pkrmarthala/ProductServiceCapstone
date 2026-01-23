package helpers;

import com.pkrmarthala.productservicecapstone.dtos.FakeStoreProductResponseDto;

public class FakeStoreProductDtoHelper {

    public static FakeStoreProductResponseDto createDummyFakeStoreProductResponseDto() {

        FakeStoreProductResponseDto dummyDto = new FakeStoreProductResponseDto();

        dummyDto.setId(1L);
        dummyDto.setTitle("Title");
        dummyDto.setDescription("Description");
        dummyDto.setPrice(10.0);
        dummyDto.setImage("image.url");
        dummyDto.setCategory("Category");

        return dummyDto;

    }

}
