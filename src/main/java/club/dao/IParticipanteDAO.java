package club.dao;

import club.model.Participante;

import java.util.List;

public interface IParticipanteDAO {
    void insertarParticipante (Participante p);

    void modificarParticipante (Participante p);

    void eliminarParticipante(Participante p);

    List<Participante> listarParticipantes(int idRuta);
}
