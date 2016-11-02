package dragos.webcrowler.sitemap;

import java.util.ArrayList;
import java.util.List;

public class TNode {
    private List<TNode> children;
    private String link;

    public TNode(String link) {
        this.link = link;
        children = new ArrayList<>();
    }

    public String getLink() {
        return link;
    }

    public void addChild(TNode child) {
        children.add(child);
    }

    public List<TNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = true;
        if (!(object instanceof TNode)) {
            result = false;
        } else {
            TNode tNode = (TNode) object;
            result = this.link.equals(tNode.getLink());
        }

        return result;
    }
}
