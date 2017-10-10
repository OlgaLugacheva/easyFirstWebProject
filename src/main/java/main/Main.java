package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.MirrorServlet;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
         Logger logger = Logger.getLogger(Main.class);
        //создаем сервлет, который обрабытывает все запросы
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        MirrorServlet mirrorServlet = new MirrorServlet();
        //создаем хендлер и закладываем в него сервелет
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //сервлет будет слушать все /
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");
        //создаем jetty-сервер
        Server server = new Server(8080);
        server.setHandler(context);
        // запускаем сервер, jettyy создает пул потоков для обработки запросов
        server.start();

        logger.info("Server started ");
        //присоединяем main к jetty
        server.join();
    }
}
