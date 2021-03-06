package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Semester;
import cours.ulaval.glo4003.domain.repository.ScheduleRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class ScheduleXMLRepository implements ScheduleRepository {

	private XMLSerializer<ScheduleXMLWrapper> serializer;
	private Map<String, Schedule> schedules = new HashMap<String, Schedule>();

	public ScheduleXMLRepository() throws Exception {
		serializer = new XMLSerializer<ScheduleXMLWrapper>(ScheduleXMLWrapper.class);
		parseXML();
	}

	@Override
	public List<Schedule> findAll() {
		return new ArrayList<Schedule>(schedules.values());
	}

	@Override
	public List<Schedule> findBy(String year) {
		List<Schedule> schedulesbyYear = new ArrayList<Schedule>();

		for (Schedule schedule : schedules.values()) {
			if (schedule.getYear().equals(year)) {
				schedulesbyYear.add(schedule);
			}
		}
		return schedulesbyYear;
	}

	@Override
	public String getId(String year, Semester semester) {
		return year + "-" + semester + "-" + (findBy(year).size() + 1);
	}

	@Override
	public void store(Schedule schedule) throws Exception {
		schedules.put(schedule.getId(), schedule);
		saveXML();
	}

	@Override
	public void delete(String id) throws Exception {
		if (schedules.containsKey(id)) {
			schedules.remove(id);
			saveXML();
		}
	}

	private void parseXML() throws Exception {
		List<Schedule> deserializedSchedules = serializer.deserialize(ConfigManager.getConfigManager().getSchedulesFilepath()).getSchedules();
		for (Schedule schedule : deserializedSchedules) {
			schedules.put(schedule.getId(), schedule);
		}

	}

	private void saveXML() throws Exception {
		ScheduleXMLWrapper scheduleXMLWrapper = new ScheduleXMLWrapper();
		scheduleXMLWrapper.setSchedules(new ArrayList<Schedule>(schedules.values()));
		serializer.serialize(scheduleXMLWrapper, ConfigManager.getConfigManager().getSchedulesFilepath());
	}

	protected void clearAll() throws Exception {
		schedules.clear();
		saveXML();
	}

	@Override
	public Schedule findById(String id) {
		return schedules.get(id);
	}

}
