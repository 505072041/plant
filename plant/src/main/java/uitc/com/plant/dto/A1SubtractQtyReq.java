package uitc.com.plant.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class A1SubtractQtyReq {

	
	@NotNull
	private Integer storeId;
	
	
	@NotNull
	private Integer flowerId;
	

	@NotNull
	private Integer qty;

}
