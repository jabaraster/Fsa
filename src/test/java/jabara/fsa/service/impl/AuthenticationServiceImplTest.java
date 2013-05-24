/**
 * 
 */
package jabara.fsa.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jabara.fsa.WebStarter;
import jabara.fsa.entity.ELoginPassword;
import jabara.fsa.entity.EMember;
import jabara.fsa.model.FailAuthentication;
import jabara.fsa.service.IAuthenticationService.AuthenticatedAs;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author jabaraster
 */
@RunWith(Enclosed.class)
public class AuthenticationServiceImplTest {

    /**
     * @throws NamingException
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }

    /**
     * @author jabaraster
     */
    public static class UserCountIsOne {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<AuthenticationServiceImpl> tool = new JpaDaoRule<AuthenticationServiceImpl>() {
                                                                    @Override
                                                                    protected AuthenticationServiceImpl createService(
                                                                            final EntityManagerFactory pEntityManagerFactory) {
                                                                        return new AuthenticationServiceImpl(pEntityManagerFactory);
                                                                    }
                                                                };

        /**
         * @throws FailAuthentication
         */
        @SuppressWarnings("nls")
        @Test
        public void _test() throws FailAuthentication {
            insertAdministrator();

            final AuthenticatedAs actual = this.tool.getSut().login("admin", "password");
            assertThat(actual, is(AuthenticatedAs.ADMINISTRATOR));
        }

        @SuppressWarnings("nls")
        private void insertAdministrator() {
            final EntityManager em = this.tool.getEntityManager();

            final EMember member = new EMember();
            member.setAdministrator(true);
            member.setUserId("admin");
            em.persist(member);

            final ELoginPassword password = new ELoginPassword();
            password.setPassword("password");
            password.setUser(member);
            em.persist(password);
        }
    }

    /**
     * @author jabaraster
     */
    public static class UserIsEmpty {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<AuthenticationServiceImpl> tool = new JpaDaoRule<AuthenticationServiceImpl>() {
                                                                    @Override
                                                                    protected AuthenticationServiceImpl createService(
                                                                            final EntityManagerFactory pEntityManagerFactory) {
                                                                        return new AuthenticationServiceImpl(pEntityManagerFactory);
                                                                    }
                                                                };

        /**
         * @throws FailAuthentication
         */
        @SuppressWarnings("nls")
        @Test(expected = FailAuthentication.class)
        public void _login() throws FailAuthentication {
            this.tool.getSut().login("jabara", "password");
        }
    }
}
