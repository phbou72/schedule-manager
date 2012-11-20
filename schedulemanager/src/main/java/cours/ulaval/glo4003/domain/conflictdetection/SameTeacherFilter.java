package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;

public class SameTeacherFilter extends Filter {

	@Override
	public void run(Schedule schedule) {
		List<Section> sections = schedule.getSectionsList();

		for (int i = 0; i < sections.size(); i++) {
			Section section = sections.get(i);
			for (String teacher : section.getTeachers()) {
				for (int j = i + 1; j < sections.size(); j++) {
					Section secondSection = sections.get(j);
					if (secondSection.hasTeacher(teacher)) {
						schedule.addAll(generateSameTeacherConflicts(section, secondSection, teacher));
					}
				}
			}
		}
		nextPipe(schedule);
	}

	private List<Conflict> generateSameTeacherConflicts(Section section, Section otherSection, String teacher) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		for (TimeSlot sectionTimeSlots : section.getCoursesAndLabTimeSlots()) {
			for (TimeSlot otherSectionTimeSlots : otherSection.getCoursesAndLabTimeSlots()) {
				if (sectionTimeSlots.isOverlapping(otherSectionTimeSlots)) {
					SameTeacherConflict conflict = new SameTeacherConflict(section.getNrc(), otherSection.getNrc(), teacher,
							sectionTimeSlots, otherSectionTimeSlots);
					conflicts.add(conflict);
				}
			}
		}
		return conflicts;
	}

	@Override
	public void nextPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}
}
