/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.service.IAuthenticationService;
import jabara.jpa.JpaDaoBase;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * @author jabaraster
 */
public class AuthenticationServiceImpl extends JpaDaoBase implements IAuthenticationService {
    private static final long serialVersionUID = 2120845289949791682L;

    /**
     * @param pEntityManagerFactory
     */
    @Inject
    public AuthenticationServiceImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }

}
