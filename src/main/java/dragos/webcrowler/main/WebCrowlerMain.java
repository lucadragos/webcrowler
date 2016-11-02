package dragos.webcrowler.main;

import java.util.Optional;

import dragos.webcrowler.sitemap.SiteMap;
import dragos.webcrowler.sitemap.TNode;

public class WebCrowlerMain {
    private static final int NO_ARGS_CONSTANT = 0;
    private static final String DEFAULT_ROOT_LINK = "http://wiprodigital.com";
    private static final int LINK_PARAM_INDEX = 0;

    public static void main(String[] args) {
        Optional<String> rootLinkOptional;
        if (args.length == NO_ARGS_CONSTANT) {
            rootLinkOptional = Optional.of(DEFAULT_ROOT_LINK);
        } else {
            rootLinkOptional = Optional.of(args[LINK_PARAM_INDEX]);
        }


        //create the map with discovery operation
        TNode root = new TNode(rootLinkOptional.get());
        SiteMap siteMap = new SiteMap(root);
        //TODO: add op

        //show the map with render operation
        //TODO: add op
    }
}
