package pe.com.tss.runakuna.support;

import pe.com.tss.runakuna.dto.PageableFilter;

/**
 * Created by josediaz on 28/10/2016.
 */
public class PageableQuery {

    private String mainSection;

    private PageableFilter pageableFilter;

    public PageableQuery(PageableFilter pageableFilter){
        this.pageableFilter=pageableFilter;
    }

    public void addMainSection(String mainSection){
        this.mainSection=mainSection;
    }


    public String buildQuery(){

        StringBuilder query= new StringBuilder();
        String sortQuery=buildSortSecction();

        query.append("select * ");
        query.append(" from (");
        query.append(" select temp.*,");

        //sort
        if(sortQuery !=null && !sortQuery.isEmpty()){
            query.append(" row_number() over ( ").append(sortQuery).append(" ) ");
        }else{
            query.append(" ROWNUM ");
        }

        query.append(" rn");
        query.append(" from ( ") ;

        query.append(mainSection);

        query.append(" )temp");

        query.append(" )");
        query.append(" where ");
        int fromRow=pageableFilter.getSkip().intValue()+1;
        int toRow=pageableFilter.getSkip().intValue()+pageableFilter.getTake().intValue();
        query.append(" rn>= ").append(fromRow);
        query.append(" and rn<= ").append(toRow);

        query.append(sortQuery);

        return query.toString();
    }

    private String buildSortSecction(){
        StringBuilder query= new StringBuilder();
        if(pageableFilter.getSortColumn() !=null && !pageableFilter.getSortColumn().isEmpty()){

            query.append(" order by  ").append(pageableFilter.getSortColumn());

            if(pageableFilter.getSortDir() !=null && !pageableFilter.getSortDir().isEmpty()){
                query.append(" ").append(pageableFilter.getSortDir());
            }else{
                query.append(" asc ");
            }
        }

        return query.toString();

    }
}