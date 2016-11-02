package dragos.webcrowler.sitemap.test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import dragos.webcrowler.exceptions.AppException;
import dragos.webcrowler.operations.MapOperation;
import dragos.webcrowler.sitemap.SiteMap;
import dragos.webcrowler.sitemap.TNode;
import org.junit.Test;

public class SiteMapUTest {

    @Test(expected = AppException.class)
    public void exceptionIsThrownIfRootNodeIsNull() {
        TNode root = null;
        SiteMap siteMap = new SiteMap(root);
    }

    @Test
    public void checkExplorationIsNotAddingDuplicateNodes() {
        TNode root = new TNode("1");
        TNode sTNode1 = new TNode("2");
        TNode sTNode2 = new TNode("3");
        TNode ssTnode3 = new TNode("4");

        root.addChild(sTNode1);
        root.addChild(sTNode2);
        sTNode2.addChild(ssTnode3);
        ssTnode3.addChild(sTNode1);


        final List<TNode> processedLinks = new ArrayList<>();

        SiteMap siteMap = new SiteMap(root);

        siteMap.exploreMap(new MapOperation() {
            @Override
            public void processNode(TNode node) {
                processedLinks.add(node);
            }
        });

        assertThat(processedLinks, hasItems(root,sTNode1,sTNode2,ssTnode3));
    }
}
