/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.WebStarter;

import javax.naming.NamingException;
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
public class MemberServiceImplTest {

    /**
     * @throws NamingException -
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }

    /**
     * @author jabaraster
     */
    public static class Other {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<MemberServiceImpl> tool = new JpaDaoRule<MemberServiceImpl>() {
                                                            @Override
                                                            protected MemberServiceImpl createService(final EntityManagerFactory pEntityManagerFactory) {
                                                                return new MemberServiceImpl(pEntityManagerFactory);
                                                            }
                                                        };

        /**
         * 
         */
        @Test
        public void _insertAdministratorIfNotExists() {
            this.tool.getSut().insertAdministratorIfNotExists();
        }
    }
}
