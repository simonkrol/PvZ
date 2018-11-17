package model;

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