package hudson.plugins.page_markup;

import hudson.Plugin;
import hudson.model.PageDecorator;

public class PluginImpl extends Plugin {

    private PageMarkupPageDecorator pageDecorator;

    @Override
    public void start() throws Exception {
        pageDecorator = new PageMarkupPageDecorator();
        PageDecorator.ALL.add(pageDecorator);
        super.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        PageDecorator.ALL.remove(pageDecorator);
    }
}
