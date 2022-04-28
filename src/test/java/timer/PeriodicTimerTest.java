package timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PeriodicTimerTest {

	private PeriodicTimer t1;
	private PeriodicTimer t2;

	@BeforeEach
	void setUp() throws Exception {
		this.t1 = new PeriodicTimer(3);
		this.t2 = new PeriodicTimer(2, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPeriodicTimerInt() {
		assertEquals(3, this.t1.getPeriod());
		assertEquals(3,this.t1.next());
		assertTrue(this.t1.hasNext());
	}

	@Test
	void testPeriodicTimerIntInt() {
		assertEquals(2, this.t2.getPeriod());
		assertEquals(3,this.t2.next());
		assertEquals(2,this.t2.next());
		assertTrue(this.t2.hasNext());
	}

}
