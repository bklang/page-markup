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

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.Util;
import hudson.model.PageDecorator;
import hudson.model.Descriptor;

class PageMarkupHelper {
   private static int count = 0;
   private static int size = 2;
   private static PageMarkupPageDecorator[] instances = new PageMarkupPageDecorator[size];

   public static void add(PageMarkupPageDecorator pd) {
      if ( size == count ) {
	 size *= 2;
	 PageMarkupPageDecorator[] tmp = new PageMarkupPageDecorator[size];
	 for(int i = 0; i < count; i++)
	    tmp[i] = instances[i];
	 instances = tmp;
      }
      instances[count] = pd;
      count++;
   }

   public static void reload() {
      for( int i=0; i < PageMarkupHelper.count; PageMarkupHelper.instances[i++].load() );
   }

   public static int getCount() {
      return count;
   }
}

@Extension
public class PageMarkupPageDecorator extends PageDecorator {

    private String headerHtmlFragment;
    private String footerHtmlFragment;

    @DataBoundConstructor
    public PageMarkupPageDecorator(String headerHtmlFragment, String footerHtmlFragment) {
        this();
        this.headerHtmlFragment = headerHtmlFragment;
        this.footerHtmlFragment = footerHtmlFragment;
    }
    
    public PageMarkupPageDecorator() {
        super(PageMarkupPageDecorator.class);
        load();
	PageMarkupHelper.add(this);
    }

    @Override
    public String getDisplayName() {
        return "Additional Page HTML Markup";
    }
   
    @Override
    public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
	headerHtmlFragment = formData.getString("htmlHeader");
	footerHtmlFragment = formData.getString("htmlFooter");
	//  + Integer.toString(PageMarkupHelper.getCount());
        save();
	PageMarkupHelper.reload();
        return true;
    }
   
    public String getheaderHtmlFragment() {
        return Util.fixEmpty(headerHtmlFragment);
    }

    public String getfooterHtmlFragment() {
        return Util.fixEmpty(footerHtmlFragment);
    }
}
