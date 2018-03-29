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

import tw.com.softleader.sample.zip.Zipper;

public class ZipCollector implements Collector<File, Zipper, Zipper> {

	private Zipper zipper;

	public ZipCollector(OutputStream outputStream) {
		this.zipper = new Zipper(outputStream);
	}

	public ZipCollector(OutputStream outputStream, Function<File, String> naming) {
		this.zipper = new Zipper(outputStream, naming);
	}

	@Override
	public Supplier<Zipper> supplier() {
		return () -> zipper;
	}

	@Override
	public BiConsumer<Zipper, File> accumulator() {
		return Zipper::add;
	}

	@Override
	public BinaryOperator<Zipper> combiner() {
		return (zb1, zb2) -> {
			zb1.addAll(zb2);
			return zb1;
		};
	}

	@Override
	public Function<Zipper, Zipper> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Sets.newHashSet();
	}
}
