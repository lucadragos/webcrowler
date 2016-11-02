package dragos.webcrowler.sitemap;

import java.util.ArrayList;
import java.util.List;

import dragos.webcrowler.exceptions.AppException;

public class TNode {
    private static final String EMPTY_NODE_MSG = "Node can not be empty";
    private List<TNode> children;
    private String link;
    private boolean canVisit;

    public TNode(String link, boolean canVisit) {
        checkLinkIsNotEmpty(link);
        this.link = link;
        children = new ArrayList<>();
        this.canVisit = canVisit;
    }

    public String getLink() {
        return link;
    }

    public boolean canVisit() {
        return canVisit;
    }

    public void addChild(TNode child) {
        children.add(child);
    }

    public List<TNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object object) {
        boolean result;
        if (!(object instanceof TNode)) {
            result = false;
        } else {
            TNode tNode = (TNode) object;
            result = this.link.equals(tNode.getLink());
        }

        return result;
    }

    private void checkLinkIsNotEmpty(String link) {
        if (link == null || link.trim().isEmpty()) {
            throw new AppException(EMPTY_NODE_MSG);
        }
    }
}
