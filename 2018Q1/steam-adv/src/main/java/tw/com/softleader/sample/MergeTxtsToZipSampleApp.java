package tw.com.softleader.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import tw.com.softleader.sample.collector.CustomCollectors;

public class MergeTxtsToZipSampleApp {

	/**
	 * 將resource/txts中所有的txt檔合併成一個ZIP檔
	 */
	public static void main(String[] args) throws Exception {
		try (final FileOutputStream outputStream = new FileOutputStream("D:/test.zip")) {
			Files.walk(Paths.get(MergeTxtsToZipSampleApp.class.getResource("/txts").toURI()))
					.map(Path::toFile)
					.filter(File::isFile)
					.filter(f -> f.getName().endsWith(".txt"))
					.collect(CustomCollectors.zipOut(outputStream, f -> f.getParentFile().getName() + "_" + f.getName()))
					.build();
		}
	}

}
