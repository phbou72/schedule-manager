package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cours.ulaval.glo4003.controller.model.AvailabilityModel;
import cours.ulaval.glo4003.domain.conflictdetection.ConcomittingCoursesFilter;
import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.conflictdetection.CourseLevelFilter;
import cours.ulaval.glo4003.domain.conflictdetection.SameTeacherFilter;
import cours.ulaval.glo4003.domain.conflictdetection.Sink;
import cours.ulaval.glo4003.domain.conflictdetection.UnavailableTeacherFilter;
import cours.ulaval.glo4003.domain.exception.FailedScheduleGenerationException;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;
import cours.ulaval.glo4003.domain.repository.CourseRepository;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;
import cours.ulaval.glo4003.persistence.ITTestBase;
import cours.ulaval.glo4003.persistence.AvailabilityXMLRepository;
import cours.ulaval.glo4003.persistence.CourseXMLRepository;
import cours.ulaval.glo4003.persistence.ProgramSheetXMLRepository;

public class ScheduleGeneratorIT extends ITTestBase {
	private static final String JSON_AVAILABILITY = "{\"monday\":[1,1,1,1,1,1,1,1,2,2,2,2,2],\"tuesday\":[2,2,2,2,2,1,1,1,1,1,0,0,0],\"wednesday\":[2,2,1,1,1,1,1,1,0,0,0,1,1],\"thursday\":[0,0,0,0,0,1,1,1,1,2,2,2,2],\"friday\":[0,0,0,1,1,1,1,1,0,0,0,0,0]}";
	private static final String OTHER_JSON_AVAILALBILITY = "{\"monday\":[0,0,0,0,0,0,0,0,0,0,0,0,0],\"tuesday\":[2,2,2,2,2,0,0,0,0,0,0,0,0],\"wednesday\":[2,2,0,0,0,0,0,0,0,0,0,0,0],\"thursday\":[0,0,0,0,0,1,0,0,1,2,2,2,2],\"friday\":[0,0,0,0,1,0,0,0,0,0,0,0,0]}";
	private static final int A_HOUR = 10;
	private static final int A_MINUTE = 30;

	private static ObjectMapper mapper = new ObjectMapper();
	private static AvailabilityModel availabilityModel;
	private static Availability availabilities;
	private static AvailabilityRepository availabilityRepository;

	private CourseRepository courseRepository;
	private ProgramSheetRepository programSheetRepository;
	private Section glo2002Section;
	private Section ift2004Section;
	private Section ift2002Section;
	private Section glo1901Section;
	private Section glo1010Section;
	private Section ift2901Section;
	private Section glo3013Section;
	private Schedule schedule;
	private ConflictDetector conflictDetector;
	private Section ift2002SectionFriday;

	@BeforeClass
	public static void setupClass() throws Exception {
		availabilityRepository = new AvailabilityXMLRepository();

		addTeacherAvailability("teacher1", JSON_AVAILABILITY);
		addTeacherAvailability("teacher2", JSON_AVAILABILITY);
		addTeacherAvailability("teacher3", JSON_AVAILABILITY);
		addTeacherAvailability("teacher4", JSON_AVAILABILITY);
		addTeacherAvailability("teacher5", JSON_AVAILABILITY);
		addTeacherAvailability("unavailableTeacher", OTHER_JSON_AVAILALBILITY);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		availabilityRepository.clear();
	}

	@Before
	public void setUp() throws Exception {
		Prerequisite concomittingPrerequisite = new Prerequisite();
		concomittingPrerequisite.setAcronyms(Arrays.asList("IFT-2002"));
		concomittingPrerequisite.setIsConcomitant(true);

		Prerequisite notConcomittingPrerequisite = new Prerequisite();
		notConcomittingPrerequisite.setAcronyms(Arrays.asList("IFT-2002"));
		notConcomittingPrerequisite.setIsConcomitant(false);

		Course glo2002 = new Course("GLO-2002", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier,
				Arrays.asList(concomittingPrerequisite), new TimeDedicated(3, 0, 6));
		Course ift2004 = new Course("IFT-2004", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier,
				Arrays.asList(notConcomittingPrerequisite), new TimeDedicated(3, 0, 6));
		Course ift2002 = new Course("IFT-2002", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier, new ArrayList<Prerequisite>(),
				new TimeDedicated(3, 0, 6));
		Course glo1901 = new Course("GLO-1901", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier, new ArrayList<Prerequisite>(),
				new TimeDedicated(3, 0, 6));
		Course glo1010 = new Course("GLO-1010", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier, new ArrayList<Prerequisite>(),
				new TimeDedicated(3, 0, 6));
		Course ift2901 = new Course("IFT-2901", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier, new ArrayList<Prerequisite>(),
				new TimeDedicated(3, 0, 6));
		Course glo3013 = new Course("GLO-3013", "Génie logiciel orienté derp", 3, "derpinini", Cycle.Premier, new ArrayList<Prerequisite>(),
				new TimeDedicated(3, 0, 6));

		courseRepository = new CourseXMLRepository();
		programSheetRepository = new ProgramSheetXMLRepository();

		courseRepository.store(glo2002);
		courseRepository.store(ift2004);
		courseRepository.store(ift2002);
		courseRepository.store(glo1901);
		courseRepository.store(glo1010);
		courseRepository.store(ift2901);
		courseRepository.store(glo3013);

		glo2002Section = new Section("87134", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse, new TimeDedicated(3, 2, 4),
				"GLO-2002", null, null);
		glo2002Section.setCourseRepository(courseRepository);
		glo2002Section.setProgramSheetRepository(programSheetRepository);
		ift2004Section = new Section("90876", "A", "a responsable person", Arrays.asList("teacher2"), TeachMode.InCourse, new TimeDedicated(3, 0, 6),
				"IFT-2004", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)), null);
		ift2004Section.setCourseRepository(courseRepository);
		ift2004Section.setProgramSheetRepository(programSheetRepository);
		ift2002Section = new Section("11765", "A", "a responsable person", Arrays.asList("teacher3", "teacher4"), TeachMode.InCourse,
				new TimeDedicated(3, 0, 6), "IFT-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)), null);
		ift2002Section.setCourseRepository(courseRepository);
		ift2002Section.setProgramSheetRepository(programSheetRepository);
		ift2002SectionFriday = new Section("88769", "A", "a responsable person", Arrays.asList("teacher3"), TeachMode.InCourse, new TimeDedicated(3,
				0, 6), "IFT-2002", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.WEDNESDAY)), null);
		ift2002SectionFriday.setCourseRepository(courseRepository);
		ift2002SectionFriday.setProgramSheetRepository(programSheetRepository);
		glo1901Section = new Section("87009", "A", "a responsable person", Arrays.asList("teacher5"), TeachMode.InCourse, new TimeDedicated(3, 0, 6),
				"GLO-1901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)), null);
		glo1901Section.setCourseRepository(courseRepository);
		glo1901Section.setProgramSheetRepository(programSheetRepository);
		glo1010Section = new Section("44678", "A", "a responsable person", Arrays.asList("teacher3"), TeachMode.InCourse, new TimeDedicated(3, 0, 6),
				"GLO-1010", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.MONDAY)), null);
		glo1010Section.setCourseRepository(courseRepository);
		glo1010Section.setProgramSheetRepository(programSheetRepository);
		ift2901Section = new Section("87678", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse, new TimeDedicated(3, 0, 6),
				"IFT-2901", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.TUESDAY)), null);
		ift2901Section.setCourseRepository(courseRepository);
		ift2901Section.setProgramSheetRepository(programSheetRepository);
		glo3013Section = new Section("98123", "A", "a responsable person", Arrays.asList("teacher1"), TeachMode.InCourse, new TimeDedicated(3, 0, 6),
				"GLO-3013", Arrays.asList(new TimeSlot(generateTimeSlotStartTime(), 3, DayOfWeek.THURSDAY)), null);
		glo3013Section.setCourseRepository(courseRepository);
		glo3013Section.setProgramSheetRepository(programSheetRepository);

		schedule = new Schedule("h2012");
		schedule.add(ift2002Section);
		schedule.add(ift2004Section);
		schedule.add(glo1901Section);
		schedule.add(glo1010Section);
		schedule.add(ift2901Section);
		schedule.add(glo3013Section);

		ConcomittingCoursesFilter concomittingCoursesFilter = new ConcomittingCoursesFilter();
		CourseLevelFilter coursesLevelFilter = new CourseLevelFilter();
		SameTeacherFilter sameTeacherFilter = new SameTeacherFilter();
		UnavailableTeacherFilter unavailableTeacherFilter = new UnavailableTeacherFilter();
		unavailableTeacherFilter.setRepository(availabilityRepository);
		Sink sink = new Sink();

		conflictDetector = new ConflictDetector();
		conflictDetector.setConcomittingCoursesFilter(concomittingCoursesFilter);
		conflictDetector.setCourseLevelFilter(coursesLevelFilter);
		conflictDetector.setSameTeacherFilter(sameTeacherFilter);
		conflictDetector.setUnavailableTeacherFilter(unavailableTeacherFilter);
		conflictDetector.setSink(sink);
	}

	@Test
	public void canProposeTimeSpotsForSectionForCourse() throws Exception {
		ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.setAvailabilityRepository(availabilityRepository);
		scheduleGenerator.setConflictDetector(conflictDetector);

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForCourses(glo2002Section, schedule);

		assertEquals(3, possibleTimeSlots.size());
	}

	@Test
	public void canProposeTimeSpotsForSectionForLab() throws Exception {
		ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.setAvailabilityRepository(availabilityRepository);
		scheduleGenerator.setConflictDetector(conflictDetector);

		List<TimeSlot> possibleTimeSlots = scheduleGenerator.proposeTimeSlotsForSectionForLab(glo2002Section, schedule);

		assertEquals(3, possibleTimeSlots.size());
		assertEquals(DayOfWeek.FRIDAY, possibleTimeSlots.get(0).getDayOfWeek());
		assertEquals(DayOfWeek.FRIDAY, possibleTimeSlots.get(1).getDayOfWeek());
		assertEquals(DayOfWeek.FRIDAY, possibleTimeSlots.get(2).getDayOfWeek());
	}

	@Test
	public void canGenerateASchedule() throws Exception {
		ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.setAvailabilityRepository(availabilityRepository);
		scheduleGenerator.setConflictDetector(conflictDetector);

		Schedule generatedSchedule = scheduleGenerator.generateSchedule(prepareSectionForScheduleGeneration());

		assertNotNull(generatedSchedule);
		assertEquals(7, generatedSchedule.getSectionsList().size());
		assertTrue(generatedSchedule.getConflicts().isEmpty());
		assertTrue(generatedSchedule.getSectionsList().contains(glo1010Section));
		assertTrue(generatedSchedule.getSectionsList().contains(glo1901Section));
		assertTrue(generatedSchedule.getSectionsList().contains(glo3013Section));
		assertTrue(generatedSchedule.getSectionsList().contains(ift2002Section));
		assertTrue(generatedSchedule.getSectionsList().contains(ift2004Section));
		assertTrue(generatedSchedule.getSectionsList().contains(ift2901Section));
		assertTrue(generatedSchedule.getSectionsList().contains(glo2002Section));
	}

	@Test(expected = FailedScheduleGenerationException.class)
	public void shouldThrowExceptionIfImpossibleToGenerateScheduleWithoutConflict() throws Exception {
		glo1010Section.setTeachers(Arrays.asList("unavailableTeacher"));

		ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.setAvailabilityRepository(availabilityRepository);
		scheduleGenerator.setConflictDetector(conflictDetector);

		scheduleGenerator.generateSchedule(prepareSectionForScheduleGeneration());
	}

	@After
	public void tearDown() throws Exception {
		courseRepository.clear();
	}

	private static void addTeacherAvailability(String teacherIdul, String json) throws Exception {
		availabilityModel = mapper.readValue(json, AvailabilityModel.class);
		availabilities = availabilityModel.toAvailability(teacherIdul);
		availabilityRepository.store(availabilities);
	}

	private Time generateTimeSlotStartTime() {
		Time startTime = new Time(A_HOUR, A_MINUTE);
		return startTime;
	}

	private List<Section> prepareSectionForScheduleGeneration() {
		List<Section> sections = new ArrayList<Section>();
		schedule.add(glo2002Section);
		for (Section section : schedule.getSectionsList()) {
			section.setCourseTimeSlots(new ArrayList<TimeSlot>());
			sections.add(section);
		}
		return sections;
	}
}
