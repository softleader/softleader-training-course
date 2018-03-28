package tw.com.softleader.ldap.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import tw.com.softleader.tree.entity.Organization;
import tw.com.softleader.tree.repository.repo.OrganizationRepo;
import tw.com.softleader.tree.repository.repo.PersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoAppTest {

	@Autowired
	private PersonRepo PersonRepo;

	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Test
	public void test() {
		Organization o = new Organization();
		o.setDn(LdapNameBuilder.newInstance("o=technology deplarment").build());
		organizationRepo.save(o);
	}

}
