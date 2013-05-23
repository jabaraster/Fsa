/**
 * 
 */
package jabara.fsa.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jabaraster
 */
@Entity
public class EReportRead extends EReadBase<EReportRead> {
    private static final long serialVersionUID = 8192432768181963015L;

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    protected EReport         report;

    /**
     * @return reportを返す.
     */
    public EReport getReport() {
        return this.report;
    }

    /**
     * @param pReport reportを設定.
     */
    public void setReport(final EReport pReport) {
        this.report = pReport;
    }
}
