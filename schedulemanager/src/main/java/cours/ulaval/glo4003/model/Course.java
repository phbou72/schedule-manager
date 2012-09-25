package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Course {

	private String acronym;
	private String title;
	private Integer credits;
	private String description;
	private Cycle cycle;
	private List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Integer getCredits() {
		return credits;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Prerequisite> prerequisites) {
		this.prerequisites = prerequisites;
	}
}
