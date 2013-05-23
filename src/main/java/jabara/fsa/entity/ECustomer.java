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
public class ECustomer extends EntityBase<ECustomer> {
    private static final long serialVersionUID    = 2216644330281850063L;

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
    @JoinColumn(nullable = false)
    @NotNull
    protected EOrganization   belong  = new EOrganization();

    /**
     * @return belongOrganizationを返す.
     */
    public EOrganization getBelong() {
        return this.belong;
    }

    /**
     * @return nameを返す.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pBelongOrganization belongOrganizationを設定.
     */
    public void setBelong(final EOrganization pBelongOrganization) {
        this.belong = pBelongOrganization;
    }

    /**
     * @param pName nameを設定.
     */
    public void setName(final String pName) {
        this.name = pName;
    }
}
