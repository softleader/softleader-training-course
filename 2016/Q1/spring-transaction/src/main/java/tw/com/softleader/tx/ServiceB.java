package tw.com.softleader.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// @Transactional
@Service
public class ServiceB {

	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveNew(int i) {
		save(i);
	}

	public void save(int i) {
		System.out.println("b-" + i + " saved");
		System.out.println(i / 0);
	}

}
