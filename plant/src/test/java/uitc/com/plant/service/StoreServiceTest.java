package uitc.com.plant.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uitc.com.plant.PlantApplication;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Store;
import uitc.com.plant.repository.StoreDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlantApplication.class)
public class StoreServiceTest {
	
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	StoreDAO storeDAO; 
	
	private Store s1,s2,s3;
	
	@Before
	public void before() {
		
		s1= new Store();
		s2= new Store();
		s3= new Store();
		
		s1.setStoreName("S1Name");
		s2.setStoreName("S2Name");
		s3.setStoreName("S3Name");
		
		
		storeDAO.save(s1);
		storeDAO.save(s2);
		storeDAO.save(s3);
		
	}
	
	@After
	public void after() {
		storeDAO.deleteAll();
	}
	
	
	@Test(expected = BasdRequestException.class)
	public void testCheckStoreName() {
		storeService.checkStoreName("S1Name");
	}
	

}
