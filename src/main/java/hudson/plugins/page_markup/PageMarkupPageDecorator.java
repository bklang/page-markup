/** PageMarkup: An HTML page markup plugin for Hudson
 *  Copyright (c) 2008 Sandia Corporation.
 *  All rights resserved.
 *
 * PageMarkup is part of the FAST software project, see
 *   https://software.sandia.gov/trac/fast
 *
 * This software is distributed under the BSD License; for the full text
 * of the license, see the LICENSE.txt file included with this
 * distribution.  Under the terms of Contract DE-AC04-94AL85000, there is
 * a non-exclusive license for use of this work by or on behalf of the
 * U.S. Government. 
 *
 * Created by John D. Siirola <jdsiiro@sandia.gov>
 * Sandia National Laboratories
 * 4 June 2009
 */
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
