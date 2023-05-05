package uitc.com.plant.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class A1FindInventoryReq {

	@NotNull
	@Min(0)
	private Integer storeId;

	@Min(1)
	private Integer pageNo;

	@Min(1)
	private Integer pageSize;

}
