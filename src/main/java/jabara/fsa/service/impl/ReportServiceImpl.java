/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.entity.EReport;
import jabara.fsa.entity.EReport_;
import jabara.fsa.service.IReportService;
import jabara.general.ArgUtil;
import jabara.jpa.JpaDaoBase;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author jabaraster
 */
public class ReportServiceImpl extends JpaDaoBase implements IReportService {
    private static final long serialVersionUID = 7273269255415613629L;

    /**
     * @param pEntityManagerFactory
     */
    @Inject
    public ReportServiceImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }

    /**
     * @see jabara.fsa.service.IReportService#countAll()
     */
    @Override
    public long countAll() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> query = builder.createQuery(Long.class);

        query.select(builder.count(query.from(EReport.class)));

        return em.createQuery(query).getSingleResult().longValue();
    }

    /**
     * @see jabara.fsa.service.IReportService#get(long, long)
     */
    @SuppressWarnings("nls")
    @Override
    public List<EReport> get(final long pFirst, final long pCount) {
        if (pFirst > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("sorry. 'pFirst' value(" + pFirst + ") is greater than Integer.MaX_VALUE.");
        }
        if (pCount > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("sorry. 'pCount' value(" + pCount + ") is greater than Integer.MaX_VALUE.");
        }
        return getAll((int) pFirst, (int) pCount);
    }

    /**
     * @see jabara.fsa.service.IReportService#getAll(int, int)
     */
    @Override
    public List<EReport> getAll(final int pOffset, final int pCount) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<EReport> query = builder.createQuery(EReport.class);
        final Root<EReport> root = query.from(EReport.class);

        root.fetch(EReport_.businessItem);
        root.fetch(EReport_.writer);

        return em.createQuery(query).setFirstResult(pOffset).setMaxResults(pCount).getResultList();
    }

    /**
     * @see jabara.fsa.service.IReportService#insert(jabara.fsa.entity.EReport)
     */
    @Override
    public void insert(final EReport pReport) {
        ArgUtil.checkNull(pReport, "pReport"); //$NON-NLS-1$
        getEntityManager().persist(pReport);
    }

}
