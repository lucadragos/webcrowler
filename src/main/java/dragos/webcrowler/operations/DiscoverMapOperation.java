package dragos.webcrowler.operations;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import dragos.webcrowler.parser.WiproFindLinksParser;
import dragos.webcrowler.sitemap.TNode;

public class DiscoverMapOperation implements MapOperation {

    public static final String NODE_INFO = "Explored node: %s";

    @Override
    public void processNode(TNode root) {
        Queue<TNode> queue = new LinkedList<>();
        HashMap<String, TNode> visited = new HashMap<>();

        String rootLink = root.getLink();
        queue.add(root);
        int nodeCounter = 0;

        while (!queue.isEmpty()) {
            TNode node = queue.poll();
            if (!visited.containsKey(node.getLink())) {
                visited.put(node.getLink(), node);

                WiproFindLinksParser.findLinks(rootLink, node);

                for (TNode child : node.getChildren()) {
                    if (!visited.containsKey(child.getLink())) {
                        queue.add(child);
                    }
                }

                nodeCounter++;
                System.out.println(format(NODE_INFO, nodeCounter));
            }
        }
    }
}
