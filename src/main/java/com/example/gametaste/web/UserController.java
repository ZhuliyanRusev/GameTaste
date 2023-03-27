package com.example.gametaste.web;
import com.example.gametaste.model.binding.UserLoginBindingModel;
import com.example.gametaste.model.binding.UserRegisterBindingModel;
import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.service.UserServiceModel;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.service.UserService;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;


    public UserController(ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model){
        if (model.containsAttribute("notFound")){
            model.addAttribute("notFound", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }



        if (userService.doesUsernameExist(userRegisterBindingModel.getUsername()) || userService.doesEmailExist(userRegisterBindingModel.getEmail())) {
            return "redirect:register";
        }

        UserServiceModel userServiceModel = userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("notFound")){
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());

        if (userServiceModel == null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        httpSession.setAttribute("user", userServiceModel);
        userService.loginUser(userServiceModel.getId(), userLoginBindingModel.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/items")
    public String allItems(Model model){
        User currentUser = userService.findUserById(this.currentUser.getId());
        model.addAttribute("userGamesSet",currentUser.getGamesSet());
        model.addAttribute("userMerchandisesSet",currentUser.getMerchandisesSet());
        return "users-items";
    }
}
