package tw.com.softleader.sample.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

import tw.com.softleader.commons.function.Unchecked;

public class ZipBuilder extends ArrayList<File> {

	private final ZipOutputStream zipOutputStream;
	private final Function<File, String> naming;

	public ZipBuilder(OutputStream outputStream) {
		this.zipOutputStream = new ZipOutputStream(outputStream);
		this.naming = File::getName;
	}

	public ZipBuilder(OutputStream outputStream, Function<File, String> naming) {
		this.zipOutputStream = new ZipOutputStream(outputStream);
		this.naming = naming;
	}

	public void build() {
		this.forEach(Unchecked.accept(f -> {
			final String fileName = naming.apply(f);
			System.out.println("putNextEntry " + fileName);
			zipOutputStream.putNextEntry(new ZipArchiveEntry(f, fileName));
			IOUtils.copy(new FileInputStream(f), zipOutputStream);
			zipOutputStream.closeEntry();
		}));
	}

}
