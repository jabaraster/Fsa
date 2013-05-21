/**
 * 
 */
package jabara.fsa.entity;

import jabara.fsa.beanvalidation.RequireMaxCharCount;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author jabaraster
 */
@Entity
public class EReport extends EReadable<EReport> {
    private static final long serialVersionUID    = -8857524597494554779L;

    private static final int  MAX_CHAR_COUNT_TEXT = 500000;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_TEXT * 3)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_TEXT)
    protected String          text;

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    protected EBusinessItem   businessItem;

    /**
     * 
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date            reportDate;

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    protected EMember         writer;

    /**
     * @return businessItemを返す.
     */
    public EBusinessItem getBusinessItem() {
        return this.businessItem;
    }

    /**
     * @return reportDateを返す.
     */
    public Date getReportDate() {
        return this.reportDate == null ? null : new Date(this.reportDate.getTime());
    }

    /**
     * @return textを返す.
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return writerを返す.
     */
    public EMember getWriter() {
        return this.writer;
    }

    /**
     * @param pBusinessItem businessItemを設定.
     */
    public void setBusinessItem(final EBusinessItem pBusinessItem) {
        this.businessItem = pBusinessItem;
    }

    /**
     * @param pReportDate reportDateを設定.
     */
    public void setReportDate(final Date pReportDate) {
        this.reportDate = pReportDate == null ? null : new Date(pReportDate.getTime());
    }

    /**
     * @param pText textを設定.
     */
    public void setText(final String pText) {
        this.text = pText;
    }

    /**
     * @param pWriter writerを設定.
     */
    public void setWriter(final EMember pWriter) {
        this.writer = pWriter;
    }
}
