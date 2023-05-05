package uitc.com.plant.service;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uitc.com.plant.PlantApplication;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Flower;
import uitc.com.plant.repository.FlowerDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlantApplication.class)
public class FlowerServiceTest {
	@Autowired
	FlowerService flowerService;

	@Autowired
	FlowerDAO flowerDAO;

	// test data
	private Flower f1, f2, f3, f4;

	@Before
	public void before() {

		f1 = new Flower();
		f2 = new Flower();
		f3 = new Flower();
		f4 = new Flower();

		f1.setFlowerName("F1Name1");
		f2.setFlowerName("F2Name");
		f3.setFlowerName("F3Name");
		f4.setFlowerName("F4Name");

		flowerDAO.saveAll(Arrays.asList(f1, f2, f3, f4));

	}

	@After
	public void after() {
		flowerDAO.deleteAll();
	}

	
	


	@Test(expected = BasdRequestException.class)
	public void testCheckFlowerNameWithException() {
		flowerService.checkFlowerName(f1.getFlowerName());
	}

	@Test(expected = BasdRequestException.class)
	public void testCheckFlowerName(){
		boolean result = flowerService.checkFlowerName(f1.getFlowerName());
		Assert.assertTrue(result); 
	}
	
}
