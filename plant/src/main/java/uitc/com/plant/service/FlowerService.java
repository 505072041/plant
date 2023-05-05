package uitc.com.plant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Flower;
import uitc.com.plant.repository.FlowerDAO;

@Slf4j
@Service
public class FlowerService {

	@Autowired
	private FlowerDAO flowerDAO;


	/**;

	/**
	 * 
	 * 檢查植物的名字是不是存在，存在的話在丟例外
	 * 
	 * @param flowerName
	 */
	public boolean checkFlowerName(
			String flowerName
	) {

		Flower result = flowerDAO.getFlowerName(flowerName);

		// trace
		log.trace("hi trace");
		// debug
		log.debug("hi debug");
		// info
		log.info("hi info");
		// warn
		log.warn("hi warn");
		// error
		log.error("hi error");

		if (result != null) {
			throw new BasdRequestException("flower name exist");
		}
		
		return true;
	}


	public Flower saveFlower(
			Flower flower
	) {
		return flowerDAO.save(flower);
	}

}
