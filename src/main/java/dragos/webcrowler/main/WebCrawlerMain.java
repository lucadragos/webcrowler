package dragos.webcrowler.main;

import java.io.IOException;

import dragos.webcrowler.operations.RenderSiteMap;
import dragos.webcrowler.operations.DiscoverMapOperation;
import dragos.webcrowler.sitemap.SiteMap;
import dragos.webcrowler.sitemap.TNode;

public class WebCrawlerMain {

    private static final String DEFAULT_ROOT_LINK = "http://wiprodigital.com";
    private static final String UNEXPECTED_SITUATION_MSG = "An unexpected situation happen and the application can not continue!";
    public static final String SITEMAP_FILE = "Sitemap.txt";

    public static void main(String[] args) {
        TNode root = new TNode(DEFAULT_ROOT_LINK, true);
        SiteMap siteMap = new SiteMap(root);
        try {
            siteMap.exploreMap(new DiscoverMapOperation());
        } catch (Exception e) {
            System.out.println(UNEXPECTED_SITUATION_MSG);
           System.out.println(e.getMessage());
        }

        //show the map with render operation
        siteMap.exploreMap(new RenderSiteMap());

        try {
            siteMap.exploreMap(new RenderSiteMap(SITEMAP_FILE));
        } catch (IOException e) {
            System.out.println("Unable to write sitemap to file "+SITEMAP_FILE);
        }
    }
}
