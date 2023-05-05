package uitc.com.plant.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import uitc.com.plant.model.Store;


@Data
public class A1SaveStoreReq {
	
	@NotBlank
	@NotNull
	private String storeName;

	private String ph;

	private String ad;

	private Date openTime;

	private Date closeTime;
	
	
	public static Store parse(A1SaveStoreReq req) {
		Store store = new Store();
		BeanUtils.copyProperties(req, store);
		return store;
	}
	

}
