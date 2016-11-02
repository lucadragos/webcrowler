package dragos.webcrowler.parser;

import java.io.IOException;

import dragos.webcrowler.sitemap.TNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WiproFindLinksParser {
    private static final String HTTP = "http";
    private static final String WIPRODIGITAL = "wiprodigital.com";
    private static final String HREF = "href";
    private static final String A_HREF = "a[href]";
    private static final String IGNORING_EMPTY_LINK = "Ignoring empty link";
    private static final String HASHTAG = "#";
    private static final String UNABLE_TO_VISIT_NODE = "Unable to visit node";
    private static final String FORWARDS_SLASH = "/";
    private static final String IMG_SRC = "img[src]";
    private static final String SRC = "src";

    public static void findLinks(String rootLink, TNode node) {
        if (node.canVisit()) {
            try {
                if (isValidLink(node.getLink())) {
                    String linkToVisit;
                    if ( node.getLink().startsWith(FORWARDS_SLASH) ) {
                        linkToVisit = rootLink+node.getLink();
                    } else {
                        linkToVisit = node.getLink();
                    }
                    Document document = Jsoup.connect(linkToVisit).get();
                    Elements links = document.select(A_HREF);
                    for (Element element : links) {
                        String link = element.attr(HREF);
                        boolean isInternal = checkLinkIsInternal(link);
                        try {
                            node.addChild(new TNode(link, isInternal));
                        } catch (Exception e) {
                            System.out.println(IGNORING_EMPTY_LINK);
                        }
                    }
                    Elements images = document.select(IMG_SRC);
                    for (Element image : images) {
                        node.addChild(new TNode(image.attr(SRC), false));
                    }
                }
            } catch (IOException e) {
                System.out.println(UNABLE_TO_VISIT_NODE);
            }
        }
    }

    public static boolean isValidLink(String link) {
        boolean canBeVisited = false;
        if (link.startsWith(HASHTAG)) {
            return false;
        }

        if (link.startsWith(HTTP)) {
            return true;
        }

        if (link.startsWith(FORWARDS_SLASH)) {
            return true;
        }

        return canBeVisited;
    }

    public static boolean checkLinkIsInternal(String link) {
        return link.contains(WIPRODIGITAL);
    }
}
