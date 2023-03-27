package com.example.gametaste.web;

import com.example.gametaste.model.binding.MerchandiseCreateBindingModel;
import com.example.gametaste.model.entity.Merchandise;
import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.service.MerchandiseServiceModel;
import com.example.gametaste.model.view.MerchandiseViewModel;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.security.UrlValidator;
import com.example.gametaste.service.MerchandiseService;
import com.example.gametaste.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/merchandises")
public class MerchandiseController {

    private final ModelMapper modelMapper;
    private final MerchandiseService merchandiseService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public MerchandiseController(ModelMapper modelMapper, MerchandiseService merchandiseService, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.merchandiseService = merchandiseService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public MerchandiseCreateBindingModel merchandiseCreateBindingModel(){
        return new MerchandiseCreateBindingModel();
    }

    @GetMapping("/add")
    public String addMerchandise(){
        return "merchandises-add";
    }

    @PostMapping("/add")
    public String addMerchandiseConfirm(@Valid MerchandiseCreateBindingModel merchandiseCreateBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes){

        if (UrlValidator.validate(merchandiseCreateBindingModel.getImageUrl())) {
            bindingResult.rejectValue("imageUrl", "imageUrl.invalid", "Invalid URL");
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("merchandiseCreateBindingModel",merchandiseCreateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.merchandiseCreateBindingModel", bindingResult);
            return "redirect:add";
        }

        MerchandiseServiceModel merchandiseServiceModel = merchandiseService
                .createMerchandise(modelMapper.map(merchandiseCreateBindingModel, MerchandiseServiceModel.class));
        return "redirect:all";
    }

    @GetMapping("/all")
    public String allMerchandises(Model model){
        List<MerchandiseViewModel> allMerchandises = merchandiseService.findAllOrderByReleaseDateThenByPrice();
        model.addAttribute("allMerchandises", allMerchandises);
        return "merchandises-all";
    }
    @GetMapping("/delete/{id}")
    public String deleteMerchandise(@PathVariable Long id){
        merchandiseService.deleteMerchandiseById(id);

        return "redirect:/merchandises/all";
    }

    @GetMapping("/add/{id}")
    public String addMerchandise(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Merchandise currentMerchandise = merchandiseService.findMerchandiseById(id);
        User currentUser = userService.findUserById(this.currentUser.getId());
        currentUser.getMerchandisesSet().add(currentMerchandise);
        userService.saveUser(currentUser);
        redirectAttributes.addAttribute("added", "true");
        return "redirect:/merchandises/all";
    }
}
