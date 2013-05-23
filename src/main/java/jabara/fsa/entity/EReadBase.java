/**
 * 
 */
package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @param <E>
 * @author jabaraster
 */
@MappedSuperclass
public abstract class EReadBase<E extends EReadBase<E>> extends EntityBase<E> {
    private static final long serialVersionUID = 7767453914938106735L;

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    protected EMember         member;

    /**
     * @return memberを返す.
     */
    public EMember getMember() {
        return this.member;
    }

    /**
     * @param pMember memberを設定.
     */
    public void setMember(final EMember pMember) {
        this.member = pMember;
    }
}
