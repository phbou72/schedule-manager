package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cours.ulaval.glo4003.controller.model.utils.TimeSlotComparator;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;

public class CalendarModel {

	private ScheduleInformationModel scheduleInfo;

	private List<CourseSlotModel> monday = new ArrayList<CourseSlotModel>();
	private List<CourseSlotModel> tuesday = new ArrayList<CourseSlotModel>();
	private List<CourseSlotModel> wednesday = new ArrayList<CourseSlotModel>();
	private List<CourseSlotModel> thursday = new ArrayList<CourseSlotModel>();
	private List<CourseSlotModel> friday = new ArrayList<CourseSlotModel>();

	public CalendarModel(Schedule schedule) {
		scheduleInfo = new ScheduleInformationModel(schedule);

		for (Section section : schedule.getSectionsList()) {
			for (TimeSlot timeSlot : section.getCoursesAndLabTimeSlots()) {
				addToList(section, timeSlot, false);
			}
			if (section.getLabTimeSlot() != null) {
				addToList(section, section.getLabTimeSlot(), true);
			}
		}
		sort();
	}

	private void sort() {
		Collections.sort(monday, new TimeSlotComparator());
		Collections.sort(tuesday, new TimeSlotComparator());
		Collections.sort(wednesday, new TimeSlotComparator());
		Collections.sort(thursday, new TimeSlotComparator());
		Collections.sort(friday, new TimeSlotComparator());
	}

	private void addToList(Section section, TimeSlot timeSlot, boolean isLab) {
		switch (timeSlot.getDayOfWeek()) {
		case MONDAY:
			monday.add(new CourseSlotModel(section, timeSlot, isLab));
			break;
		case TUESDAY:
			tuesday.add(new CourseSlotModel(section, timeSlot, isLab));
			break;
		case WEDNESDAY:
			wednesday.add(new CourseSlotModel(section, timeSlot, isLab));
			break;
		case THURSDAY:
			thursday.add(new CourseSlotModel(section, timeSlot, isLab));
			break;
		case FRIDAY:
			friday.add(new CourseSlotModel(section, timeSlot, isLab));
			break;
		default:
			break;
		}
	}

	public ScheduleInformationModel getScheduleInfo() {
		return scheduleInfo;
	}

	public void setScheduleInfo(ScheduleInformationModel scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}

	public List<CourseSlotModel> getMonday() {
		return monday;
	}

	public void setMonday(List<CourseSlotModel> monday) {
		this.monday = monday;
	}

	public List<CourseSlotModel> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<CourseSlotModel> tuesday) {
		this.tuesday = tuesday;
	}

	public List<CourseSlotModel> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<CourseSlotModel> wednesday) {
		this.wednesday = wednesday;
	}

	public List<CourseSlotModel> getThursday() {
		return thursday;
	}

	public void setThursday(List<CourseSlotModel> thursday) {
		this.thursday = thursday;
	}

	public List<CourseSlotModel> getFriday() {
		return friday;
	}

	public void setFriday(List<CourseSlotModel> friday) {
		this.friday = friday;
	}

}
