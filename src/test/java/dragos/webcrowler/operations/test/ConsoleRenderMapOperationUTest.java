package dragos.webcrowler.operations.test;

import dragos.webcrowler.operations.RenderSiteMap;
import dragos.webcrowler.sitemap.TNode;
import org.junit.Test;

public class ConsoleRenderMapOperationUTest {

    @Test
    public void checkConsoleDisplay() {
        TNode root = new TNode("1", false);
        TNode sTNode1 = new TNode("2", false);
        TNode sTNode2 = new TNode("3", false);
        TNode ssTnode3 = new TNode("4", false);

        root.addChild(sTNode1);
        root.addChild(sTNode2);
        sTNode2.addChild(ssTnode3);
        ssTnode3.addChild(sTNode1);

        RenderSiteMap consoleRender = new RenderSiteMap();
        consoleRender.processNode(root);
    }
}
