//package com.example.gametaste.service;
//
//import com.example.gametaste.model.entity.enums.UserRoleEnum;
//import com.example.gametaste.model.service.UserServiceModel;
//import com.example.gametaste.repository.UserRepository;
//import com.example.gametaste.security.CurrentUser;
//import com.example.gametaste.service.impl.UserServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.userdetails.User;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceImplTest {
//
//    private UserServiceImpl testUSI;
//
//    private UserRepository mockUserRepository;
//    private ModelMapper modelMapper = new ModelMapper();
//    private CurrentUser currentUser = new CurrentUser();
//
//    @BeforeEach
//    void setUp(){
//        testUSI = new UserServiceImpl(
//                mockUserRepository,
//                modelMapper,
//                currentUser);
//    }
//    @Test
//    public void testRegisterUser() {
//
//        UserServiceModel userServiceModel = new UserServiceModel();
//        userServiceModel.setUsername("testuser");
//        userServiceModel.setPassword("testpassword");
//        userServiceModel.setEmail("testuser@example.com");
//        userServiceModel.setUserRoleEnum(UserRoleEnum.USER);
//        userServiceModel.setFirstName("test");
//        userServiceModel.setLastName("testov");
//

//        User user = modelMapper.map(userServiceModel,User.class);
//
//
//
//        UserServiceModel result = testUSI.registerUser(userServiceModel);

//        assertEquals(userServiceModel.getUsername(), result.getUsername());
//        assertEquals(userServiceModel.getPassword(), result.getPassword());
//        assertEquals(userServiceModel.getEmail(), result.getEmail());
//        assertEquals(UserRoleEnum.USER, result.getUserRoleEnum());
//        Assertions.assertEquals(userServiceModel.getFirstName(),result.getFirstName());
//        Assertions.assertEquals(userServiceModel.getLastName(),result.getLastName());
//        assertNotNull(result.getId());
//    }
//}
