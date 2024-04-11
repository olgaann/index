package app.listener;

import app.HibernateUtil;
import app.repositories.RoomRepository;
import app.services.RoomService;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {

    private static ServletContext context;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        RoomService roomService = new RoomService(new RoomRepository(sessionFactory));
        servletContext.setAttribute("sessionFactory", sessionFactory);
        servletContext.setAttribute("roomService", roomService);
    }

    public static ServletContext getServletContext() {
        return context; // Метод для доступа к ServletContext из других классов
    }

}
