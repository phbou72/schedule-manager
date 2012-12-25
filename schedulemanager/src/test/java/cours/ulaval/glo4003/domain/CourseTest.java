package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {

	private Course course;

	private Prerequisite prerequisite;

	@Before
	public void setUp() {
		prerequisite = mock(Prerequisite.class);
		when(prerequisite.containsAcronym(anyString())).thenReturn(true);

		course = new Course();
		course.setPrerequisites(Arrays.asList(prerequisite));
	}

	@Test
	public void canTellIfACourseIsConcomitting() {
		when(prerequisite.isConcomitant()).thenReturn(true);

		Course otherCourse = new Course();
		otherCourse.setAcronym(anyString());

		assertTrue(course.isConcomitting(otherCourse));
	}

	@Test
	public void canTellIfACourseIsNotConcomitting() {
		when(prerequisite.isConcomitant()).thenReturn(false);

		Course otherCourse = new Course();
		otherCourse.setAcronym(anyString());

		assertFalse(course.isConcomitting(otherCourse));
	}

}
