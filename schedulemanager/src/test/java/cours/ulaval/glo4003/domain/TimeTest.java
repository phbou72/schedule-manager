package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {

	private static final Integer A_MINUTE = 30;
	private static final Integer AN_HOUR = 10;
	private static final Integer AN_EARLIER_HOUR = 8;
	private static final Integer AN_EARLIER_MINUTE = 15;
	private static final Integer A_LATER_HOUR = 12;
	private static final Integer A_LATER_MINUTE = 45;

	private Time time;
	private Time anEarlierTime;
	private Time anotherEarlierTime;
	private Time aLaterTime;
	private Time anotherLaterTime;

	@Before
	public void setUp() {
		time = new Time(AN_HOUR, A_MINUTE);

		// Il y a quatre cas à tester
		anEarlierTime = new Time(AN_EARLIER_HOUR, AN_EARLIER_MINUTE);
		anotherEarlierTime = new Time(AN_HOUR, AN_EARLIER_MINUTE);
		aLaterTime = new Time(A_LATER_HOUR, A_LATER_MINUTE);
		anotherLaterTime = new Time(AN_HOUR, A_LATER_MINUTE);
	}

	@Test
	public void canAddHour() {
		Integer hoursToAdd = 2;

		time.addHours(hoursToAdd);

		assertEquals(AN_HOUR + hoursToAdd, (int) time.getHour());
	}

	@Test
	public void canDetermineIfATimeIsAfterASpecifiedTime() {
		assertTrue(time.after(anEarlierTime));
		assertTrue(time.after(anotherEarlierTime));
		assertFalse(time.after(aLaterTime));
		assertFalse(time.after(anotherLaterTime));
	}

	@Test
	public void canDetermineIfATimeIsBeforeASpecifiedTime() {
		assertTrue(time.before(aLaterTime));
		assertTrue(time.before(anotherLaterTime));
		assertFalse(time.before(anEarlierTime));
		assertFalse(time.before(anotherEarlierTime));
	}

	@Test
	public void canConvertTimeToString() {
		assertEquals(AN_HOUR + ":" + A_MINUTE, time.toString());
	}
}
