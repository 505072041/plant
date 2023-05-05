package uitc.com.plant.dto.common;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class PageInfo {

	private Long totalElements;
	private Integer pageElements;
	private Integer currentPage;
	private Boolean hasNext;
	private Boolean hasPrevious;
	private Integer totalPages;

	public static PageInfo parse(
			Page<?> page
	) {

		PageInfo result = new PageInfo();
		result.setTotalElements(page.getTotalElements());
		result.setPageElements(page.getNumberOfElements());
		result.setCurrentPage(page.getPageable().getPageNumber() + 1);
		result.setHasNext(page.hasNext());
		result.setHasPrevious(page.hasPrevious());
		result.setTotalPages(page.getTotalPages());
		return result;
	}
}
