/**
 * 
 */
package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jabaraster
 */
@Entity
public class ERead extends EntityBase<ERead> {
    private static final long serialVersionUID = 8239443761070021724L;

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    protected EReadable<?>    read;

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
     * @return readを返す.
     */
    public EReadable<?> getRead() {
        return this.read;
    }

    /**
     * @param pMember memberを設定.
     */
    public void setMember(final EMember pMember) {
        this.member = pMember;
    }

    /**
     * @param pRead readを設定.
     */
    public void setRead(final EReadable<?> pRead) {
        this.read = pRead;
    }
}
