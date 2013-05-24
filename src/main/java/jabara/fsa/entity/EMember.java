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

    private static final int  MAX_CHAR_COUNT_USER_ID = 100;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_USER_ID * 3, unique = true)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_USER_ID)
    protected String          userId;

    /**
     * 
     */
    @Column(nullable = false)
    protected boolean         administrator       = false;

    /**
     * @return nameを返す.
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * @return administratorを返す.
     */
    public boolean isAdministrator() {
        return this.administrator;
    }

    /**
     * @param pAdministrator administratorを設定.
     */
    public void setAdministrator(final boolean pAdministrator) {
        this.administrator = pAdministrator;
    }

    /**
     * @param pName nameを設定.
     */
    public void setUserId(final String pName) {
        this.userId = pName;
    }

}
