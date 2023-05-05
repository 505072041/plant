package uitc.com.plant.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class A1AddQtyReq {

	@NotNull
	@Min(0)
	private Integer storeId;

	@NotNull
	@Min(0)
	private Integer flowerId;

	@NotNull
	@Min(0)
	private Integer qty;
}
