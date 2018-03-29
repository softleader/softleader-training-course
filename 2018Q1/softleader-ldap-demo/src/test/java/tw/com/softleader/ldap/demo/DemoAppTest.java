package tw.com.softleader.ldap.demo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import tw.com.softleader.tree.entity.Organization;
import tw.com.softleader.tree.entity.Person;
import tw.com.softleader.tree.ldap.base.TreeOperations;
import tw.com.softleader.tree.repository.repo.OrganizationRepo;
import tw.com.softleader.tree.repository.repo.PersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoAppTest {

	@Autowired
	private TreeOperations treeOperations;
	
	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Test
	@Ignore
	public void testCreateOrg() {
		Organization o = new Organization();
		organizationRepo.save(o);
	}
	
	@Test
	public void testCreatePerson() {
		Person p = new Person();

		personRepo.save(p);
	}
	
	@Test
	public void test() {
		
		treeOperations.showList(LdapNameBuilder.newInstance().build()).forEach(System.out::println);
	}

}
