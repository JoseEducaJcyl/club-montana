package club.dao;

import club.model.Socio;

import java.util.List;

public interface ISocioDAO {
    void insertarSocio(Socio ruta);

    void modificarSocio (Socio ruta);

    void eliminarSocio (Socio ruta);

    List<Socio> listarSocios();
}
