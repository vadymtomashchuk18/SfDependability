package marsrovergame;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoverTest {

	@Test
	public void testLandingEmptyPosition() {
		Mars field = new Mars(10,10);
		Rover rover = new Rover("", field);
		String pos = rover.getPosition();
		assertEquals("(0,0,N)", pos);
	}

}
