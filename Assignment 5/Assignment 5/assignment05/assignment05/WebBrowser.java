package assignment05;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WebBrowser {
    private LinkedListStack<URL> backStack; // 后退栈 | Stack for back navigation
    private LinkedListStack<URL> forwardStack; // 前进栈 | Stack for forward navigation
    URL currentWebpage; // 当前网页 | The current webpage

    // Constructor for a new browser without history
    // 无历史记录的新浏览器构造函数
    public WebBrowser() {
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        currentWebpage = null;
    }

    // Constructor for a new browser with history
    // 带历史记录的新浏览器构造函数
    public WebBrowser(SinglyLinkedList<URL> history) {
        this(); // Call the default constructor
        if (!history.isEmpty()) {
            Iterator<URL> iterator = history.iterator();
            // Set the current webpage to the first element of the history
            // 将当前网页设置为历史记录的第一个元素
            if (iterator.hasNext()) {
                currentWebpage = iterator.next();
                // Add the rest of the history into the back stack
                // 将剩余的历史记录加入后退栈
                while (iterator.hasNext()) {
                    backStack.push(iterator.next());
                }
            }
        }
    }

    // Visit a webpage
    // 访问网页
    public void visit(URL webpage) {
        if (currentWebpage != null) {
            backStack.push(currentWebpage); // Push the current webpage to the back stack
        }
        currentWebpage = webpage; // Set the current webpage
        forwardStack.clear(); // Clear the forward stack
    }

    // Navigate back
    // 后退导航
    public URL back() {
        if (backStack.isEmpty()) {
            throw new NoSuchElementException("No previously-visited URL");
        }
        forwardStack.push(currentWebpage); // Push the current webpage to the forward stack
        currentWebpage = backStack.pop(); // Set the current webpage to the top of the back stack
        return currentWebpage;
    }

    // Navigate forward
    // 前进导航
    public URL forward() {
        if (forwardStack.isEmpty()) {
            throw new NoSuchElementException("No URL to visit next");
        }
        backStack.push(currentWebpage); // Push the current webpage to the back stack
        currentWebpage = forwardStack.pop(); // Set the current webpage to the top of the forward stack
        return currentWebpage;
    }

    // Get browsing history
    // 获取浏览历史
    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        LinkedListStack<URL> tempStack = new LinkedListStack<>();

        // Copy back stack to temporary stack and reverse order
        // 复制后退栈到临时栈，并反转顺序
        while (!backStack.isEmpty()) {
            tempStack.push(backStack.pop());
        }

        // Refill history list and back stack from temporary stack
        // 从临时栈回填到历史列表和后退栈
        while (!tempStack.isEmpty()) {
            URL url = tempStack.pop();
            history.insertFirst(url);
            backStack.push(url);
        }

        // If there is a current webpage, add it to history as well
        // 如果有当前网页，也加入历史
        if (currentWebpage != null) {
            history.insertFirst(currentWebpage);
        }

        return history;
    }
}
