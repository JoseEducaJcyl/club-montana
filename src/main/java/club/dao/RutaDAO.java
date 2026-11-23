package club.dao;

import club.model.Ruta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RutaDAO implements IRutaDAO{
    private final Session session;

    public RutaDAO(Session session) {
        this.session = session;
    }

    @Override
    public void insertarRuta(Ruta r) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(r);   // INSERT
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void modificarRuta(Ruta r) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(r); // UPDATE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void eliminarRuta(Ruta r) {
        Transaction tx = session.beginTransaction();
        try {
            session.remove(r); // DELETE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Ruta> listarRutas() {
        return session.createQuery("SELECT r FROM Ruta r JOIN FETCH r.guia", Ruta.class).getResultList();
    }
}
