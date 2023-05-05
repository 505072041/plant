package uitc.com.plant.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import uitc.com.plant.dto.common.InventoryDto;
import uitc.com.plant.dto.common.PageInfo;
import uitc.com.plant.model.Inventory;
import uitc.com.plant.service.InventoryService;

@Data
@Slf4j
public class A1FindInventoryResp {

	private List<InventoryDto> inventories;
	private PageInfo pageInfo;

	public static A1FindInventoryResp parse(
			Page<Inventory> invs
	) {

		List<Inventory> pageInv = invs.getContent();
		log.debug("pageInv:{}", pageInv);
		
		List<InventoryDto> inventoryDtos = InventoryDto.parse(pageInv);
		log.debug("inventoryDtos:{}", inventoryDtos);
		
		PageInfo pageInfo = PageInfo.parse(invs);
		log.debug("pageInfo:{}", pageInfo);
		A1FindInventoryResp result = new A1FindInventoryResp();
		
		result.setInventories(inventoryDtos);
		result.setPageInfo(pageInfo);
		log.debug("result:{}", result);
		return result;
	}

}
