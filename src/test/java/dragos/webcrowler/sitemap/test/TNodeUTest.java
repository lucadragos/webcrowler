package dragos.webcrowler.sitemap.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import dragos.webcrowler.exceptions.AppException;
import dragos.webcrowler.sitemap.TNode;
import org.junit.Test;

public class TNodeUTest {

    @Test
    public void checkThat2NodesWithTheSameLinkAreEquals() {
        String dummyLink = "dummyLink";
        TNode tNode1 = new TNode(dummyLink);
        TNode tNode2 = new TNode(dummyLink);

        assertThat(tNode1, equalTo(tNode2));
    }

    @Test
    public void check2NodesAreDifferentIfTheLinkIsDifferent() {
        String dummyLink = "dummyLink";
        String dummyLink1 = "dummyLink1";
        TNode tNode1 = new TNode(dummyLink);
        TNode tNode2 = new TNode(dummyLink1);

        assertThat(tNode1, not(equalTo(tNode2)));
    }

    @Test
    public void equalCheckIsAlwaysIfTheCompareObjectIsNotOfTheSameType() {
        String dummyLink = "dummyLink";
        Object object = new Object();
        TNode tNode1 = new TNode(dummyLink);

        assertThat(tNode1, not(equalTo(object)));
    }

    @Test
    public void checkNodeIsAddedToChildren() {
        int expectedSize = 1;
        String dummyLink = "dummyLink";
        String dummyLink1 = "dummyLink1";
        TNode tNode1 = new TNode(dummyLink);
        TNode tNode2 = new TNode(dummyLink1);

        tNode1.addChild(tNode2);

        assertThat(tNode1.getChildren().size(), equalTo(expectedSize));
        assertThat(tNode1.getChildren(), hasItem(tNode2));
    }

    @Test(expected = AppException.class)
    public void checkExceptionIsThrownIfLinkIsEmpty() {
        String emptyString = "";
        TNode node = new TNode(emptyString);
    }

    @Test(expected = AppException.class)
    public void checkExceptionIsThrownIfLinkIsWhiteSpacesOnly() {
        String emptyString = " ";
        TNode node = new TNode(emptyString);
    }

    @Test(expected = AppException.class)
    public void checkExceptionIsThrownIfLinkIsNull() {
        String nullLink = null;
        TNode node = new TNode(nullLink);
    }
}
