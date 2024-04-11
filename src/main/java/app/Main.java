package app;

import app.entities.Client;
import app.entities.Room;
import app.listener.ContextListener;
import app.services.RoomService;
import app.servlets.RoomServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@WebListener
public class Main {


    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            //формулируем запрос для выборки всех SingleRoom и тех SuiteRoom, которые находятся на третьем этаже
            String hql = "FROM Room r WHERE (TYPE(r) = SingleRoom) OR (TYPE(r) = SuiteRoom AND r.floor = 3)";
            List<Room> rooms = session.createQuery(hql, Room.class).getResultList();
            System.out.println(rooms);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }


    }
}
