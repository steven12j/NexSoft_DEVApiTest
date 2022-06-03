package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.User;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class UserServiceTest {
	
	UserService userService;
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	String expectedExceptionMessage;
	
	@BeforeEach
	public void preparation() {
		
		userService = new UserServiceImpl();
		firstName = "Antoni";
		lastName = "Kurniawan";
		email = "antoni@gmail.com";
		password = "rahasia";
		repeatPassword = "rahasia";
	}
	
	
	@DisplayName("User object is created")
	@Test
	//@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testCreateUser_WhenDetailsProvide_ReturnUserObject() {
		
		//Prepare
		preparation();
		
		//Act
		
		User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
		
		
		assertTimeout(Duration.ofMillis(500), () -> {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		//Assert
				
		assertNotNull(user, "if creatuser sucess should not return null");
		assertEquals(firstName, user.getFirstName(), "User first name is incorrect");
		assertEquals(lastName, user.getLastName(), "User last name is incorrect");
		assertEquals(email, user.getEmail(), "User email is incorrect");
		assertNotNull(user.getId(), "User id cannot null");
	}
	
	
	//Red test / Negative test
	@Test
	@DisplayName("Empty first name cause exception")
	public void testCreateUser_WhenFirstNameIsEmpty_ThrowsIllegalException() {
		
		//Arrange
		
		firstName = "";
		expectedExceptionMessage = "User first name is empty";
		//Act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstName, lastName, email, password, repeatPassword);
		}, "Empty first name cause Illegal Argument Exception");
				
		//Assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	}
	
	//Red test / Negative test
		@Test
		@DisplayName("Empty last name cause exception")
		public void testCreateUser_WhenLastNameIsEmpty_ThrowsIllegalException() {
			
			//Arrange
			
			lastName = "";
			expectedExceptionMessage = "User last name is empty";
			//Act
			IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
				userService.createUser(firstName, lastName, email, password, repeatPassword);
			}, "Empty last name cause Illegal Argument Exception");
					
			//Assert
			assertEquals(expectedExceptionMessage, thrown.getMessage());
		}
	
}
