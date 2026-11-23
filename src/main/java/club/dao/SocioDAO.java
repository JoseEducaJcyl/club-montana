package club.dao;

import club.model.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SocioDAO implements ISocioDAO{
    private final Session session;

    public SocioDAO(Session session) {
        this.session = session;
    }

    @Override
    public void insertarSocio(Socio s) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(s);   // INSERT
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void modificarSocio(Socio s) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(s); // UPDATE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void eliminarSocio(Socio s) {
        Transaction tx = session.beginTransaction();
        try {
            session.remove(s); // DELETE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Socio> listarSocios() {
        return session.createSelectionQuery("FROM Socio", Socio.class).getResultList();
    }
}
