/**
 * 
 */
package jabara.fsa.entity;

import jabara.fsa.beanvalidation.RequireMaxCharCount;
import jabara.jpa.entity.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author jabaraster
 */
@Entity
public class EBusinessItem extends EntityBase<EBusinessItem> {
    private static final long serialVersionUID    = -5120215878074568939L;

    private static final int  MAX_CHAR_COUNT_NAME = 100;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_NAME * 3)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_NAME)
    protected String          name;
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = true)
    protected ECustomer       customer;

    /**
     * @return customerを返す.
     */
    public ECustomer getCustomer() {
        return this.customer;
    }

    /**
     * @return nameを返す.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pCustomer customerを設定.
     */
    public void setCustomer(final ECustomer pCustomer) {
        this.customer = pCustomer;
    }

    /**
     * @param pName nameを設定.
     */
    public void setName(final String pName) {
        this.name = pName;
    }
}
