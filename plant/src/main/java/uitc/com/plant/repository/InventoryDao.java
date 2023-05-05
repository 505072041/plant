package uitc.com.plant.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import uitc.com.plant.model.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, Long> {

	@Override
	@EntityGraph(attributePaths = {
			"store",
			"flower" })
	List<Inventory> findAll();

	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Inventory> findById(
			Long id
	);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Inventory> findByStoreIdAndFlowerId(
			Integer storeId,
			Integer flowerId
	);

	@EntityGraph(attributePaths = {
			"store",
			"flower" })
	Page<Inventory> findByStoreIdOrderByFlowerIdAsc(
			Pageable pageRequest,
			Integer storeId
	);

}
