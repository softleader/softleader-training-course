package tw.com.softleader.trainingexception.base.validation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import lombok.Getter;
import tw.com.softleader.trainingexception.base.web.Msg;

public class Validations {

	/**
	 * 有些 List 的方法不想讓外面呼叫到，因此用隱含而非繼承的方式
	 */
	private final List<Validation> validations;

	/**
	 * 所有的 Validation 中，ValidationType 最嚴重的那個
	 */
	@Getter
	private ValidationType finalType = ValidationType.SUCCESS;

	public Validations() {
		this.validations = Lists.newArrayList();
	}

	public Validations(Validation... elements) {
		this.validations = Lists.newArrayList(elements);
		Stream.of(elements)
				.map(Validation::getType)
				.min(Comparator.comparing(ValidationType::getLevel))
				.ifPresent(type -> this.finalType = type);
	}

	public List<Msg> unPassToMsg() {
		return validations.stream()
				.filter(v -> !v.isPass())
				.map(Validation::getMsg)
				.collect(Collectors.toList());
	}

	public boolean isAllow(ValidationType excepted) {
		return finalType.isGe(excepted);
	}

	public boolean add(Validation validation) {
		final ValidationType currentType = validation.getType();
		this.finalType = currentType.isLt(this.finalType) && !validation.isPass() ? currentType : this.finalType;
		return validations.add(validation);
	}

	public boolean addAll(Validations validations) {
		final ValidationType currentType = validations.getFinalType();
		this.finalType = currentType.isLt(this.finalType) ? currentType : this.finalType;
		return validations.addAll(validations);
	}

}
