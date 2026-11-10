package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Clase de utilidad que crea UNA SOLA VEZ la SessionFactory (patrón Singleton).
 *
 * ¿Qué es la SessionFactory?
 *  - Es un objeto PESADO: lee la configuración, comprueba el mapeo de las entidades
 *    y prepara el pool de conexiones. Por eso solo se crea una vez por aplicación.
 *  - Es la "fábrica" de Session. Cada Session es una conversación ligera con la BD.
 */
public class HibernateUtil {

    // Única instancia de la fábrica, compartida por toda la aplicación
    private static final SessionFactory sessionFactory = construirSessionFactory();

    // Constructor privado: es una clase de utilidad, no se instancia
    private HibernateUtil() {}

    private static SessionFactory construirSessionFactory() {
        try {
            // 1) Leemos el fichero hibernate.cfg.xml (src/main/resources) y creamos el "registro de servicios":
            //    contiene los datos de conexión, show_sql, etc.
            StandardServiceRegistry registro = new StandardServiceRegistryBuilder()
                    .configure() // sin parámetros busca "hibernate.cfg.xml" en el classpath
                    .build();

            // 2) Con el registro leemos los metadatos (las clases @Entity indicadas con <mapping> en el xml)
            Metadata metadatos = new MetadataSources(registro).buildMetadata();

            // 3) Construimos la fábrica de sesiones
            return metadatos.buildSessionFactory();

        } catch (Exception e) {
            System.err.println("Error al crear la SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /** Devuelve la fábrica para que los DAO puedan abrir sesiones. */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /** Cierra la fábrica y libera las conexiones. Se llama al terminar el programa. */
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
