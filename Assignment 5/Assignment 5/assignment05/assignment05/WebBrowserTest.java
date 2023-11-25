package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowserTest {

    private WebBrowser webBrowser;
    private URL url1, url2, url3;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        webBrowser = new WebBrowser();
        url1 = new URL("http://example1.com");
        url2 = new URL("http://example2.com");
        url3 = new URL("http://example3.com");
    }

    @Test
    public void testVisitAndGetCurrentWebpage() {
        webBrowser.visit(url1);
        Assertions.assertEquals(url1, webBrowser.currentWebpage);

        webBrowser.visit(url2);
        Assertions.assertEquals(url2, webBrowser.currentWebpage);
    }

    @Test
    public void testBackWithEmptyHistory() {
        Assertions.assertThrows(NoSuchElementException.class, webBrowser::back);
    }

    @Test
    public void testBackNavigation() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        Assertions.assertEquals(url1, webBrowser.back());
    }

    @Test
    public void testForwardWithEmptyHistory() {
        Assertions.assertThrows(NoSuchElementException.class, webBrowser::forward);
    }

    @Test
    public void testForwardNavigation() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.back();
        Assertions.assertEquals(url2, webBrowser.forward());
    }

    @Test
    public void testHistory() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.back();
        SinglyLinkedList<URL> history = webBrowser.history();
        Assertions.assertFalse(history.isEmpty());
        Assertions.assertEquals(url1, history.getFirst());
    }

    @Test
    public void testConstructorWithHistory() throws MalformedURLException {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        history.insertFirst(url1);
        history.insertFirst(url2);

        WebBrowser webBrowserWithHistory = new WebBrowser(history);
        Assertions.assertEquals(url2, webBrowserWithHistory.currentWebpage);
        Assertions.assertEquals(url1, webBrowserWithHistory.back());
    }

//    @Test
//    public void testConstructorWithHistory() throws MalformedURLException {
//        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
//        history.insertFirst(url1);
//        history.insertFirst(url2);
//        history.insertFirst(url3);
//
//        WebBrowser webBrowserWithHistory = new WebBrowser(history);
//        Assertions.assertEquals(url3, webBrowserWithHistory.currentWebpage);
//        Assertions.assertEquals(url2, webBrowserWithHistory.back());
//    }
}
