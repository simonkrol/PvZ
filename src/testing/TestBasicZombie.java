package testing;
import model.BasicZombie;
import model.Lane;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestBasicZombie
{
	BasicZombie peter;
	Lane testLane;
	public void setUp() throws Exception
	{
		testLane = new Lane();
		peter = new BasicZombie(testLane);
	}
	@Test
	public void test()
	{
		fail("Not yet implemented");
	}
	

}
