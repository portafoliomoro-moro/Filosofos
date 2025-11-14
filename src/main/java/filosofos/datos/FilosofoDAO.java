package filosofos.datos;

import filosofos.dominio.Filosofo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static filosofos.conexion.Conexion.getConexion;

public class FilosofoDAO implements IFilosofoDAO {

    @Override
    public List<Filosofo> listarFilosofo() {
        List<Filosofo> filosofos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM filosofos ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var filosofo = new Filosofo();
                filosofo.setNombre(rs.getString("nombre"));
                filosofo.setApellido(rs.getString("apellido"));
                filosofo.setPensamiento(rs.getString("pensamiento"));
                filosofos.add(filosofo);
            }
        }catch(Exception e){
            System.out.println("Error al listar los registros: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return filosofos;
    }

    @Override
    public boolean buscarFilosofoPorId(Filosofo filosofo) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM filosofos WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, filosofo.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                filosofo.setNombre(rs.getString("nombre"));
                filosofo.setApellido(rs.getString("apellido"));
                filosofo.setPensamiento(rs.getString("pensamiento"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al buscar registro por id: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarFilosofo(Filosofo filosofo) {
        PreparedStatement ps;
        Connection con =  getConexion();
        var sql = "INSERT INTO filosofos(nombre, apellido, pensamiento) VALUES (?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, filosofo.getNombre());
            ps.setString(2, filosofo.getApellido());
            ps.setString(3, filosofo.getPensamiento());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al agregar registro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarFilosofo(Filosofo filosofo) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE filosofos SET nombre=?, apellido=?, pensamiento=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, filosofo.getNombre());
            ps.setString(2, filosofo.getApellido());
            ps.setString(3, filosofo.getPensamiento());
            ps.setInt(4, filosofo.getId());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al modificar registro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarFilosofo(Filosofo filosofo) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "DELETE FROM filosofos WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, filosofo.getId());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar registro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        IFilosofoDAO filosofoDao = new FilosofoDAO();
//        System.out.println("*** Eliminar Filósofo ***");
//        var eliminar = new Filosofo(4);
//        var eliminado = filosofoDao.eliminarFilosofo(eliminar);
//        if(eliminado)
//            System.out.println("Registro eliminado: " + eliminar);
//        else
//            System.out.println("Rgistro No eliminado: " + eliminar);
//    }
}
