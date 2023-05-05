package uitc.com.plant.dto.common;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import uitc.com.plant.model.Inventory;

@Data
public class InventoryDto {

	private Integer storeId;
	private String storeName;
	private String flowerName;
	private Integer qty;

	public static InventoryDto parse(
			Inventory inventory
	) {
		InventoryDto result = new InventoryDto();
		result.setStoreId(inventory.getStore().getId());
		result.setFlowerName(inventory.getFlower().getFlowerName());
		result.setQty(inventory.getQty());
		result.setStoreName(inventory.getStore().getStoreName());
		return result;
	}

	public static List<InventoryDto> parse(
			List<Inventory> inventories
	) {
		return inventories.stream().map(a -> parse(a)).collect(Collectors.toList());
	}

}
