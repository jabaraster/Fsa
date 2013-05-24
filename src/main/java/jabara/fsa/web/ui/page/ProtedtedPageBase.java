/**
 * 
 */
package jabara.fsa.web.ui.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author jabaraster
 */
public abstract class ProtedtedPageBase extends RestrictedPageBase {
    private static final long serialVersionUID = 4974019219251588135L;

    /**
     * 
     */
    protected ProtedtedPageBase() {
        super();
    }

    /**
     * @param pParameters
     */
    protected ProtedtedPageBase(final PageParameters pParameters) {
        super(pParameters);
    }

}
