package cours.ulaval.glo4003.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cours.ulaval.glo4003.model.Course;

public class CourseRepository {

	@Autowired
	@Qualifier("CourseBeanRetriever")
	private ICourseRetriever courseRetriever;

	public void setCourseRetriever(ICourseRetriever courseRetriever) {
		this.courseRetriever = courseRetriever;
	}

	public ICourseRetriever getCourseRetriever() {
		return courseRetriever;
	}

	public Collection<Course> getAll() {
		return courseRetriever.getCourses();
	}
}