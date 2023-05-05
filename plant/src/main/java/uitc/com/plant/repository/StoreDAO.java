package uitc.com.plant.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uitc.com.plant.model.Inventory;
import uitc.com.plant.model.Store;

public interface StoreDAO extends JpaRepository<Store, Integer> {

	@Query(value = " select * from store where store_name = :name ", nativeQuery = true)
	public Store getStoreName(
			@Param("name") String name
	);


	public Store findByStoreName(String storeName);
	

	
	
}
