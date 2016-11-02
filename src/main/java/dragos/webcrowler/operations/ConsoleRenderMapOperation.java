package dragos.webcrowler.operations;

import static java.lang.Boolean.TRUE;

import dragos.webcrowler.sitemap.TNode;

public class ConsoleRenderMapOperation implements MapOperation {

    @Override
    public void processNode(TNode node) {
        print(node, "", TRUE);
    }

    private void print(TNode node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getLink());
        //rendering all nodes except last one
        for (int i = 0; i < node.getChildren().size() - 1; i++) {
            TNode tNode = node.getChildren().get(i);
            print(tNode, prefix + (isTail ? "    " : "│   "), false);
        }

        //rendering last node
        if (node.getChildren().size() > 0) {
            TNode child = node.getChildren().get(node.getChildren().size() - 1);
            print(child, prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
