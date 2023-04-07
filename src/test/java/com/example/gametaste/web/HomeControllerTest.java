package com.example.gametaste.web;

import com.example.gametaste.model.service.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private HttpSession httpSession;

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ExtendedModelMap();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndexWithNullUser() {
        // Arrange
        when(httpSession.getAttribute("user")).thenReturn(null);

        // Act
        String result = homeController.index(httpSession, model);

        // Assert
        assertEquals("index", result);
    }

    @Test
    public void testIndexWithNonNullUser() {
        // Arrange
        when(httpSession.getAttribute("user")).thenReturn(new UserServiceModel());

        // Act
        String result = homeController.index(httpSession, model);

        // Assert
        assertEquals("home", result);
    }
}