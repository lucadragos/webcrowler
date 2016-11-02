package dragos.webcrowler.sitemap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import dragos.webcrowler.exceptions.AppException;
import dragos.webcrowler.operations.MapOperation;

public class SiteMap {
    private TNode root;

    public SiteMap(TNode root){
        checkRootNode(root);
        this.root = root;
    }

    public void exploreMap(MapOperation mapOperation) {
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);

        HashMap<String,TNode> visited = new HashMap<>();

        while(!queue.isEmpty()) {
            TNode node = queue.poll();
            if (!visited.containsKey(node.getLink())) {
                visited.put(node.getLink(),node);

                mapOperation.processNode(node);

                //operation to discover
                //discover the nodes
                //evaluate the nodes to be and then add them to the

                for(TNode child : node.getChildren()) {
                    if (!visited.containsKey(child.getLink())){
                        queue.add(child);
                    }
                }
            }
        }
    }

    private void checkRootNode(TNode root) {
        if (root == null) {
            throw new AppException("Root node can not be null");
        }
    }
}
