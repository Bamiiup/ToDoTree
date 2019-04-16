package aaandrey.todotree.domain.representation.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "SORT_RULE")
public class SortRule {
    @Column(name = "FIELD_NAME", nullable = false)
    private String fieldName;

    @Column(name = "_ORDER", nullable = false)
    private int order;

    @Column(name = "IS_DIRECT", nullable = false)
    private boolean isDirect;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(boolean isDirect) {
        this.isDirect = isDirect;
    }

}
