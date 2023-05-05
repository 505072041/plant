package uitc.com.plant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uitc.com.plant.model.InventoryLog;

public interface InventoryLogDao extends JpaRepository<InventoryLog, Long>{

}
