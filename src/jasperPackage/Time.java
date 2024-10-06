package jasperPackage;

public class Time {
	int hour;
	int minute;

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public boolean isBefore(Time time) {
		if (this.hour < time.hour) {
			return true;
		} else if (this.hour == time.hour) {
			if (this.minute < time.minute) {
				return true;
			}
		}
		return false;
	}

	public boolean isAfter(Time time) {
		if (this.hour > time.hour) {
			return true;
		} else if (this.hour == time.hour) {
			if (this.minute > time.minute) {
				return true;
			}
		}
		return false;
	}

	public boolean isBetween(Time start, Time end) {
		if (this.isAfter(start) && this.isBefore(end)) {
			return true;
		}
		return false;
	}

	public boolean isEqual(Time time) {
		if (this.hour == time.hour && this.minute == time.minute) {
			return true;
		}
		return false;
	}

	public String toString() {
		return String.format("%02d:%02d", hour, minute);
	}
}
