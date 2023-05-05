package uitc.com.plant.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import lombok.Data;
import uitc.com.plant.model.Flower;

@Data
public class A1SaveFlowerReq {

	@NotBlank
	@NotNull
	@Length(min = 1, max = 50)
	private String flowerName;

	public static Flower parse(A1SaveFlowerReq req) {
		Flower result = new Flower();
		BeanUtils.copyProperties(req, result); 
		return result;

	} 

}
