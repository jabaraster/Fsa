/**
 * 
 */
package jabara.fsa.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jabara.fsa.Environment;
import jabara.fsa.WebStarter;
import jabara.fsa.entity.EBusinessItem;
import jabara.fsa.entity.EComment;
import jabara.fsa.entity.ECustomer;
import jabara.fsa.entity.EMember;
import jabara.fsa.entity.EOrganization;
import jabara.fsa.entity.ERead;
import jabara.fsa.entity.EReadable;
import jabara.fsa.entity.EReport;
import jabara.jpa_guice.ThreadLocalEntityManagerFactoryHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author jabaraster
 */
@RunWith(Enclosed.class)
public class ReportServiceImplTest {

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
    @Ignore
    public static abstract class Base {
        EntityManagerFactory emf;
        ReportServiceImpl    sut;

        /**
         * @return -
         */
        @SuppressWarnings({ "nls" })
        public EReport insert() {
            final EntityManager em = this.emf.createEntityManager();

            final EOrganization organization = new EOrganization();
            organization.setAddress("熊本市西区");
            organization.setName("有限会社じゃばら");
            organization.setPhoneNumber("000-0001-0000");
            em.persist(organization);

            final ECustomer customer = new ECustomer();
            customer.setName("河野　智遵");
            customer.setBelongOrganization(organization);
            em.persist(customer);

            final EBusinessItem item = new EBusinessItem();
            item.setCustomer(customer);
            item.setName("Web予約システム構築");
            em.persist(item);

            final EMember member = new EMember();
            member.setName("河野");
            em.persist(member);

            em.flush();
            em.clear();

            final EReport report = new EReport();
            report.setBusinessItem(item);
            report.setReportDate(Calendar.getInstance().getTime());
            report.setText("報告内容です。");
            report.setWriter(member);

            this.sut.insert(report);

            em.flush();
            em.clear();

            return report;
        }

        /**
         * 
         */
        @Before
        public void setUp() {
            this.emf = ThreadLocalEntityManagerFactoryHandler.wrap(Persistence.createEntityManagerFactory(Environment.getApplicationName()));
            this.sut = new ReportServiceImpl(this.emf);
            this.emf.createEntityManager().getTransaction().begin();
        }

        /**
     * 
     */
        @After
        public void tearDown() {
            if (this.emf != null) {
                this.emf.createEntityManager().getTransaction().rollback();
                this.emf.close();
            }
        }
    }

    /**
     * @author jabaraster
     */
    public static class Other extends Base {

        /**
         * 
         */
        @Test
        public void _test() {
            final EntityManager em = this.emf.createEntityManager();

            final EReport report0 = insert();
            final EReport report1 = insert();

            final EComment comment = new EComment();
            comment.setBusinessItem(report0.getBusinessItem());
            comment.setText("aaa"); //$NON-NLS-1$
            comment.setWriter(report0.getWriter());
            em.persist(comment);

            final ERead read0 = new ERead();
            read0.setRead(report0);
            read0.setMember(report1.getWriter());

            final ERead read1 = new ERead();
            read1.setRead(comment);
            read1.setMember(report0.getWriter());

            em.persist(read0);
            em.persist(read1);

            em.flush();
            em.clear();

            for (final EReadable<?> read : selectReadable()) {
                System.out.println(read);
            }
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        private List<EReadable<?>> selectReadable() {
            final EntityManager em = this.emf.createEntityManager();
            final CriteriaBuilder builder = em.getCriteriaBuilder();
            final CriteriaQuery<EReadable> query = builder.createQuery(EReadable.class);
            query.from(EReadable.class);
            final List<EReadable> list = em.createQuery(query).getResultList();
            final List<EReadable<?>> ret = new ArrayList<>();
            ret.addAll((Collection<? extends EReadable<?>>) list);
            return ret;
        }
    }

    /**
     * @author jabaraster
     */
    @Ignore
    public static class RecordCountIsOne extends Base {

        /**
         * 
         */
        @SuppressWarnings("boxing")
        @Test
        public void _countAll() {
            assertThat(this.sut.countAll(), is(1L));
        }

        /**
         * 
         */
        @Test
        public void _getAll() {
            for (final EReport report : this.sut.getAll(0, 10)) {
                System.out.println(report.getBusinessItem());
            }
        }

        /**
         * @see jabara.fsa.service.impl.ReportServiceImplTest.Base#setUp()
         */
        @Before
        @Override
        public void setUp() {
            super.setUp();
            insert();
        }
    }
}
