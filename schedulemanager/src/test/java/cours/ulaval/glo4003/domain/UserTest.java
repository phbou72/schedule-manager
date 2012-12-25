package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private static String IDUL = "brgaa";
	private static String NAME = "Bruno Gagnon-Adam";
	private static String PASSWORD = "motdepasse";
	private static Role ROLE = Role.ROLE_Directeur;
	private static String WRONG_PASSWORD = "mauvaismotdepasse";
	private static String VALID_EMAIL_ADRESS = "email@schedulemanager.com";
	private static Notification notification;
	private static final String A_PATH = "path";

	private User user;

	@Before
	public void setUp() {
		user = new User(IDUL, NAME, PASSWORD, ROLE);
		notification = new Notification(Notification.NEW_SCHEDULE, A_PATH);
	}

	@Test
	public void canVerifyValidEmailAdress() {
		user.setEmailAddress(VALID_EMAIL_ADRESS);

		assertTrue(user.hasValidEmailAdress());
	}

	@Test
	public void canValidateCredentials() {
		assertTrue(user.validateCredentials(PASSWORD));
	}

	@Test
	public void canValidateWrongCredentials() {
		assertFalse(user.validateCredentials(WRONG_PASSWORD));
	}

	@Test
	public void canAddRoleToUser() {
		user.addRole(Role.ROLE_Enseignant);

		assertTrue(user.hasRole(Role.ROLE_Enseignant));
	}

	@Test
	public void cannotAddTheSameRoleTwice() {
		int nbExpected = user.getNumberOfRoles() + 1;

		user.addRole(Role.ROLE_Enseignant);
		user.addRole(Role.ROLE_Enseignant);

		assertEquals(nbExpected, user.getNumberOfRoles());
	}

	@Test
	public void canAddNotificationToUser() {
		user.addNotification(notification);

		assertTrue(user.hasNotification(notification));
	}

	@Test
	public void canRemoveNotificationToUser() {
		user.removeNotification(notification);

		assertFalse(user.hasNotification(notification));
	}

	@Test
	public void cannotAddTheSameNotificationTwice() {
		int nbExpected = 1;

		user.addNotification(notification);
		user.addNotification(notification);

		assertEquals(nbExpected, user.getNumberOfNotifications());
	}
}
