package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitCrawling
 *
 */
@WebListener
public class InitCrawling implements ServletContextListener {

    public InitCrawling() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { // 실행시 수행 크롤링
    }
	
}
