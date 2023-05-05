package uitc.com.plant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uitc.com.plant.model.Flower;

public interface FlowerDAO extends JpaRepository<Flower, Integer> {

	@Query(value = " select * from flower where flower_name = :name ", nativeQuery = true)
	public Flower getFlowerName(
			@Param("name") String name
	);
}
