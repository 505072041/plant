package uitc.com.plant.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class A1getStorePhoneReq {

	@NotBlank
	@NotNull
	@Length(min = 1, max = 50)
	private String storeName;

}
