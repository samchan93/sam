package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase {

	@Test
	public void testSaveForm() {
		assertNotNull(new SaveModel("12/12/12","1.7","50","200","20"));
	}
	
	@Test
	public void testMealForm() {
		assertNotNull(new MealModel("12/12/12","breakfast-bread","500"));
	}
	
	@Test
	public void testExerciseForm() {
		assertNotNull(new ExerciseModel("12/12/12","breakfast-bread","1200-1230","300"));
	}

}
