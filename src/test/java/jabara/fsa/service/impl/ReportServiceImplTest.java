/**
 * 
 */
package jabara.fsa.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jabara.fsa.WebStarter;
import jabara.fsa.entity.EBusinessItem;
import jabara.fsa.entity.ECustomer;
import jabara.fsa.entity.EMember;
import jabara.fsa.entity.EOrganization;
import jabara.fsa.entity.EReadBase_;
import jabara.fsa.entity.EReport;
import jabara.fsa.entity.EReportRead;
import jabara.fsa.entity.EReportRead_;

import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.BeforeClass;
import org.junit.Rule;
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
    public static class Other {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<ReportServiceImpl> tool = new JpaDaoRule<ReportServiceImpl>() {
                                                            @Override
                                                            protected ReportServiceImpl createService(final EntityManagerFactory pEntityManagerFactory) {
                                                                return new ReportServiceImpl(pEntityManagerFactory);
                                                            }
                                                        };

        /**
         * 
         */
        @SuppressWarnings("boxing")
        @Test
        public void test() {
            insertReport();
            insertReport();
            final EReport report = insertReport();
            final EMember reportReader = this.tool.insertMember("じゃばら"); //$NON-NLS-1$
            insertReportRead(report, reportReader);

            assertThat(getReports().size(), is(3));
            assertThat(getReadReports(reportReader).size(), is(1));
            assertThat(getUnreadReports(report.getWriter()).size(), is(2));
        }

        private List<EReport> getReadReports(final EMember pReportReader) {
            return getReportsCore(pReportReader, Readable.READ);
        }

        private List<EReport> getReports() {
            return getReportsCore(null, Readable.OUT_OF);
        }

        private List<EReport> getReportsCore(final EMember pReportReader, final Readable pReadable) {
            final EntityManager em = this.tool.getEntityManager();
            final CriteriaBuilder builder = em.getCriteriaBuilder();
            final CriteriaQuery<EReport> query = builder.createQuery(EReport.class);
            final Root<EReport> root = query.from(EReport.class);

            if (pReadable != Readable.OUT_OF) {
                final Subquery<String> subquery = query.subquery(String.class);
                final Root<EReportRead> subqueryRoot = subquery.from(EReportRead.class);
                subquery.correlate(root);
                subquery.select(builder.literal("X")); //$NON-NLS-1$
                subquery.where( //
                        builder.equal(subqueryRoot.get(EReportRead_.report), root) //
                        , builder.equal(subqueryRoot.get(EReadBase_.member), pReportReader) //
                );

                if (pReadable == Readable.READ) {
                    query.where(builder.exists(subquery));
                } else {
                    query.where(builder.not(builder.exists(subquery)));
                }
            }

            return em.createQuery(query).getResultList();
        }

        private List<EReport> getUnreadReports(final EMember pReportReader) {
            return getReportsCore(pReportReader, Readable.UNREAD);
        }

        @SuppressWarnings("nls")
        private EReport insertReport() {
            final EMember member = this.tool.insertMember("河野");
            final EOrganization organization = this.tool.insertOrganization("企業", "住所", "092-123-4567");
            final ECustomer customer = this.tool.insertCustomer("顧客", organization);
            final EBusinessItem businessItem = this.tool.insertBusinessItem("案件X", customer);
            return this.tool.insertReport( //
                    member //
                    , businessItem //
                    , Calendar.getInstance() //
                    , "報告です。");
        }

        private void insertReportRead(final EReport pReport, final EMember pReportReader) {
            final EReportRead rr = new EReportRead();
            rr.setMember(pReportReader);
            rr.setReport(pReport);
            this.tool.getEntityManager().persist(rr);
        }

        enum Readable {
            READ, UNREAD, OUT_OF, ;
        }
    }
}