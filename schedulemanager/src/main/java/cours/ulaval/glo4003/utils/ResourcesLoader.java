package cours.ulaval.glo4003.utils;

import java.io.InputStream;

public class ResourcesLoader {

	@SuppressWarnings("rawtypes")
	public InputStream loadResource(Class aClass, String resourceName) {
		return aClass.getResourceAsStream(resourceName);
	}
}