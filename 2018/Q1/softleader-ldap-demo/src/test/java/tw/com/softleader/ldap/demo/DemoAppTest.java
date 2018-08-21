package tw.com.softleader.ldap.demo;

import javax.naming.Name;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
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
		o.setDn(LdapNameBuilder.newInstance("o=technology department").build());
		organizationRepo.save(o);
	}
	
	@Test
	public void testCreatePerson() {
		Person p = new Person();
		// TODO 
		personRepo.save(p);
	}
	
	@Test
	public void testShowList() {
		treeOperations.showList(LdapNameBuilder.newInstance("o=technology department").build(), 2).forEach(System.out::println);
	}

	
	
	public static void main(String[] args) {
		Name name = LdapNameBuilder.newInstance("o=80230237").add("o=Taipei").add("o=R&D").add("uid=137").build();
		System.out.println("name: " + name);
		System.out.println();
		System.out.println("suffix0: " + name.getSuffix(0));
		System.out.println("suffix1: " + name.getSuffix(1));
		System.out.println("suffix2: " + name.getSuffix(2));
		System.out.println("suffix3: " + name.getSuffix(3));
		System.out.println("prefix0: " + name.getPrefix(0));
		System.out.println("prefix1: " + name.getPrefix(1));
		System.out.println("prefix2: " + name.getPrefix(2));
		System.out.println("prefix3: " + name.getPrefix(3));
		
		
		// TODO
		Assert.assertEquals(LdapNameBuilder.newInstance("o=Taipei").build(), name);
		
	}

}
