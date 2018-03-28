package tw.com.softleader.sample.collector;

import java.io.File;
import java.io.OutputStream;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.Sets;

import tw.com.softleader.sample.zip.ZipBuilder;

public class ZipCollector implements Collector<File, ZipBuilder, ZipBuilder> {

	private ZipBuilder zipBuilder;

	public ZipCollector(OutputStream outputStream) {
		this.zipBuilder = new ZipBuilder(outputStream);
	}

	public ZipCollector(OutputStream outputStream, Function<File, String> naming) {
		this.zipBuilder = new ZipBuilder(outputStream, naming);
	}

	@Override
	public Supplier<ZipBuilder> supplier() {
		return () -> zipBuilder;
	}

	@Override
	public BiConsumer<ZipBuilder, File> accumulator() {
		return ZipBuilder::add;
	}

	@Override
	public BinaryOperator<ZipBuilder> combiner() {
		return (zb1, zb2) -> {
			zb1.addAll(zb2);
			return zb1;
		};
	}

	@Override
	public Function<ZipBuilder, ZipBuilder> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Sets.newHashSet();
	}
}
