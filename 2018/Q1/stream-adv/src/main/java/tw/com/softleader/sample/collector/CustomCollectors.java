package tw.com.softleader.sample.collector;

import java.io.File;
import java.io.OutputStream;
import java.util.function.Function;

import tw.com.softleader.sample.collector.ZipCollector;

public class CustomCollectors {

	public static ZipCollector zipOut(OutputStream outputStream) {
		return new ZipCollector(outputStream);
	}

	public static ZipCollector zipOut(OutputStream outputStream, Function<File, String> naming) {
		return new ZipCollector(outputStream, naming);
	}

}
