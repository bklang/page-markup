package hudson.plugins.page_markup;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Util;
import hudson.model.PageDecorator;

public class PageMarkupPageDecorator extends PageDecorator {

    private String headerHtmlFragment;
    private String footerHtmlFragment;

    public PageMarkupPageDecorator(String headerHtmlFragment, String footerHtmlFragment) {
        this();
        this.headerHtmlFragment = headerHtmlFragment;
        this.footerHtmlFragment = footerHtmlFragment;
    }
    
    protected PageMarkupPageDecorator() {
        super(PageMarkupPageDecorator.class);
        load();
    }

    @Override
    public String getDisplayName() {
        return "Additional Page HTML Markup";
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        save();
        return true;
    }

    public String getheaderHtmlFragment() {
        return Util.fixEmpty(headerHtmlFragment);
    }

    public void setheaderHtmlFragment(String htmlFragment) {
        this.headerHtmlFragment = htmlFragment;
    }

    public String getfooterHtmlFragment() {
        return Util.fixEmpty(footerHtmlFragment);
    }

    public void setfooterHtmlFragment(String htmlFragment) {
        this.footerHtmlFragment = htmlFragment;
    }
}
