package dragos.webcrowler.operations;

import static java.lang.Boolean.TRUE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dragos.webcrowler.exceptions.AppException;
import dragos.webcrowler.sitemap.TNode;

public class RenderSiteMap implements MapOperation {

    private static final String UNABLE_TO_RENDER_THE_SITEMAP = "Unable to render the sitemap";

    File file;
    BufferedWriter bufferedWriter;

    public RenderSiteMap(){}

    public RenderSiteMap(String fileName) throws IOException {
        initFile(fileName);
    }

    @Override
    public void processNode(TNode node) {
        try {
            render(node, "", TRUE);
        } catch (IOException e) {
            throw new AppException(UNABLE_TO_RENDER_THE_SITEMAP,e);
        }
    }

    private void initFile(String fileName) throws IOException {
        if (fileName != null && !fileName.trim().isEmpty()) {
            file = new File(fileName);
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        }
    }

    private void render(TNode node, String prefix, boolean isTail) throws IOException {
        if (file == null) {
            renderToConsole(node, prefix, isTail);
        } else {
            renderToFile(node, prefix, isTail);
        }
        //rendering all nodes except last one
        for (int i = 0; i < node.getChildren().size() - 1; i++) {
            TNode tNode = node.getChildren().get(i);
            render(tNode, prefix + (isTail ? "    " : "│   "), false);
        }

        //rendering last node
        if (node.getChildren().size() > 0) {
            TNode child = node.getChildren().get(node.getChildren().size() - 1);
            render(child, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    private void renderToConsole(TNode node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getLink());
    }

    private void renderToFile(TNode node, String prefix, boolean isTail) throws IOException {
        bufferedWriter.write(prefix + (isTail ? "└── " : "├── ") + node.getLink());
        bufferedWriter.newLine();
    }
}
