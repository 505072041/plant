package uitc.com.plant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flower_id")
	private Flower flower;

	@Column(name = "qty")
	private Integer qty;

	public static Inventory parse(
			Store store,
			Flower flower,
			Integer qty
	) {
		Inventory result = new Inventory();
		result.setFlower(flower);
		result.setQty(qty);
		result.setStore(store);
		return result;
	}

	public static List<Store> parseStore(
			List<Inventory> inventories
	) {

		// check null and size
		if (inventories == null || inventories.size() == 0) {
			return new ArrayList<>();
		}

		List<Store> stores = new ArrayList<>();
		// lulala
		for (Inventory inventory : inventories) { // 1
			// chech if is null
			Store store = inventory.getStore();
			if (store == null) {
				continue;
			}
			stores.add(store);
		}

		return stores;
	}

	public static List<Flower> parseFlower(
			List<Inventory> inventories
	) {

		List<Flower> flowerBox = new ArrayList<>();

		for (Inventory i : inventories) {
			Flower f = i.getFlower();
			flowerBox.add(f);
		}

		return flowerBox;

	}

}
