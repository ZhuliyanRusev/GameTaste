package com.example.gametaste.web;

import com.example.gametaste.model.binding.NewsCreateBindingModel;
import com.example.gametaste.model.view.NewsViewModel;
import com.example.gametaste.repository.NewsRepository;
import com.example.gametaste.service.NewsService;
import com.example.gametaste.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest {

    private static final String NEWS_DESCRIPTION = "Some News description in here.";
    private static final String NEWS_IMAGE_URL = "https://image.api.playstation.com/vulcan/ap/rnd/202206/0720/eEczyEMDd2BLa3dtkGJVE9Id.png";
    private static final String NEWS_TITLE = "Some News title in here.";
    private static final String NEWS_RELEASE_DATE = "2023-04-15";

    private MockMvc mockMvc;
    private NewsService newsService;
    private NewsController testNC;
    @Mock
    private NewsRepository mockNewsRepository;
    @Mock
    private ModelMapper modelMapper;
    private NewsViewModel testNewsViewModel;
    private NewsViewModel secondNewsViewModel;

    @BeforeEach
    void setup(){
        newsService = mock(NewsServiceImpl.class);
        testNC = new NewsController(newsService,modelMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(testNC).build();
        testNewsViewModel = new NewsViewModel();
        secondNewsViewModel = new NewsViewModel();
    }

    @Test
    public void newsCreateBindingModelTest(){
        NewsCreateBindingModel newsCreateBindingModel = testNC.newsCreateBindingModel();

        newsCreateBindingModel.setDescription(NEWS_DESCRIPTION);
        Assertions.assertNotNull(newsCreateBindingModel);
        Assertions.assertNotNull(newsCreateBindingModel.getDescription());
    }

    @Test
    public void testNews() throws Exception {

        List<NewsViewModel> allNews = new ArrayList<>();


        testNewsViewModel.setDescription(NEWS_DESCRIPTION);
        testNewsViewModel.setId(1L);
        testNewsViewModel.setImageUrl(NEWS_IMAGE_URL);
        testNewsViewModel.setTitle(NEWS_TITLE);
        testNewsViewModel.setFromDate(LocalDate.parse(NEWS_RELEASE_DATE));

        secondNewsViewModel.setDescription(NEWS_DESCRIPTION);
        secondNewsViewModel.setId(2L);
        secondNewsViewModel.setImageUrl(NEWS_IMAGE_URL);
        secondNewsViewModel.setTitle(NEWS_TITLE);
        secondNewsViewModel.setFromDate(LocalDate.parse("2024-04-15"));

        allNews.add(secondNewsViewModel);
        allNews.add(testNewsViewModel);

        when(newsService.findAllNewsSortByReleaseDateDesc()).thenReturn(allNews);

        List<NewsViewModel> convertedNews = newsService.findAllNewsSortByReleaseDateDesc();


//        mockMvc.perform(MockMvcRequestBuilders.get("/news"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("news"))
//                .andExpect(model().attributeExists("allNews"))
//                .andExpect(model().attribute("allNews", allNews));
    }

    @Test
    public void addNewsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/news/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("news-add"));
    }

//    @Test
//    public void addNewsConfirmTest() throws Exception {
//        NewsCreateBindingModel newsCreateBindingModel = new NewsCreateBindingModel();
//        newsCreateBindingModel.setDescription(NEWS_DESCRIPTION);
//        newsCreateBindingModel.setImageUrl("invalid-url");
//        newsCreateBindingModel.setTitle(NEWS_TITLE);
//        newsCreateBindingModel.setFromDate(LocalDate.parse(NEWS_RELEASE_DATE));
//
//        mockMvc.perform(post("/news/add")
//                        .flashAttr("newsCreateBindingModel", newsCreateBindingModel))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:"))
//                .andExpect(flash().attributeExists("newsCreateBindingModel"))
//                .andExpect(flash().attribute(BindingResult.MODEL_KEY_PREFIX + "newsCreateBindingModel",
//                        hasProperty("fieldErrors[0].defaultMessage".getClass(), is("Invalid URL").toString())))
//                .andExpect(flash().attribute(BindingResult.MODEL_KEY_PREFIX + "newsCreateBindingModel", hasProperty("fieldErrors".getClass(), hasSize(1).toString())))
//                .andExpect(flash().attribute(BindingResult.MODEL_KEY_PREFIX + "newsCreateBindingModel", hasProperty("fieldErrors[0].field".getClass(), is("imageUrl").toString())))
//                .andExpect(flash().attribute(BindingResult.MODEL_KEY_PREFIX + "newsCreateBindingModel", hasProperty("fieldErrors[0].code".getClass(), is("imageUrl.invalid").toString())))
//                .andExpect(flash().attribute(BindingResult.MODEL_KEY_PREFIX + "newsCreateBindingModel", hasProperty("fieldErrors[0].defaultMessage".getClass(), is("Invalid URL").toString())));
//    }
    @Test
    public void deleteNewsTest() throws Exception {
        Long newsId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/news/delete/" + newsId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/news"));

        verify(newsService, times(1)).deleteById(newsId);
    }
}
