package tw.com.softleader.sample.data;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 員工打卡資料
 *
 * @author Rhys
 */
public class TimeClockEntity {

	private long id; // 資料ID
	private LocalDateTime timeIn; // 打卡上班時間
	private LocalDateTime timeOut; // 打卡下班時間
	private String employeeName; // 員工姓名
	private double hourilyRate; // 時薪(美金)

	public TimeClockEntity(long id, String timeIn, String timeOut, String employeeName,
						   double hourilyRate) {
		super();
		this.id = id;
		this.timeIn = LocalDateTime.parse(timeIn);
		this.timeOut = LocalDateTime.parse(timeOut);
		this.employeeName = employeeName;
		this.hourilyRate = hourilyRate;
	}

	public double calcSalary() {
		return Duration.between(this.getTimeIn(), this.getTimeOut()).toHours() * this.getHourilyRate();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(LocalDateTime timeIn) {
		this.timeIn = timeIn;
	}

	public LocalDateTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(LocalDateTime timeOut) {
		this.timeOut = timeOut;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getHourilyRate() {
		return hourilyRate;
	}

	public void setHourilyRate(double hourilyRate) {
		this.hourilyRate = hourilyRate;
	}

	@Override
	public String toString() {
		return employeeName + ": " + timeIn + " ~ " + timeOut + " (" + hourilyRate + "$)";
	}
}
