/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.entity.EMember;
import jabara.fsa.service.IMemberService;
import jabara.jpa.JpaDaoBase;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author jabaraster
 */
public class MemberServiceImpl extends JpaDaoBase implements IMemberService {
    private static final long serialVersionUID = 7579772724649072761L;

    /**
     * @param pEntityManagerFactory -
     */
    @Inject
    public MemberServiceImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }

    /**
     * @see jabara.fsa.service.IMemberService#insertAdministratorIfNotExists()
     */
    @Override
    public void insertAdministratorIfNotExists() {
        if (existsAministrator()) {
            return;
        }
        final EMember member = new EMember();
        member.setAdministrator(true);
        member.setName("管理者"); //$NON-NLS-1$
        getEntityManager().persist(member);
    }

    private boolean existsAministrator() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<String> query = builder.createQuery(String.class);
        query.from(EMember.class);

        final String DUMMY = "X"; //$NON-NLS-1$
        query.select(builder.literal(DUMMY).alias(DUMMY));

        return !em.createQuery(query).setMaxResults(1).getResultList().isEmpty();
    }
}
