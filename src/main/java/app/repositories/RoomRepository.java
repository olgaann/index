package app.repositories;

import app.entities.Room;
import app.entities.TestRoom;
import app.entities.SingleRoom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoomRepository {
    private SessionFactory sessionFactory;
    private static Random random = new Random();

    public RoomRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Room> findAll() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            List<Room> roomList;

            transaction = session.beginTransaction();
            String hql = "FROM Room";
            Query<Room> query = session.createQuery(hql, Room.class);
            roomList = query.list();

            transaction.commit();
            return roomList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public Optional<Room> add(int number) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TestRoom room = new TestRoom(485);
            session.save(room);
            //Room newRoom = new SuiteRoom(number, "KING");
            Room newRoom = new SingleRoom(number, 15);
            newRoom.setNumber(number);
            session.save(newRoom);
            transaction.commit();

            return Optional.of(newRoom);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return Optional.empty();
        } finally {
            session.close();
        }
    }


    public List<Room> addRandomTestRooms(int count) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            long startTime = System.currentTimeMillis();
            List<Room> randomRooms = IntStream.generate(() -> random.nextInt())
                    .limit(count)
                    .mapToObj(random -> new TestRoom(random))
                    .collect(Collectors.toList());

            int batchSize = 100;

            for (int i = 0; i < randomRooms.size(); i++) {
                session.save(randomRooms.get(i));

                if (i % batchSize == 0 && i > 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Время выполнения: " + duration + " миллисекунд");
            return randomRooms;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public List<Room> findWithoutIndex() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            List<Room> roomList;
            long startTime = System.currentTimeMillis();
            transaction = session.beginTransaction();
            String hql = "FROM TestRoom t WHERE t.randomInt > 2000";
            Query<Room> query = session.createQuery(hql, Room.class);
            roomList = query.list();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Время выполнения без индексации: " + duration + " миллисекунд");
            transaction.commit();
            return roomList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public List<Room> findWithIndex() { //TODO тут надо индекс навесить через терминал, в коде этого нет!
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            List<Room> roomList;
            long startTime = System.currentTimeMillis();
            transaction = session.beginTransaction();

            String explainSQL = "EXPLAIN SELECT * FROM test_rooms WHERE randomint > 2000";
            NativeQuery explainQuery = session.createNativeQuery(explainSQL);
            List explainResult = explainQuery.list();
            for (Object row : explainResult) {
                System.out.println(row);
            }


            String hql = "FROM TestRoom t WHERE t.randomInt > 2000";
            Query<Room> query = session.createQuery(hql, Room.class);
            roomList = query.list();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Время выполнения c индексацией: " + duration + " миллисекунд");
            transaction.commit();
            return roomList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}
