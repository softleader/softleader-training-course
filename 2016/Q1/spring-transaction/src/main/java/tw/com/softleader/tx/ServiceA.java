package tw.com.softleader.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// @Transactional
@Service
public class ServiceA {

	@Autowired
	private ServiceB b;

	public void save(int times) {
		System.out.println("a saved");
		for (int i = 0; i < times; i++) {
			try {
				b.saveNew(i);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
