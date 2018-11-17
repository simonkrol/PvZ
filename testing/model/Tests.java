package model;
/**
 * Class calls all JUnit tests
 * @author Simon Krol, Shaun Gordon
 * @version Nov 16, 2018
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ //Put the name of the test class in here
	TestZombie.class,
	TestLane.class,
	TestLevel.class,
	TestSpot.class,
	TestPlant.class,
})

public class Tests
{


}