package com.example.gametaste.service;


import com.example.gametaste.model.entity.User;
import com.example.gametaste.model.entity.enums.UserRoleEnum;
import com.example.gametaste.model.service.UserServiceModel;
import com.example.gametaste.repository.UserRepository;
import com.example.gametaste.security.CurrentUser;
import com.example.gametaste.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private static final String USER_USERNAME = "testUser";
    private static final String USER_PASSWORD = "testPassword";
    private static final String USER_FIRST_NAME = "testFirstName";
    private static final String USER_LAST_NAME = "testLastName";
    private static final String USER_EMAIL = "testEmail";
    private UserServiceImpl testUSI;

    private final ModelMapper modelMapper = new ModelMapper();
    @Mock
    private UserRepository mockUserRepository;

    private final CurrentUser currentUser = new CurrentUser();

    private User testUser;
    private User secondTestUser;
    private User thirdTestUser;

    @BeforeEach
    void setup() {
        testUSI = new UserServiceImpl(mockUserRepository, modelMapper, currentUser);
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername(USER_USERNAME);
        testUser.setPassword(USER_PASSWORD);
        testUser.setFirstName(USER_FIRST_NAME);
        testUser.setLastName(USER_LAST_NAME);
        testUser.setEmail(USER_EMAIL);
        testUser.setUserRoleEnum(UserRoleEnum.USER);

        secondTestUser = new User();
        thirdTestUser = new User();

    }

    @Test
    public void registerUserTest() {


        UserServiceModel testUserServiceModel = new UserServiceModel();
        testUserServiceModel.setId(1L);
        testUserServiceModel.setUsername(USER_USERNAME);
        testUserServiceModel.setPassword(USER_PASSWORD);
        testUserServiceModel.setFirstName(USER_FIRST_NAME);
        testUserServiceModel.setLastName(USER_LAST_NAME);
        testUserServiceModel.setEmail(USER_EMAIL);

        when(mockUserRepository.save(any(User.class))).thenReturn(testUser);

        UserServiceModel convertedUserServiceModel = testUSI.registerUser(testUserServiceModel);

        Assertions.assertEquals(convertedUserServiceModel.getFirstName(), testUser.getFirstName());
        Assertions.assertEquals(convertedUserServiceModel.getId(), testUser.getId());
        Assertions.assertNotNull(convertedUserServiceModel);
        Assertions.assertEquals(convertedUserServiceModel.getUserRoleEnum(), testUser.getUserRoleEnum());


    }

    @Test
    public void doesUsernameExistTest() {

        when(mockUserRepository.existsByUsername(testUser.getUsername()))
                .thenReturn(false);


        Assertions.assertFalse(testUSI.doesUsernameExist(testUser.getUsername()));

    }

    @Test
    public void doesUsernameExist_ShouldReturnTrue() {

        when(mockUserRepository.existsByUsername(testUser.getUsername()))
                .thenReturn(true);


        Assertions.assertTrue(testUSI.doesUsernameExist(testUser.getUsername()));

    }

    @Test
    public void doesEmailExistTest() {

        when(mockUserRepository.existsByEmail(testUser.getEmail()))
                .thenReturn(false);


        Assertions.assertFalse(testUSI.doesEmailExist(testUser.getEmail()));

    }

    @Test
    public void doesEmailExist_ShouldReturnTrue() {

        when(mockUserRepository.existsByEmail(testUser.getEmail()))
                .thenReturn(true);


        Assertions.assertTrue(testUSI.doesEmailExist(testUser.getEmail()));

    }
    @Test
    public void findUserByUsernameAndPasswordTest(){

        when(mockUserRepository.findByUsernameAndPassword(testUser.getUsername(),testUser.getPassword()))
                .thenReturn(Optional.of(testUser));

        UserServiceModel testUserServiceModel = testUSI.findUserByUsernameAndPassword(testUser.getUsername(),testUser.getPassword());

        Assertions.assertEquals(testUserServiceModel.getUsername(),testUser.getUsername());
        Assertions.assertEquals(testUserServiceModel.getId(),testUser.getId());
    }
   @Test
   public void loginUserTest(){
        testUSI.loginUser(testUser.getId(),testUser.getUsername());

        Assertions.assertNotNull(currentUser);
        Assertions.assertEquals(currentUser.getId(),testUser.getId());
        Assertions.assertEquals(currentUser.getUsername(),testUser.getUsername());
   }

   @Test
    public void findUserByIdTest(){

        when(mockUserRepository.findById(testUser.getId()))
                .thenReturn(Optional.ofNullable(testUser));

             secondTestUser = testUSI.findUserById(1L);

             Assertions.assertNotNull(secondTestUser);
             Assertions.assertEquals(secondTestUser.getId(),testUser.getId());
   }

    @Test
    public void saveUserTest() {
        testUSI.saveUser(testUser);

        verify(mockUserRepository, times(1)).save(testUser);
    }
    @Test
    public void initializeAdminTest() {
        when(mockUserRepository.count()).thenReturn(0L);

        testUSI.initializeAdmin();

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUserRepository).save(argumentCaptor.capture());
        User savedUser = argumentCaptor.getValue();

        Assertions.assertEquals("Admin", savedUser.getUsername());
        Assertions.assertEquals("admin@admin", savedUser.getEmail());
        Assertions.assertEquals("Admin", savedUser.getFirstName());
        Assertions.assertEquals("Admin", savedUser.getLastName());
        Assertions.assertEquals("111", savedUser.getPassword());
        Assertions.assertEquals(UserRoleEnum.ADMIN, savedUser.getUserRoleEnum());
    }
    @Test
    public void  initializeAdminTestShouldNotEnterIFBlockWhenRepositoryIsFilled(){
            when(mockUserRepository.count()).thenReturn(1L);
            testUSI.initializeAdmin();
            verify(mockUserRepository, never()).save(any(User.class));
        }

    @Test
    public void findAllUsersTest(){
        when(mockUserRepository.findAll())
                .thenReturn(List.of(testUser,secondTestUser,thirdTestUser));

        secondTestUser.setId(2L);
        secondTestUser.setUsername("anotherUsername");
        secondTestUser.setPassword("anotherPassword");
        secondTestUser.setEmail("anotherEmail");
        secondTestUser.setFirstName(USER_FIRST_NAME);
        secondTestUser.setLastName(USER_LAST_NAME);
        secondTestUser.setUserRoleEnum(UserRoleEnum.USER);

        thirdTestUser.setId(3L);
        thirdTestUser.setUsername("thirdUsername");
        thirdTestUser.setPassword("anotherPassword");
        thirdTestUser.setEmail("thirdEmail");
        thirdTestUser.setFirstName(USER_FIRST_NAME);
        thirdTestUser.setLastName(USER_LAST_NAME);
        thirdTestUser.setUserRoleEnum(UserRoleEnum.USER);

        List<User> allUsers = testUSI.findAllUsers();

        Assertions.assertEquals(3,allUsers.size());
    }
}
