package uitc.com.plant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uitc.com.plant.exception.BasdRequestException;
import uitc.com.plant.model.Store;
import uitc.com.plant.repository.StoreDAO;

@Slf4j
@Service
public class StoreService {

	@Autowired
	private StoreDAO storeDAO;

	/**
	 * 如果有此店，丟出例外
	 * 
	 * @param storeS
	 */
	public void checkStoreName(
			String storeS
	) {
		log.debug("step 1");
		Store store = storeDAO.findByStoreName(storeS);
		log.debug("step 2");

		if (store != null) {
			log.debug("step 3 - insiede if statement");
			throw new BasdRequestException("Store name exist");
		}

		log.debug("This Store is  NULL   !!!!!");

	}

	public Store saveStore(
			Store store
	) {
		return storeDAO.save(store);
	}

	/**
	 * 
	 * 用店名去找電話，無此店名會丟例外
	 * 
	 * @param storeS
	 * @return
	 */
	public Store findByStoreName(
			String storeS
	) {

		Store store = storeDAO.getStoreName(storeS); // 抓不到

		log.debug("OBJECT:{}, {}", store, "hello");

		if (store == null) {
			throw new BasdRequestException("店名:" + storeS + " 不存在");
		}
		return store;
	}

}
