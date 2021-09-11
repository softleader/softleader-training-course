package tw.com.softleader.training.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class TimerUtils {
	
	public static LocalDateTime start() {
		return LocalDateTime.now();
	}
	
	public static Duration finish(final LocalDateTime startTime) {
		return Duration.between(startTime, LocalDateTime.now());
	}
	
	/**
	 * 將一個方法run n次, 取出總經過時間
	 */
	public static Duration runNTimesCost(Callable<?> task, int Times) {
		final LocalDateTime startTime = start();
		
		try {
			for (int i = 0; i < Times; i++) {
				task.call();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finish(startTime);
	}

}
