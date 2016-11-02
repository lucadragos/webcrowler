package dragos.webcrowler.sitemap.test;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
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
    public void checkMapIsUsingTheMapOperation() {
        final Flag flag =new Flag();
        TNode root = new TNode("1", false);
        SiteMap siteMap = new SiteMap(root);

        siteMap.exploreMap(new MapOperation() {
            @Override
            public void processNode(TNode node) {
                flag.setFlag(true);
            }
        });

        assertThat(flag.getFlag(),is(TRUE));
    }

    class Flag {
        boolean flag;

        public boolean getFlag() {
            return flag;
        }
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
