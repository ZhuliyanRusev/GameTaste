package com.example.gametaste.service;

import com.example.gametaste.model.entity.Category;
import com.example.gametaste.model.entity.Merchandise;
import com.example.gametaste.model.entity.News;
import com.example.gametaste.model.entity.enums.MerchandiseEnum;
import com.example.gametaste.model.service.MerchandiseServiceModel;
import com.example.gametaste.model.view.MerchandiseViewModel;
import com.example.gametaste.repository.MerchandiseRepository;
import com.example.gametaste.service.impl.MerchandiseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MerchandiseServiceImplTest {

    private final String MERCHANDISE_IMAGE_URL = "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61+yI8cp94L._AC_SL1500_.jpg";
    private final String MERCHANDISE_TITLE = "Some title in here";

    private static final String MERCHANDISE_DATE = "2023-04-15";

    private MerchandiseServiceImpl testMSI;
    @Mock
    private MerchandiseRepository mockMerchandiseRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private Merchandise testMerchandise;
    private Merchandise secondTestMerchandise;
    private Merchandise thirdTestMerchandise;

    @BeforeEach
    void setup() {
        testMSI = new MerchandiseServiceImpl(modelMapper, mockMerchandiseRepository);

        testMerchandise = new Merchandise();

        testMerchandise.setId(1L);
        testMerchandise.setImageUrl(MERCHANDISE_IMAGE_URL);
        testMerchandise.setCategory(MerchandiseEnum.FIGURES);
        testMerchandise.setPrice(BigDecimal.valueOf(70));
        testMerchandise.setProductTitle(MERCHANDISE_TITLE);
        testMerchandise.setReleaseDate(LocalDate.parse(MERCHANDISE_DATE));

        secondTestMerchandise = new Merchandise();
        thirdTestMerchandise = new Merchandise();
    }

    @Test
    public void createMerchandiseTest() {
        MerchandiseServiceModel convertMerchandiseServiceModel = new MerchandiseServiceModel();

        convertMerchandiseServiceModel.setId(2L);
        convertMerchandiseServiceModel.setImageUrl(MERCHANDISE_IMAGE_URL);
        convertMerchandiseServiceModel.setCategory(MerchandiseEnum.FIGURES);
        convertMerchandiseServiceModel.setPrice(BigDecimal.valueOf(70));
        convertMerchandiseServiceModel.setProductTitle(MERCHANDISE_TITLE);
        convertMerchandiseServiceModel.setReleaseDate(LocalDate.parse(MERCHANDISE_DATE));


        when(mockMerchandiseRepository.save(any(Merchandise.class))).thenReturn(testMerchandise);

        MerchandiseServiceModel testMerchandiseServiceModel = testMSI.createMerchandise(convertMerchandiseServiceModel);


        Assertions.assertEquals(testMerchandiseServiceModel.getId(), testMerchandise.getId());
        Assertions.assertNotEquals(testMerchandiseServiceModel.getId(), convertMerchandiseServiceModel.getId());
        Assertions.assertEquals(testMerchandiseServiceModel.getImageUrl(), testMerchandise.getImageUrl());
        Assertions.assertEquals(testMerchandiseServiceModel.getCategory(), testMerchandise.getCategory());
        Assertions.assertEquals(testMerchandiseServiceModel.getPrice(), testMerchandise.getPrice());
    }

    @Test
    public void findAllOrderByReleaseDateThenByPriceTest() {


        secondTestMerchandise.setId(2L);
        secondTestMerchandise.setImageUrl(MERCHANDISE_IMAGE_URL);
        secondTestMerchandise.setCategory(MerchandiseEnum.FIGURES);
        secondTestMerchandise.setPrice(BigDecimal.valueOf(50));
        secondTestMerchandise.setProductTitle(MERCHANDISE_TITLE);
        secondTestMerchandise.setReleaseDate(LocalDate.parse("2025-04-15"));

        thirdTestMerchandise.setId(3L);
        thirdTestMerchandise.setImageUrl(MERCHANDISE_IMAGE_URL);
        thirdTestMerchandise.setCategory(MerchandiseEnum.FIGURES);
        thirdTestMerchandise.setPrice(BigDecimal.valueOf(30));
        thirdTestMerchandise.setProductTitle(MERCHANDISE_TITLE);
        thirdTestMerchandise.setReleaseDate(LocalDate.parse(MERCHANDISE_DATE));

        when(mockMerchandiseRepository.findAllByOrderByReleaseDateDescPriceDesc())
                .thenReturn(Arrays.asList(secondTestMerchandise, testMerchandise, thirdTestMerchandise));

        List<MerchandiseViewModel> merchandiseViewModels = testMSI.findAllOrderByReleaseDateThenByPrice();

        Assertions.assertEquals(3, merchandiseViewModels.size());
        Assertions.assertEquals(secondTestMerchandise.getReleaseDate(), merchandiseViewModels.get(0).getReleaseDate());
        Assertions.assertEquals(BigDecimal.valueOf(30), merchandiseViewModels.get(2).getPrice());
    }

    @Test
    public void deleteMerchandiseByIdTest() {
        Long id1 = 1L;
        Long id2 = 2L;
        Long id3 = 3L;

        testMSI.deleteMerchandiseById(id1);
        testMSI.deleteMerchandiseById(id2);
        testMSI.deleteMerchandiseById(id3);

        verify(mockMerchandiseRepository, times(1)).deleteById(id1);
        verify(mockMerchandiseRepository, times(1)).deleteById(id2);
        verify(mockMerchandiseRepository, times(1)).deleteById(id3);
    }

    @Test
    public void findMerchandiseByIdTest() {

        when(mockMerchandiseRepository.findById(1L)).thenReturn(Optional.of(testMerchandise));

        secondTestMerchandise = testMSI.findMerchandiseById(1L);

        Assertions.assertEquals(secondTestMerchandise.getPrice(), testMerchandise.getPrice());
        Assertions.assertNotNull(secondTestMerchandise);
    }
}
