package dragos.webcrowler.parser;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

public class WiproFindLinksParserUTest {
    @Test
    public void trueIsReturnedIfLinkedIsWiproInternal() {
        String testLink = "www.wiprodigital.com/test";
        boolean isInternal = WiproFindLinksParser.checkLinkIsInternal(testLink);
        assertTrue(isInternal);
    }

    @Test
    public void pageBookmarksAreNotValid() {
        String testLink = "#test";
        boolean isValid = WiproFindLinksParser.isValidLink(testLink);
        assertFalse(isValid);
    }

    @Test
    public void httpLinksAreValid() {
        String testLink = "http://www.wiprodigital.com/test";
        boolean isValid = WiproFindLinksParser.isValidLink(testLink);
        assertTrue(isValid);
    }

    @Test
    public void relativeLinkAreValid() {
        String testLink = "/home";
        boolean isValid = WiproFindLinksParser.isValidLink(testLink);
        assertTrue(isValid);
    }
}
