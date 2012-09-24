package cours.ulaval.glo4003.persistence;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import cours.ulaval.glo4003.model.CoursesPool;
import cours.ulaval.glo4003.repository.ICourseRetriever;

public class CourseBeanRetriever implements ICourseRetriever {

	private Unmarshaller unmarshaller;

	public CourseBeanRetriever() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CoursesPool.class);
		unmarshaller = context.createUnmarshaller();
	}

	@Override
	public CoursesPool getCourses() throws JAXBException {
		InputStream stream = CourseBeanRetriever.class
				.getResourceAsStream(ResourcesNames.COURSES_FILE);
		CoursesPool courses = (CoursesPool) unmarshaller.unmarshal(stream);

		return courses;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
