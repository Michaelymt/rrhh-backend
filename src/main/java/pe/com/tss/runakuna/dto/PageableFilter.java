package pe.com.tss.runakuna.dto;

/**
 * Created by josediaz on 28/10/2016.
 */
public class PageableFilter {

    private Long page= 1l;
    private Long pageSize = 10l;


    private String sortColumn;
    private String sortDir;


    public Long getSkip() {

        if(page == null){
            page = 1l;
            pageSize=10l;
        }

        return (page-1)* pageSize;
    }

    public Long getTake() {
        return pageSize;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
