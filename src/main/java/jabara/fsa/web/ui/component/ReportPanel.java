/**
 * 
 */
package jabara.fsa.web.ui.component;

import jabara.fsa.entity.EReport;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * @author jabaraster
 */
public class ReportPanel extends Panel {
    private static final long serialVersionUID = -4895912724516061075L;

    private MultiLineLabel    text;

    /**
     * @param pId -
     * @param pModel -
     */
    public ReportPanel(final String pId, final Model<EReport> pModel) {
        super(pId, pModel);
        this.add(getText());
    }

    /**
     * @return -
     */
    private EReport getModelObject() {
        return (EReport) super.getDefaultModel().getObject();
    }

    @SuppressWarnings({ "nls", "serial" })
    private MultiLineLabel getText() {
        if (this.text == null) {
            this.text = new MultiLineLabel("text", new AbstractReadOnlyModel<String>() {
                @SuppressWarnings("synthetic-access")
                @Override
                public String getObject() {
                    return getModelObject().getText();
                }
            });
        }
        return this.text;
    }
}
