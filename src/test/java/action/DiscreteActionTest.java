package action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timer.PeriodicTimer;
import timer.Timer;

class DiscreteActionTest {

	private DiscreteAction da;
	private String o = new String("Toto");
	private Timer t = new PeriodicTimer(5);
	@BeforeEach
	void setUp() throws Exception {
		da = new DiscreteAction(o, "toString", t);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertSame(o, da.getObject());
		assertEquals("toString", da.getMethod().getName());
		assertNull(da.getCurrentLapsTime());

		da.next();
		assertSame(o, da.getObject());
		assertEquals("toString", da.getMethod().getName());
		assertEquals(5, da.getCurrentLapsTime());

		da.spendTime(2);
		assertSame(o, da.getObject());
		assertEquals("toString", da.getMethod().getName());
		assertEquals(3, da.getCurrentLapsTime());
	}

}
