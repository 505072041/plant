package uitc.com.plant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uitc.com.plant.dto.A1AddQtyReq;
import uitc.com.plant.dto.A1FindInventoryReq;
import uitc.com.plant.dto.A1FindInventoryResp;
import uitc.com.plant.dto.A1SaveFlowerReq;
import uitc.com.plant.dto.A1SaveInventoryReq;
import uitc.com.plant.dto.A1SaveStoreReq;
import uitc.com.plant.dto.A1SubtractQtyReq;
import uitc.com.plant.dto.A1getStorePhoneReq;
import uitc.com.plant.model.Flower;
import uitc.com.plant.model.Inventory;
import uitc.com.plant.model.Store;
import uitc.com.plant.service.FlowerService;
import uitc.com.plant.service.InventoryService;
import uitc.com.plant.service.StoreService;

@RestController
@RequestMapping("/a1")
public class A1Controller {
	@Autowired
	FlowerService flowerService;

	@Autowired
	StoreService storeService;

	@Autowired
	InventoryService inventoryService;

	// 存入此花
	@PostMapping("/saveFlower")
	public String saveFlower(
			@Valid @RequestBody A1SaveFlowerReq req
	) {
		// 檢查資料庫是否有這個flower name
		flowerService.checkFlowerName(req.getFlowerName());
		// 若沒有則轉乘model
		Flower flower = A1SaveFlowerReq.parse(req);
		// 存起來
		flower = flowerService.saveFlower(flower);
		return "成功 id:" + flower.getId();
	}

	// 存入此店
	@PostMapping("/saveStore")
	public String saveStore(
			@Valid @RequestBody A1SaveStoreReq req
	) {
		// 檢查Store是否存在
		storeService.checkStoreName(req.getStoreName());
		// 如果不存在 就轉換Model
		Store store = A1SaveStoreReq.parse(req);
		// Save Model
		store = storeService.saveStore(store);
		return "成功 id:" + store.getId();
	}

	// 輸入店名，如果存在就顯示電話號碼
	@PostMapping("/getStorePhone")
	public void getStorePhone(
			@RequestBody A1getStorePhoneReq req
	) {

		// 如果店名不存在 回傳Exception
		Store store = storeService.findByStoreName(req.getStoreName());
		// 如果店名存在，找出他的電話
		String phone = store.getPh();
		// sysout 電話
		System.out.println(phone);

	}

	// 減少庫存
	@PostMapping("/A1SubtractQty")
	public String A1SubtractQty(
			@Valid @RequestBody A1SubtractQtyReq req
	) {
		// 如果商品不存在 回傳Exception 如果商品存在 減去 qty
		Inventory invent = inventoryService.SubtractQty(req.getFlowerId(), req.getStoreId(), req.getQty());

		return "減去 qty 成功!! 目前庫存:" + invent.getQty();

	}

	// 輸入 店家Id 與 花Id 與想增加的庫存數量
	@PostMapping("/addQty")
	public String addQty(
			@Valid @RequestBody A1AddQtyReq req
	) {

		// 輸入 店家Id 與 花Id 與想增加的庫存數量
		Inventory invent = inventoryService.findByStoreIdAndFlowerIdToAddQty(req.getStoreId(), req.getFlowerId(),
				req.getQty());

		// 回傳此物件
		return "成功，庫存數量增加，總Total為: " + invent.getQty();

	}

	// Save Inventory
	@PostMapping("/addInventory")
	public String addInventory(
			@Valid @RequestBody A1SaveInventoryReq req
	) {
		// 檢查Inventory是否存在
		inventoryService.checkInventory(req.getStoreId(), req.getFlowerId());

		// 如果不存在 就轉換Model
		Inventory invent = A1SaveInventoryReq.parse(req);

		// save Inventory
		invent = inventoryService.saveInventory(invent);

		return "Inventory 存入成功，此花庫存: " + invent.getQty() + " id:" + invent.getId();
	}

	@PostMapping("/findInventory")
	public A1FindInventoryResp findInventory(
			@Valid @RequestBody A1FindInventoryReq req
	) {

		// 依ID查詢inventory table
		Page<Inventory> invs = inventoryService.findByStoreIdAndPageNo(req.getPageNo(), req.getPageSize(),
				req.getStoreId());
		// parse 回傳物件
		A1FindInventoryResp resp = A1FindInventoryResp.parse(invs);
		return resp;
	}

}
