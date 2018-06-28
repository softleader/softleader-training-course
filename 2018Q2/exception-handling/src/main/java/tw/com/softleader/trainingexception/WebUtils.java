package tw.com.softleader.trainingexception;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebUtils {

	public static String getMessage(final Throwable t) {
		// 但當發生NullPointer等比較單純的Exception的時候, 其message有可能為null而拋錯
		return Optional.ofNullable(t.getMessage()).orElse(t.getClass().getSimpleName() + ": " +
				Stream.of(t.getStackTrace())
						.filter(st -> st.getClassName().startsWith("com.fubon.psb"))
						.map(StackTraceElement::toString)
						.collect(Collectors.joining("\n"))
		);
	}

}
