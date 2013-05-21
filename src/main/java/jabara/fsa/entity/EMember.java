/**
 * 
 */
package jabara.fsa.entity;

import jabara.fsa.beanvalidation.RequireMaxCharCount;
import jabara.jpa.entity.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author jabaraster
 */
@Entity
public class EMember extends EntityBase<EMember> {
    private static final long serialVersionUID    = -6160695120326005348L;

    private static final int  MAX_CHAR_COUNT_NAME = 100;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_NAME * 3)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_NAME)
    protected String          name;

    /**
     * @return nameを返す.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pName nameを設定.
     */
    public void setName(final String pName) {
        this.name = pName;
    }

}
