package uitc.com.plant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import uitc.com.plant.PlantApplication;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Flower;
import uitc.com.plant.model.Inventory;
import uitc.com.plant.model.Store;
import uitc.com.plant.repository.FlowerDAO;
import uitc.com.plant.repository.InventoryDao;
import uitc.com.plant.repository.InventoryLogDao;
import uitc.com.plant.repository.StoreDAO;

@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlantApplication.class)
public class InventoryServiceTest {
	@Autowired
	InventoryService inventoryService;

	@Autowired
	FlowerDAO flowerDAO;
	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	StoreDAO storeDAO;
	@Autowired
	InventoryLogDao inventoryLogDao;

	// test data
	private Flower f1, f2, f3, f4;
	private Store s1, s2, s3;
	private Inventory i1, i2, i3;

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

		s1 = new Store();
		s2 = new Store();
		s3 = new Store();

		s1.setStoreName("S1Name");
		s2.setStoreName("S2Name");
		s3.setStoreName("S3Name");

		storeDAO.save(s1);
		storeDAO.save(s2);
		storeDAO.save(s3);

		i1 = new Inventory();
		i1.setFlower(f1);
		i1.setStore(s1);
		i1.setQty(20);

		i2 = new Inventory();
		i2.setFlower(f2);
		i2.setStore(s1);
		i2.setQty(20);

		i3 = new Inventory();
		i3.setFlower(f3);
		i3.setStore(s1);
		i3.setQty(20);

		inventoryDao.saveAll(Arrays.asList(i1, i2, i3));

	}

	@After
	public void after() {
		inventoryDao.deleteAll();
		flowerDAO.deleteAll();
		storeDAO.deleteAll();
	}

	@Test
	public void testFindAll() {
		log.debug("==============================");
		inventoryService.findAll();
		log.debug("==============================");
	}

	@Test
	public void testFindByIdTest() {
		log.debug("==============================");
		inventoryService.findByIdTest2(i1.getId(), s1, f1);
		log.debug("==============================");
	}

	@Test
	public void testFindAndSubtractQty() {
		log.debug("==============================");
		Inventory result = inventoryService.findAndSubtractQty(i1.getId(), 1);
		Assert.assertEquals(Integer.valueOf(19), result.getQty());
		log.debug("==============================");
	}

	@Test
	public void testSubQty() {
		Inventory result = inventoryService.SubtractQty(s1.getId(), f1.getId(), 1);
		Assert.assertNotNull(result);
		Assert.assertEquals(Integer.valueOf(19), result.getQty());
	}

	@Test
	public void testFindByStoreIdAndFlowerIdToAllQty() {
		Inventory result = inventoryService.findByStoreIdAndFlowerIdToAddQty(s1.getId(), f1.getId(), 30);
		Assert.assertNotNull(result);
		Assert.assertEquals(Integer.valueOf(50), result.getQty());
	}

	@Test(expected = BasdRequestException.class)
	public void testCheckInventory() {
		inventoryService.checkInventory(s1.getId(), f1.getId());
	}

	@Test
	@Repeat(5)
	public void testMultiFinadAndSubtract() throws InterruptedException { //Error

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 20; i++) {
			TestProc proc = new TestProc(inventoryService, i1.getId());
			Thread thread = new Thread(proc);
			threads.add(thread);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}

		Optional<Inventory> result = inventoryDao.findById(i1.getId());
		Assert.assertEquals(Integer.valueOf(0), result.get().getQty());
	}

	@Test
	@Repeat(5)
	public void testMultiSubQty() throws InterruptedException {  //Error

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 20; i++) {
			TestProcc proc = new TestProcc(inventoryService, i2.getStore().getId(), i2.getFlower().getId(), 1);
			Thread thread = new Thread(proc);
			threads.add(thread);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}

		Optional<Inventory> result = inventoryDao.findById(i2.getId());

		Assert.assertEquals(Integer.valueOf(0), result.get().getQty());

	}

	@Test
	@Repeat(30)
	public void testFindByStoreIdAndFlowerIdThread() throws InterruptedException {

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 50; i++) {
			TestAddQty test = new TestAddQty(inventoryService, s1.getId(), f1.getId(), 2); // 50個人同時加2次
																							// =100
			Thread thread = new Thread(test);
			threads.add(thread);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}

		Optional<Inventory> result = inventoryDao.findById(i1.getId());
		Assert.assertEquals(Integer.valueOf(120), result.get().getQty());
	}

}

class TestProc implements Runnable {
	private InventoryService service;
	private Long id;

	@Override
	public void run() {
		service.findAndSubtractQty(id, 1);

	}

	public TestProc(
			InventoryService service,
			Long id
	) {
		this.service = service;
		this.id = id;
	}

}

class TestProcc implements Runnable {
	private InventoryService service;
	private Integer storeId;
	private Integer flowerId;
	private Integer qty;

	@Override
	public void run() {

		service.SubtractQty(storeId, flowerId, qty);

	}

	public TestProcc(
			InventoryService service,
			Integer storeId,
			Integer flowerId,
			Integer qty
	) {
		this.service = service;
		this.storeId = storeId;
		this.flowerId = flowerId;
		this.qty = qty;

	}

}

class TestAddQty implements Runnable {
	private InventoryService service;
	private Integer storeId;
	private Integer flowerId;
	private Integer qty;

	@Override
	public void run() {
		service.findByStoreIdAndFlowerIdToAddQty(storeId, flowerId, qty);
	}

	public TestAddQty(
			InventoryService service,
			Integer storeId,
			Integer flowerId,
			Integer qty
	) {
		this.service = service;
		this.storeId = storeId;
		this.flowerId = flowerId;
		this.qty = qty;

	}
}
