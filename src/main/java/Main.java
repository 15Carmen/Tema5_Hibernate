
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            setUp();
            create("Paca la Pirania", 1);
            //read(1);
            //update(1, "Paca la Pirania", 7)

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(String nombre, int numAciertos) {
        PersonasEntity persona = new PersonasEntity(nombre, numAciertos);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);

        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println(id);
        sessionFactory.close();
    }

    private static void read(int id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class, id);//   PersonasEntity persona = session.load(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior

        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println(persona.getNombre());
        sessionFactory.close();
    }

    private static void update(int id, String nombre, int numAciertos) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class, id);
        persona.setNombre(nombre);
        persona.setNumAciertos(numAciertos);

        // session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(persona);

        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        sessionFactory.close();
    }

    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
