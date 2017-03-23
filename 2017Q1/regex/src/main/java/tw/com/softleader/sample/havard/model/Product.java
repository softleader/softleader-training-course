package tw.com.softleader.sample.havard.model;

import java.time.YearMonth;
import java.util.Objects;

import tw.com.softleader.sample.havard.enums.CardType;

public class Product {

	private String productNo; // 完整產品編號
	private CardType cardType; // 卡別(A,B,C,E,D,F,M or null)
	private YearMonth yearMonth; // 年月
	private int seqNo; // 流水號
	private String batchNo; // 批號(A-Z-AA-ZZ or null)

	public Product(String productNo, CardType cardType, YearMonth yearMonth, Integer seqNo, String batchNo) {
		super();
		Objects.requireNonNull(productNo);
		Objects.requireNonNull(yearMonth);
		Objects.requireNonNull(seqNo);

		this.productNo = productNo;
		this.cardType = cardType;
		this.yearMonth = yearMonth;
		this.seqNo = seqNo;
		this.batchNo = batchNo;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", cardType=" + cardType + ", yearMonth=" + yearMonth + ", seqNo="
				+ seqNo + ", batchNo=" + batchNo + "]";
	}

	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public YearMonth getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
