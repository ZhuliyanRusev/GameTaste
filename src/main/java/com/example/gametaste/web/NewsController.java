package com.example.gametaste.web;

import com.example.gametaste.model.binding.NewsCreateBindingModel;
import com.example.gametaste.model.service.NewsServiceModel;
import com.example.gametaste.security.UrlValidator;
import com.example.gametaste.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public NewsCreateBindingModel newsCreateBindingModel() {
        return new NewsCreateBindingModel();
    }

    @GetMapping()
    public String news(Model model) {

        model.addAttribute("allNews", newsService.findAllNewsSortByReleaseDateDesc());


        return "news";
    }

    @GetMapping("/add")
    public String addNews() {
        return "news-add";
    }

    @PostMapping("/add")
    public String addNewsConfirm(@Valid NewsCreateBindingModel newsCreateBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (UrlValidator.validate(newsCreateBindingModel.getImageUrl())) {
            bindingResult.rejectValue("imageUrl", "imageUrl.invalid", "Invalid URL");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsCreateBindingModel", newsCreateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.newsCreateBindingModel", bindingResult);
            return "redirect:add";
        }

        NewsServiceModel newsServiceModel = newsService.saveNews(modelMapper.map(newsCreateBindingModel, NewsServiceModel.class));
        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        newsService.deleteById(id);

        return "redirect:/news";
    }
}