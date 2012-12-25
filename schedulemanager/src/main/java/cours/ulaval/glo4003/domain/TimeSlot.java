package cours.ulaval.glo4003.domain;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TimeSlot {

	private Time startTime;
	private Time endTime;
	private DayOfWeek dayOfWeek;
	private Integer duration;

	public TimeSlot() {
		this.startTime = new Time();
		this.endTime = new Time();
	}

	public TimeSlot(Time startTime, Time endTime, DayOfWeek dayOfWeek) {
		this(startTime, endTime.getHour() - startTime.getHour(), dayOfWeek);
	}

	public TimeSlot(Time startTime, Integer duration, DayOfWeek dayOfWeek) {
		this.startTime = startTime;
		this.duration = duration;
		this.dayOfWeek = dayOfWeek;

		calculateEndTime();
	}

	private void calculateEndTime() {
		endTime = new Time(startTime.getHour(), startTime.getMinute());
		endTime.addHours(duration);
	}

	public boolean isOverlapping(TimeSlot timeslot) {
		if (dayOfWeek != timeslot.dayOfWeek) {
			return false;
		} else if (endTime.before(timeslot.getStartTime()) || endTime.equals(timeslot.getStartTime())) {
			return false;
		} else if (startTime.after(timeslot.getEndTime()) || startTime.equals(timeslot.getEndTime())) {
			return false;
		}

		return true;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public TimeSlot clone() {
		TimeSlot newTimeSlot = new TimeSlot();

		try {
			BeanUtils.copyProperties(newTimeSlot, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newTimeSlot;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
