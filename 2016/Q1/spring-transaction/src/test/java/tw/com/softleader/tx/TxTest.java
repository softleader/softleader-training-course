package tw.com.softleader.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-tx.xml"})
public class TxTest {

	@Autowired
	private ServiceA a;

	@Test
	public void test() {
		a.save(1);
	}

}
