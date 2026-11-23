package club.dao;

import club.model.Ruta;

import java.util.List;


public interface IRutaDAO {
    void insertarRuta (Ruta ruta);

    void modificarRuta (Ruta ruta);

    void eliminarRuta (Ruta ruta);

    List<Ruta> listarRutas();
}
