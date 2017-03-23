package tw.com.softleader.sample.havard;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

public class CardParser {

	public static void main(String[] args) {

		try (Stream<String> productNos = Files.lines(new File(CardParser.class.getResource("/ex-harvard_product_no.txt").toURI()).toPath())) {
			// TODO 將不合邏輯的產品編號篩選並sysout出來
			// TODO 將productNo轉成Product物件

			// ex:
			// Pattern pattern = Pattern.compile(regex); // 編譯pattern物件
			// Matcher matcher = pattern.matcher(input text) // 表達式比對物件
			// matcher.matches(); // 進行比對(true=符合, false=失敗)
			// matcher.group(); // 取得比對符合的完整字串
			// matcher.group(group idx); // 取得塞入變數的字串

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

}
