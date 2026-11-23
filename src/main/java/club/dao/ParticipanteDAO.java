package club.dao;

import club.model.Participante;
import club.model.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ParticipanteDAO implements IParticipanteDAO{
    private final Session session;

    public ParticipanteDAO(Session session) {
        this.session = session;
    }

    @Override
    public void insertarParticipante(Participante p) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(p);   // INSERT
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void modificarParticipante(Participante p) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(p); // UPDATE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void eliminarParticipante(Participante p) {
        Transaction tx = session.beginTransaction();
        try {
            session.remove(p); // DELETE
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Participante> listarParticipantes(int idRuta) {
        return session.createSelectionQuery("FROM Participante p WHERE p.ruta.id = :idRuta", Participante.class)
                .setParameter("idRuta", idRuta)
                .getResultList();
    }
}
