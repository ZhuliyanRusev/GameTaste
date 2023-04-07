package com.example.gametaste.web;

import com.example.gametaste.model.binding.UserLoginBindingModel;
import com.example.gametaste.model.binding.UserRegisterBindingModel;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private UserController testUC;
    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private UserService mockUserService;

    private final CurrentUser currentUser = new CurrentUser();

    @Mock
    private HttpSession httpSession;


    @BeforeEach()
    void setup() {
        testUC = new UserController(modelMapper, mockUserService, currentUser);

    }

    @Test
    public void userRegisterBindingModelTest() {

        UserRegisterBindingModel userRegisterBindingModel = testUC.userRegisterBindingModel();

        Assertions.assertNotNull(userRegisterBindingModel);
    }

    @Test
    public void userLoginBindingModelTest() {
        UserLoginBindingModel userLoginBindingModel = testUC.userLoginBindingModel();

        Assertions.assertNotNull(userLoginBindingModel);
    }

    @Test
    public void testRegister() {
        Model model = new ExtendedModelMap();

        String view = testUC.register(model);

        assertEquals(false, model.getAttribute("userAlreadyExists"));
        assertEquals("register", view);
    }

    @Test
    public void registerConfirmTest() {

        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername("testuser");
        userRegisterBindingModel.setEmail("testuser@test.com");
        userRegisterBindingModel.setPassword("testpassword");
        userRegisterBindingModel.setConfirmPassword("testpassword");

        BindingResult bindingResult = new BeanPropertyBindingResult(userRegisterBindingModel, "userRegisterBindingModel");

        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        Model model = new ExtendedModelMap();

        Mockito.when(mockUserService.doesUsernameExist(userRegisterBindingModel.getUsername())).thenReturn(false);
        Mockito.when(mockUserService.doesEmailExist(userRegisterBindingModel.getEmail())).thenReturn(false);

        String viewName = testUC.registerConfirm(userRegisterBindingModel, bindingResult, redirectAttributes, model);

        Assertions.assertEquals("redirect:login", viewName);
        Assertions.assertFalse(redirectAttributes.containsAttribute("userRegisterBindingModel"));
        Assertions.assertFalse(redirectAttributes.containsAttribute("userAlreadyExists"));
    }
}
