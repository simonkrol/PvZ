package view;
/**
 * Class calls all JUnit view tests
 * @author Shaun Gordon
 * @version Nov 25, 2018
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses
({
	TestView.class,
})

public class Tests 
{
	
}
