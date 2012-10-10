package cours.ulaval.glo4003.domain;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
	private String id;
	private String year;
	private String season;
	private Map<String, Section> sections;
	private String personInCharge;

	public Schedule() {
	}

	public Schedule(String id) {
		this.id = id;
		sections = new HashMap<String, Section>();
	}

	public void add(Section section) {
		if (!sectionExist(section)) {
			sections.put(section.getNrc(), section);
		}
	}

	private boolean sectionExist(Section section) {
		return sections.containsKey(section.getNrc());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Map<String, Section> getSections() {
		return sections;
	}

	public void setSections(Map<String, Section> sections) {
		this.sections = sections;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

}
