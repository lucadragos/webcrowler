package dragos.webcrowler.sitemap;

import dragos.webcrowler.exceptions.AppException;
import dragos.webcrowler.operations.MapOperation;

public class SiteMap {
    private TNode root;

    public SiteMap(TNode root) {
        checkRootNode(root);
        this.root = root;
    }

    public void exploreMap(MapOperation mapOperation) {
        mapOperation.processNode(root);
    }

    private void checkRootNode(TNode root) {
        if (root == null) {
            throw new AppException("Root node can not be null");
        }
    }
}
