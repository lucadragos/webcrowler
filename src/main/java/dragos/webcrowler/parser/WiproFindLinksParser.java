package dragos.webcrowler.parser;

import java.io.IOException;

import dragos.webcrowler.sitemap.TNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WiproFindLinksParser {
    public static final String HTTP = "http";
    public static final String WIPRODIGITAL = "wiprodigital.com";
    public static final String HREF = "href";
    public static final String A_HREF = "a[href]";
    public static final String IGNORING_EMPTY_LINK = "Ignoring empty link";
    public static final String HASHTAG = "#";
    public static final String UNABLE_TO_VISIT_NODE = "Unable to visit node";
    public static final String FORWARDS_SLASH = "/";

    public static void findLinks(String rootLink, TNode node) {
        if (node.canVisit()) {
            try {
                if (isValidLink(node)) {
                    //TODO: to remove content
                    System.out.println(node.getLink());

                    String linkToVisit;
                    if ( node.getLink().startsWith("/") ) {
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
                }
            } catch (IOException e) {
                System.out.println(UNABLE_TO_VISIT_NODE);
            }
        }
    }

    public static boolean isValidLink(TNode node) {
        boolean canBeVisited = false;
        String link = node.getLink();
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
