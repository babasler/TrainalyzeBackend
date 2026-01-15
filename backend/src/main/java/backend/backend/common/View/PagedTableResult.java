package backend.backend.common.View;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedTableResult<T> {

    private List<T> rows;

    private int pageSize;
    private int totalPages;

    public PagedTableResult(
            List<T> rows,
            int totalPages,
            int pageSize,
            long totalElements
    ) {
        this.rows = rows;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }
}

