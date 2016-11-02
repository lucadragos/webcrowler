package dragos.webcrowler.main;

import dragos.webcrowler.operations.ConsoleRenderMapOperation;
import dragos.webcrowler.operations.DiscoverMapOperation;
import dragos.webcrowler.sitemap.SiteMap;
import dragos.webcrowler.sitemap.TNode;

public class WebCrowlerMain {

    private static final String DEFAULT_ROOT_LINK = "http://wiprodigital.com";
    private static final String UNEXPECTED_SITUATION_MSG = "An unexpected situation happen and the application can not continue!";

    public static void main(String[] args) {
        //create the map with discovery operation
        TNode root = new TNode(DEFAULT_ROOT_LINK, true);
        SiteMap siteMap = new SiteMap(root);
        try {
            siteMap.exploreMap(new DiscoverMapOperation());
        } catch (Exception e) {
            System.out.println(UNEXPECTED_SITUATION_MSG);
           System.out.println(e.getMessage());
        }

        //show the map with render operation
        siteMap.exploreMap(new ConsoleRenderMapOperation());
    }
}
