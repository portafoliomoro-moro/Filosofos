package filosofos.datos;

import filosofos.dominio.Filosofo;

import java.util.List;

public interface IFilosofoDAO {
    List<Filosofo> listarFilosofo();
    boolean buscarFilosofoPorId(Filosofo filosofo);
    boolean agregarFilosofo(Filosofo filosofo);
    boolean modificarFilosofo(Filosofo filosofo);
    boolean eliminarFilosofo(Filosofo filosofo);
}
