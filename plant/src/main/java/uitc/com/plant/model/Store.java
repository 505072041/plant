package uitc.com.plant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "phone")
	private String ph;

	@Column(name = "address")
	private String ad;

	@Temporal(TemporalType.DATE)
	@Column(name = "open_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date openTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "close_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date closeTime;

	
}
