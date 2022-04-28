package timer;

public class PeriodicTimer implements Timer {

	private int period;
	private int next;

	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}


	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}



	public int getPeriod() {
		return this.period;
	}


	@Override
	public Integer next() {

		int ret =  this.next;

		this.next = this.period;

		return ret;
	}



	@Override
	public boolean hasNext() {
		return true;
	}

}
