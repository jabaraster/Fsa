package jabara.fsa.web.ui.page;

import jabara.fsa.model.FailAuthentication;
import jabara.fsa.web.ui.AppSession;
import jabara.general.Empty;
import jabara.wicket.ErrorClassAppender;

import java.io.Serializable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 
 */
@SuppressWarnings("synthetic-access")
public class LoginPage extends WebPageBase {
    private static final long serialVersionUID = 1925170327965147328L;

    private final Handler     handler          = new Handler();

    private FeedbackPanel     feedback;
    private StatelessForm<?>  form;
    private TextField<String> user;
    private PasswordTextField password;
    private Button            submitter;

    /**
     * 
     */
    public LoginPage() {
        this.add(getFeedback());
        this.add(getForm());
    }

    /**
     * @see jabara.fsa.web.ui.page.WebPageBase#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
     */
    @Override
    public void renderHead(final IHeaderResponse pResponse) {
        super.renderHead(pResponse);
        addPageCssReference(pResponse, getPageClass());
        pResponse.render(OnDomReadyHeaderItem.forScript(JavaScriptUtil.getFocusScript(getUser())));
    }

    /**
     * @see jabara.fsa.web.ui.page.WebPageBase#getTitleLabelModel()
     */
    @Override
    protected IModel<String> getTitleLabelModel() {
        return Model.of(getString("pageTitle")); //$NON-NLS-1$
    }

    private FeedbackPanel getFeedback() {
        if (this.feedback == null) {
            this.feedback = new FeedbackPanel("feedback"); //$NON-NLS-1$
        }
        return this.feedback;
    }

    private StatelessForm<?> getForm() {
        if (this.form == null) {
            this.form = new StatelessForm<>("form"); //$NON-NLS-1$
            this.form.add(getUser());
            this.form.add(getPassword());
            this.form.add(getSubmitter());
        }
        return this.form;
    }

    private PasswordTextField getPassword() {
        if (this.password == null) {
            this.password = new PasswordTextField("password", Model.of(Empty.STRING)); //$NON-NLS-1$
        }
        return this.password;
    }

    @SuppressWarnings("serial")
    private Button getSubmitter() {
        if (this.submitter == null) {
            this.submitter = new Button("submitter") { //$NON-NLS-1$
                @Override
                public void onError() {
                    LoginPage.this.handler.onSubmitterError();
                }

                @Override
                public void onSubmit() {
                    LoginPage.this.handler.tryLogin();
                }
            };
        }
        return this.submitter;
    }

    private TextField<String> getUser() {
        if (this.user == null) {
            this.user = new TextField<>("user", Model.of(Empty.STRING)); //$NON-NLS-1$
            this.user.setRequired(true);
        }
        return this.user;
    }

    private class Handler implements Serializable {
        private static final long        serialVersionUID   = 6317461189636878176L;

        private final ErrorClassAppender errorClassAppender = new ErrorClassAppender(Model.of("error")); //$NON-NLS-1$

        private void onSubmitterError() {
            this.errorClassAppender.addErrorClass(getForm());
        }

        private void tryLogin() {
            try {
                AppSession.get().login(getUser().getModelObject(), getPassword().getModelObject());
                setResponsePage(getApplication().getHomePage());
            } catch (final FailAuthentication e) {
                error(getString("message.failLogin")); //$NON-NLS-1$
                this.errorClassAppender.addErrorClass(getForm());
            }
        }
    }

}
