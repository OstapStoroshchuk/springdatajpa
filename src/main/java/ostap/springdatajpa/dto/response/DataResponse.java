package ostap.springdatajpa.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DataResponse<T> {

    private List<T> data = new ArrayList<>();

    private Integer totalPages;

    private Long totalElements;

    public DataResponse() {
    }

    public DataResponse(List<T> data, Page page) {
        this.data = data;
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
