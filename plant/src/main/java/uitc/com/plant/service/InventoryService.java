package uitc.com.plant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Flower;
import uitc.com.plant.model.Inventory;
import uitc.com.plant.model.InventoryLog;
import uitc.com.plant.repository.InventoryDao;
import uitc.com.plant.repository.InventoryLogDao;

@Slf4j
@Service
public class InventoryService {

	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	InventoryLogDao inventoryLogDao;

	public List<Inventory> findAll() {
		// select * from inventory
		List<Inventory> inventories = inventoryDao.findAll();
		return inventories;
	}

	@Transactional
	public Inventory findByIdTest(
			Long id,
			Integer qty
	) {

		Optional<Inventory> result = inventoryDao.findById(id);

		if (!result.isPresent()) {
			throw new BasdRequestException("not exist");
		}

		result.get().setQty(qty);
		return result.get();
	}

	@Transactional
	public Inventory findByIdTest2(
			Long id,
			uitc.com.plant.model.Store store,
			Flower flower
	) {

		inventoryDao.deleteById(id);

		Inventory inventory = new Inventory();

		inventory.setStore(store);
		inventory.setFlower(flower);
		return inventoryDao.save(inventory);

	}

	@Transactional
	public Inventory findAndSubtractQty(
			Long id,
			Integer qty
	) {

		Optional<Inventory> invent = inventoryDao.findById(id);

		if (!invent.isPresent()) {
			throw new BasdRequestException("not exist");
		}

		Integer originalQty = invent.get().getQty();

		Integer tempQty = originalQty - qty;

		if (tempQty < 0) {
			throw new BasdRequestException("qty is not enough");
		}

		invent.get().setQty(tempQty);
		InventoryLog info = new InventoryLog();
		info.setMessage("庫存ID:" + id + " 扣 " + qty);
		inventoryLogDao.save(info);

		return invent.get();

	}

	@Transactional
	public Inventory SubtractQty(
			Integer storeId,
			Integer flowerId,
			Integer qty

	) {

		Optional<Inventory> invent = inventoryDao.findByStoreIdAndFlowerId(storeId, flowerId);

		log.debug("FID:{}, SID:{}, inventqty:{}", flowerId, storeId, invent.get().getQty());

		if (!invent.isPresent()) {
			throw new BasdRequestException("this plant not exist");
		}

		Integer originalQty = invent.get().getQty();

		Integer tempQty = originalQty - qty;

		if (tempQty < 0) {
			throw new BasdRequestException("Qty is not enough");
		}

		invent.get().setQty(tempQty);
		InventoryLog info = new InventoryLog();
		info.setMessage("庫存ID:" + invent.get().getId() + " 扣 " + qty);
		inventoryLogDao.save(info);

		return invent.get();

	}

	/**
	 * 
	 * 輸入 店家Id 與 花Id 與想增加的庫存數量
	 * 
	 * @param storeId
	 * @param flowerId
	 * @return
	 */
	@Transactional
	public Inventory findByStoreIdAndFlowerIdToAddQty(
			Integer storeId,
			Integer flowerId,
			Integer qty
	) {
		Optional<Inventory> inventO = inventoryDao.findByStoreIdAndFlowerId(storeId, flowerId);

		if (!inventO.isPresent()) {
			throw new BasdRequestException(" No data is found");
		}

		Inventory invent = inventO.get();
		Integer temp = invent.getQty() + qty;
		invent.setQty(temp);

		InventoryLog info = new InventoryLog();
		info.setMessage("庫存現在增加為:" + temp);

		return invent;
	}

	@Transactional
	public Inventory saveInventory(
			Inventory invent
	) {
		return inventoryDao.save(invent);
	}

	@Transactional
	public void checkInventory(
			Integer storeId,
			Integer flowerId
	) {
		Optional<Inventory> invent = inventoryDao.findByStoreIdAndFlowerId(storeId, flowerId);
		if (invent.isPresent()) {
			throw new BasdRequestException("this Inventory  existed");
		}

	}

	public Page<Inventory> findByStoreIdAndPageNo(
			Integer pageNo,
			Integer pageSize,
			Integer storeId
	) {

		// check page no
		if (pageNo == null) {
			pageNo = 1;
		}

		// check page size
		if (pageSize == null) {
			pageSize = 10;
		}

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		log.debug("pageable:{}", pageable);
		return inventoryDao.findByStoreIdOrderByFlowerIdAsc(pageable, storeId);
	}
}
