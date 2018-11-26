package controller;
/**
 * Class calls all JUnit Controller tests
 * @author Shaun Gordon
 * @version Nov 25, 2018
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({
TestController.class,//Put the name of the test class in here
})

public class Tests 
{
	
}
