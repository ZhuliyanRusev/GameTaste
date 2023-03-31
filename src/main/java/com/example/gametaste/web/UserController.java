package com.example.gametaste.web;
import com.example.gametaste.model.binding.UserLoginBindingModel;
import com.example.gametaste.model.binding.UserRegisterBindingModel;
import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.entity.Merchandise;
import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.service.UserServiceModel;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.service.UserService;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;

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
        if(!model.containsAttribute("userAlreadyExists")){
            model.addAttribute("userAlreadyExists",false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }



        if (userService.doesUsernameExist(userRegisterBindingModel.getUsername()) || userService.doesEmailExist(userRegisterBindingModel.getEmail())) {
           redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel)
                   .addFlashAttribute("userAlreadyExists",true);
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

        BigDecimal totalGamesPrice = currentUser.getGamesSet().stream()
                .map(Game::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("allGamesPrice", totalGamesPrice);

        BigDecimal totalMerchandisesPrice = currentUser.getMerchandisesSet().stream()
                .map(Merchandise::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("allMerchandisesPrice", totalMerchandisesPrice);

        int itemsCount = currentUser.getGamesSet().size();
        itemsCount += currentUser.getMerchandisesSet().size();
        model.addAttribute("allItemsCount", itemsCount);
        return "users-items";
    }
    @GetMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id){
        User currentUser = userService.findUserById(this.currentUser.getId());
        Set<Game> gamesSet = currentUser.getGamesSet();
        gamesSet.removeIf(game -> game.getId().equals(id));
        userService.saveUser(currentUser);
        return "redirect:/users/items";
    }
    @GetMapping("/removeMerchandise/{id}")
    public String removeMerchandise(@PathVariable Long id){
        User currentUser = userService.findUserById(this.currentUser.getId());
        Set<Merchandise> merchandiseSet = currentUser.getMerchandisesSet();
        merchandiseSet.removeIf(game -> game.getId().equals(id));
        userService.saveUser(currentUser);
        return "redirect:/users/items";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "users-admin";
    }
}
