/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.entity.ELoginPassword;
import jabara.fsa.entity.ELoginPassword_;
import jabara.fsa.entity.EMember_;
import jabara.fsa.model.FailAuthentication;
import jabara.fsa.service.IAuthenticationService;
import jabara.general.NotFound;
import jabara.jpa.JpaDaoBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author jabaraster
 */
public class AuthenticationServiceImpl extends JpaDaoBase implements IAuthenticationService {
    private static final long serialVersionUID = 6002856896981032655L;

    /**
     * @param pEntityManagerFactory
     */
    public AuthenticationServiceImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }

    /**
     * @see jabara.fsa.service.IAuthenticationService#login(String, String)
     */
    @Override
    public AuthenticatedAs login(final String pUserId, final String pPassword) throws FailAuthentication {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<ELoginPassword> query = builder.createQuery(ELoginPassword.class);
        final Root<ELoginPassword> root = query.from(ELoginPassword.class);

        query.distinct(true);
        root.fetch(ELoginPassword_.user);

        query.where( //
        builder.equal(root.get(ELoginPassword_.user).get(EMember_.userId), pUserId) //
        );

        try {
            final ELoginPassword member = getSingleResult(em.createQuery(query));
            if (!member.equal(pPassword)) {
                throw FailAuthentication.INSTANCE;
            }

            return member.getUser().isAdministrator() ? AuthenticatedAs.ADMINISTRATOR : AuthenticatedAs.NORMAL_USER;

        } catch (final NotFound e) {
            throw FailAuthentication.INSTANCE;
        }
    }
}
