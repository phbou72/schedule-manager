package cours.ulaval.glo4003.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PrerequisiteTest {

	private static final String AN_ACRONYM_NOT_ADDED = "not_a_prerequisite";

	private static final String AN_ACRONYM = "anAcronym";

	private Prerequisite prerequisite;
	private List<String> acronyms = Arrays.asList(AN_ACRONYM);

	@Before
	public void setUp() {
		prerequisite = new Prerequisite();
	}

	@Test
	public void containsAcronymReturnTrueIfAcronymIsInside() {
		prerequisite.setAcronyms(acronyms);

		assertTrue(prerequisite.containsAcronym(AN_ACRONYM));
	}

	@Test
	public void canAddAnAccronym() {
		prerequisite.addAcronym(AN_ACRONYM);

		assertTrue(prerequisite.containsAcronym(AN_ACRONYM));
	}

}
