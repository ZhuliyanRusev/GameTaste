package com.example.gametaste.web;

import com.example.gametaste.model.binding.GamesCreateBindingModel;
import com.example.gametaste.model.entity.Game;
import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.service.GameServiceModel;
import com.example.gametaste.model.view.GamesViewModel;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.security.UrlValidator;
import com.example.gametaste.service.GameService;
import com.example.gametaste.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/games")
public class GameController {
    private final ModelMapper modelMapper;
    private final GameService gameService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public GameController(ModelMapper modelMapper, GameService gameService, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.gameService = gameService;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @ModelAttribute
    public GamesCreateBindingModel gamesCreateBindingModel() {
        return new GamesCreateBindingModel();
    }

    @GetMapping("/add")
    public String addGame() {
        return "games-add";
    }

    @PostMapping("/add")
    public String addGameConfirm(@Valid GamesCreateBindingModel gamesCreateBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (UrlValidator.validate(gamesCreateBindingModel.getImageUrl())) {
            bindingResult.rejectValue("imageUrl", "imageUrl.invalid", "Invalid URL");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gamesCreateBindingModel", gamesCreateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.gamesCreateBindingModel", bindingResult);

            return "redirect:add";

        }
//todo да добавя логика за предотвратяване на счупване на страницата от повтарящо се име на играта
        GameServiceModel gameServiceModel = gameService.addGame(modelMapper.map(gamesCreateBindingModel, GameServiceModel.class));
        return "redirect:all";
    }

    @GetMapping("/all")
    public String allGames(Model model) {
        model.addAttribute("allGames", gameService.findAllGamesSortByPriceDescending());
        return "games-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        List<User> usersWithGames = userService.findAllUsers();

        usersWithGames.forEach(user -> {
            user.getGamesSet().removeIf(game -> game.getId().equals(id));
            userService.saveUser(user);
        });
//        for (User user : usersWithGame) {
//            user.getGamesSet().removeIf(game -> game.getId().equals(id));
//            userService.saveUser(user);
//        }
        gameService.deleteGameById(id);
        return "redirect:/games/all";
    }

    @GetMapping("/add/{id}")
    public String buyGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Game currentGame = gameService.findGameById(id);
        User currentUser = userService.findUserById(this.currentUser.getId());
        currentUser.getGamesSet().add(currentGame);
        userService.saveUser(currentUser);
        redirectAttributes.addAttribute("added", "true");
        return "redirect:/games/all";
    }
}
