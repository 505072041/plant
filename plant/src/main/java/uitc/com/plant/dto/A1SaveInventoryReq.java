package uitc.com.plant.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import uitc.com.plant.model.Flower;
import uitc.com.plant.model.Inventory;
import uitc.com.plant.model.Store;

@Data
public class A1SaveInventoryReq {

	@NotNull
	@Valid
	private Integer storeId;

	@NotNull
	@Valid
	private Integer flowerId;

	@NotNull
	@Min(1)
	@Max(20)
	private Integer qty;

	public static Inventory parse(
			A1SaveInventoryReq req
	) {
		Inventory result = new Inventory();
		result.setQty(req.qty);

		Flower flower = new Flower();
		flower.setId(req.getFlowerId());
		Store store = new Store();
		store.setId(req.getStoreId());

		result.setFlower(flower);
		result.setStore(store);
		return result;

	}
}
