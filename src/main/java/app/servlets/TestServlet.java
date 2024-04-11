package app.servlets;

import app.entities.Room;
import app.repositories.RoomRepository;
import app.services.RoomService;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TestServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init() {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        RoomRepository roomRepository = new RoomRepository(sessionFactory);
        roomService = new RoomService(roomRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/simple")) {
            List<Room> list = roomService.findWithoutIndex();

            writer.write(String.valueOf(list));
        } else if (requestURI.endsWith("/index")) {
            List<Room> list = roomService.findWithIndex();

            writer.write(String.valueOf(list));
        } else {
            writer.write("Your request is invalid");
        }
    }
}
