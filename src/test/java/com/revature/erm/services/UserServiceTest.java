package com.revature.erm.services;
import com.revature.erm.dtos.requests.LoginRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateUserRequest;
import com.revature.erm.models.User;
import com.revature.erm.repos.UserRepos;

import com.revature.erm.util.exceptions.AuthenticationException;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.lang.annotation.RetentionPolicy;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest {
    private UserService sut;
    private UserRepos mockUserRepos = mock(UserRepos.class);


    @Before
    public void setup() {
        sut = new UserService(mockUserRepos);
    }


    @After
    public void cleanUp() {
        reset(mockUserRepos);
    }
    @Test
    public void test_isUserNameValid_returnsFalse_givenEmptyString() {
        String username = "";
        boolean result = sut.isUsernameValid(username);
        Assert.assertFalse(result);
    }
    @Test
    public void test_isUserNameValid_returnsFalse_givenNullString() {
        String username = null;
        boolean result = sut.isUsernameValid(null);
        Assert.assertFalse(result);
    }


    @Test
    public void test_isUsernameValid_returnsFalse_givenShortUsername() {
        Assert.assertFalse(sut.isUsernameValid("short"));

    }


    @Test
    public void test_isUsernameValid_returnsFalse_givenLongUsername() {
        Assert.assertFalse(sut.isUsernameValid("undernocircumstanceshouldausernamebethislong"));
    }


    @Test
    public void test_isUsernameValid_returnsFalse_givenUsernameWithIllegalCharacters() {
        Assert.assertFalse(sut.isUsernameValid("ILOVEQC!!"));
    }


    @Test
    public void test_isUsernameValid_returnsTrue_givenValidUsername() {
        Assert.assertTrue(sut.isUsernameValid("tester99"));
    }
    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidUsername() {

        // Arrange
        LoginRequest loginRequest = new LoginRequest("bad", "p4$$W0RD");
        // Act
        try {
            sut.login(loginRequest);
        } finally {
            verify(mockUserRepos, times(0)).findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }

    }


    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidPassword() {

        // Arrange
        LoginRequest loginRequest = new LoginRequest("tester99", "NiceTry");
        // Act
        try {
            sut.login(loginRequest);
        } finally {
            verify(mockUserRepos, times(0)).findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }
    }
    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidUsernameAndPassword() {

        // Arrange
        LoginRequest loginRequest = new LoginRequest("Nicetry!", "Buddyboy!");
        // Act
        try {
            sut.login(loginRequest);
        } finally {
            verify(mockUserRepos, times(0)).findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }
    }


    @Test(expected = AuthenticationException.class)
    public void test_login_throwsAuthenticationException_givenUnknownUserCredentials() {

        // Arrange
        UserService spiedSut = Mockito.spy(sut);

        LoginRequest loginRequest = new LoginRequest("Notinyourdatabase", "p4$$W0RDD");

        when(spiedSut.isUsernameValid(loginRequest.getUsername())).thenReturn(true);
        when(spiedSut.isPasswordValid(loginRequest.getPassword())).thenReturn(true);
        when(mockUserRepos.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())).thenReturn(null);
        // Act
        sut.login(loginRequest);

    }
    @Test
    public void test_login_returnsNonNullAppUser_givenValidAndKnownCredentials() {

        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        LoginRequest loginRequest = new LoginRequest("tester99", "p4$$W0RD");
        when(spiedSut.isUsernameValid(loginRequest.getUsername())).thenReturn(true);
        when(spiedSut.isPasswordValid(loginRequest.getPassword())).thenReturn(true);
        when(mockUserRepos.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())).thenReturn(new User());
        // Act
        User loginResult = spiedSut.login(loginRequest);
        // Assert
        assertNotNull(loginResult);
        verify(mockUserRepos, times(1)).findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        verify(spiedSut, times(1)).isUsernameValid(loginRequest.getUsername());
        verify(spiedSut, times(1)).isPasswordValid(loginRequest.getPassword());

    }
    @Test(expected = InvalidRequestException.class)
    public void test_register_throwsInvalidRequestException_givenInvalidNewUserData() {

        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        NewUserRequest stubbedRequest = new NewUserRequest();
        doReturn(false).when(spiedSut).isUserValid(any());
        // Act
        try {
            spiedSut.register(stubbedRequest);
        } finally {
            // Assert
            verify(spiedSut, times(1)).isUserValid(any());
            verify(spiedSut, times(0)).isUsernameAvailable(anyString());
            verify(spiedSut, times(0)).isEmailAvailable(anyString());
            verify(mockUserRepos, times(0)).save(any());
        }

    }
    @Test
    public void isUserValid_givenInvalidUserUserName() {
        //arrange
        User invalidUser = new User("Tester", "McTesterson", "employee@email.com", "W!hataBadUsername", "p4$$WORD");
        //act
        boolean result = sut.isUserValid(invalidUser);
        //assert
        Assertions.assertFalse(result);
    }


    @Test
    public void isUserValid_givenInvalidUserEmail() {
        //arrange
        User invalidUser = new User("Tester", "McTesterson", "employeeemail.com", "Tester99", "p4$$WORD");
        //act
        boolean result = sut.isUserValid(invalidUser);
        //assert
        Assertions.assertFalse(result);
    }


    @Test
    public void isUserValid_givenInvalidUserPassword() {
        //arrange
        User invalidUser = new User("Tester", "McTesterson", "employee@email.com", "Tester99", "nogoodpassword");
        //act
        boolean result = sut.isUserValid(invalidUser);
        //assert
        Assertions.assertFalse(result);
    }
    @Test
    public void test_registration_throwsResourceConflictException_givenDuplicateUsernameAndEmail() throws ResourceConflictException, IOException {

        UserService spiedSut = Mockito.spy(sut);

        NewUserRequest repeatedUserRequest = new NewUserRequest("Tester", "McTesterson", "employee@email.com", "Tester99", "p4$$WORD");

        User repeatedUserToSave = repeatedUserRequest.extractUser();

        String username = repeatedUserToSave.getUsername();
        String email = repeatedUserToSave.getEmail();

        when(mockUserRepos.findUserByUsername(username)).thenReturn(new User());
        when(mockUserRepos.findUserByEmail(email)).thenReturn(new User());

        try {
            Exception exception = assertThrows(ResourceConflictException.class, () -> {
                spiedSut.register(repeatedUserRequest);
            });
            String exceptionMessage = exception.getMessage();
            Assertions.assertNotNull(exceptionMessage);
        } finally {
            verify(mockUserRepos, times(0)).save(repeatedUserToSave);
        }

    }
}


