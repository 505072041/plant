package uitc.com.plant.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import uitc.com.plant.repository.FlowerDAO;
import uitc.com.plant.repository.StoreDAO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlantApplication.class)
public class InventoryTest {

	// test date
	private Store s1,s2,s3;
	private Flower f1,f2,f3;
	
	@Autowired
	StoreDAO storeDAO;
	
	@Autowired
	FlowerDAO flowerDAO;
	
	

	@Before
	public void before() {

		s1 = new Store();
		s1.setAd("address");
		s1.setCloseTime(new Date());
		s1.setOpenTime(new Date());
		s1.setPh("0912345678");
		s1.setStoreName("name");

		s2 = new Store();
		s2.setAd("addresss");
		s2.setCloseTime(new Date());
		s2.setOpenTime(new Date());
		s2.setPh("09123456780");
		s2.setStoreName("namee");

		s3 = new Store();
		s3.setAd("addresss3");
		s3.setCloseTime(new Date());
		s3.setOpenTime(new Date());
		s3.setPh("091234567803");
		s3.setStoreName("namee3");
		
		
		f1 = new Flower();
		f1.setFlowerName("rose1");
		
		f2 = new Flower();
		f2.setFlowerName("rose2");
		
		f3 = new Flower();
		f3.setFlowerName("rose3");

	}

	@After
	public void after() {
		flowerDAO.deleteAll();
		storeDAO.deleteAll();
		
	}

	@Test
	public void testParse() {
		Inventory result = Inventory.parse(s1, f1, 1);
		Assert.assertNotNull(result);
		Assert.assertEquals(s1, result.getStore());
		Assert.assertEquals(f1, result.getFlower());
		Assert.assertEquals(Integer.valueOf(1), result.getQty());
	}
	
	//測試Store Parse
	@Test
	public void testParseStore() {

		Inventory inventory1 = Inventory.parse(s1, f1, 10);
		Inventory inventory2 = Inventory.parse(s2, f1, 20);
		Inventory inventory4 = Inventory.parse(null, null, 30);

		List<Inventory> inventories = Arrays.asList(inventory1, inventory2);

		List<Store> stores = Inventory.parseStore(inventories);

		Assert.assertNotNull(stores);
		Assert.assertEquals(2, stores.size());
		inventories = Arrays.asList(inventory1, inventory4);
		
		stores = Inventory.parseStore(inventories);

		Assert.assertEquals(1, stores.size());
		
		stores = Inventory.parseStore(null);	
	}
	
	
	//測試Flower Parse
	@Test
	public void testParseFlower() {
		
		Inventory invent1 = Inventory.parse(s1, f1, 1);
		Inventory invent2 = Inventory.parse(s2, f2, 1);
		Inventory invent3 = Inventory.parse(s3, f3, 1);
		
		List<Inventory> inventList = Arrays.asList(invent1, invent2, invent3);
		
		
		List<Flower> flower = Inventory.parseFlower(inventList);
		Assert.assertNotNull(flower);
		Assert.assertEquals(3, flower.size());
	}
	
}

