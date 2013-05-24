/**
 * 
 */
package jabara.fsa.dao.impl;

import jabara.fsa.dao.IMemberDao;
import jabara.jpa.JpaDaoBase;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * @author jabaraster
 */
public class MemberDaoImpl extends JpaDaoBase implements IMemberDao {
    private static final long serialVersionUID = -7660437864039149337L;

    /**
     * @param pEntityManagerFactory
     */
    @Inject
    public MemberDaoImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }
}
