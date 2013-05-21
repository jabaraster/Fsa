package jabara.fsa.web.ui.page;

import jabara.fsa.Environment;
import jabara.fsa.entity.EReport;
import jabara.fsa.service.IReportService;
import jabara.fsa.web.ui.component.ReportPanel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 */
@SuppressWarnings("synthetic-access")
public class TopPage extends RestrictedPageBase {
    private static final long   serialVersionUID    = -4965903336608758671L;

    private static final int    ITEM_COUNT_PER_PAGE = 20;

    @Inject
    IReportService              reportService;

    private final Handler       handler             = new Handler();

    private Label               applicationName;
    private Label               now;
    private AjaxLink<?>         reloader;
    private Link<?>             goLogout;

    private AjaxPagingNavigator reportsPagingNavigator;
    private DataView<EReport>   reports;

    /**
     * 
     */
    public TopPage() {
        this.add(getApplicationName());
        this.add(getNow());
        this.add(getReloader());
        this.add(getGoLogout());

        this.add(getReportsPagingNavigator());
        this.add(getReports());
    }

    /**
     * @see jabara.fsa.web.ui.page.WebPageBase#getTitleLabelModel()
     */
    @Override
    protected IModel<String> getTitleLabelModel() {
        return Model.of("Top"); //$NON-NLS-1$
    }

    private Label getApplicationName() {
        if (this.applicationName == null) {
            this.applicationName = new Label("applicationName", Model.of(Environment.getApplicationName())); //$NON-NLS-1$
        }
        return this.applicationName;
    }

    private Link<?> getGoLogout() {
        if (this.goLogout == null) {
            this.goLogout = new BookmarkablePageLink<>("goLogout", LogoutPage.class); //$NON-NLS-1$
        }
        return this.goLogout;
    }

    @SuppressWarnings({ "serial", "nls" })
    private Label getNow() {
        if (this.now == null) {
            this.now = new Label("now", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()); //$NON-NLS-1$
                }
            });
        }
        return this.now;
    }

    @SuppressWarnings("serial")
    private AjaxLink<?> getReloader() {
        if (this.reloader == null) {
            this.reloader = new IndicatingAjaxLink<Object>("reloader") { //$NON-NLS-1$
                @Override
                public void onClick(final AjaxRequestTarget pTarget) {
                    TopPage.this.handler.onReloaderClick(pTarget);
                }
            };
        }
        return this.reloader;
    }

    @SuppressWarnings("serial")
    private DataView<EReport> getReports() {
        if (this.reports == null) {
            this.reports = new DataView<EReport>("reports", new ReportsProvider(), ITEM_COUNT_PER_PAGE) { //$NON-NLS-1$
                @Override
                protected void populateItem(final Item<EReport> pItem) {
                    pItem.add(new ReportPanel("report", Model.of(pItem.getModelObject()))); //$NON-NLS-1$
                }
            };
        }
        return this.reports;
    }

    private AjaxPagingNavigator getReportsPagingNavigator() {
        if (this.reportsPagingNavigator == null) {
            this.reportsPagingNavigator = new AjaxPagingNavigator("reportsPagingNavigator", getReports()); //$NON-NLS-1$
        }
        return this.reportsPagingNavigator;
    }

    private class Handler implements Serializable {
        private static final long serialVersionUID = 8826180320287426527L;

        private void onReloaderClick(final AjaxRequestTarget pTarget) {
            pTarget.add(getNow());
        }

    }

    private class ReportsProvider implements IDataProvider<EReport> {
        private static final long serialVersionUID = 8519006705820549202L;

        @Override
        public void detach() {
            // 処理なし
        }

        @Override
        public Iterator<? extends EReport> iterator(final long pFirst, final long pCount) {
            return TopPage.this.reportService.get(pFirst, pCount).iterator();
        }

        @Override
        public IModel<EReport> model(final EReport pObject) {
            return Model.of(pObject);
        }

        @Override
        public long size() {
            return TopPage.this.reportService.countAll();
        }

    }
}
