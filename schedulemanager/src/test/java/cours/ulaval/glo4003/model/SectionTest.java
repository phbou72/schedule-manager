package cours.ulaval.glo4003.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class SectionTest {
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;
	private static final int A_DURATION = 3;

	@Test
	public void canInstantiateASection() {
		Section section = new Section();

		assertNotNull(section);
	}

	@Test
	public void canInstantiateSectionWithParameters() {
		String nrc = "87134";
		String group = "A";
		String personInCharge = "a responsable person";
		List<String> teachers = Arrays.asList("teacher1", "teacher2");
		TimeDedicated timeDedicated = new TimeDedicated();
		TeachMode teachMode = TeachMode.InCourse;
		List<TimeSlot> courseTimeSlot = Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3));
		List<TimeSlot> labTimeSlot = new ArrayList<TimeSlot>();
		String courseAcronym = "GLO_4002";

		Section section = new Section(nrc, group, personInCharge, teachers, teachMode, timeDedicated, courseAcronym,
				courseTimeSlot, labTimeSlot);

		assertEquals(nrc, section.getNrc());
		assertEquals(group, section.getGroup());
		assertEquals(personInCharge, section.getPersonInCharge());
		assertEquals(teachers, section.getTeachers());
		assertEquals(timeDedicated, section.getTimeDedicated());
		assertEquals(teachMode, section.getTeachMode());
		assertEquals(courseTimeSlot, section.getCourseTimeSlots());
		assertEquals(labTimeSlot, section.getLabTimeSlot());
		assertEquals(courseAcronym, section.getCourseAcronym());
	}

	private Calendar generateTimeSlotStartTime() {
		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		startTime.set(Calendar.HOUR, A_HOUR);
		startTime.set(Calendar.MINUTE, A_MINUTE);
		return startTime;
	}
}