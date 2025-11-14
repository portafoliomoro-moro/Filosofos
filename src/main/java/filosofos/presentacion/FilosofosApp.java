package filosofos.presentacion;

import filosofos.datos.FilosofoDAO;
import filosofos.datos.IFilosofoDAO;
import filosofos.dominio.Filosofo;

import java.util.Scanner;

public class FilosofosApp {
    public static void main(String[] args) {
        filosofosApp();
    }

    public static void filosofosApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        IFilosofoDAO filosofoDao = new FilosofoDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(opcion, consola, filosofoDao);
            }catch(Exception e){
                System.out.println("Error al ingresar opción: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Filósofos
                1. Listar Filósofo
                2. Buscar Filósofo
                3. Agregar Filósofo
                4. Modificar Filósofo
                5. Eliminar Filósofo
                6. Salir
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner consola, IFilosofoDAO folosofoDao){
        var salir = false;
        switch(opcion){
            case 1 -> {
                System.out.println("--- Listar Filósofo ---");
                var listarFilosofo = folosofoDao.listarFilosofo();
                listarFilosofo.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("--- Buscar Filósofo ---");
                System.out.print("Id del filosofo a buscar: ");
                var idFilosofo = Integer.parseInt(consola.nextLine());
                var buscarFilosofo = new Filosofo(idFilosofo);
                var encontrado = folosofoDao.buscarFilosofoPorId(buscarFilosofo);
                if(encontrado){
                    System.out.println("Filósofo encontrado: " + buscarFilosofo);
                }
                else{
                    System.out.println("Filósofo No encontrado: " + buscarFilosofo);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar Filósofo ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Pensamiento: ");
                var pensamiento = consola.nextLine();
                var agregarFilosofo = new Filosofo(nombre, apellido, pensamiento);
                var agregado = folosofoDao.agregarFilosofo(agregarFilosofo);
                if(agregado){
                    System.out.println("Filósofo agregado: " + agregarFilosofo);
                }
                else{
                    System.out.println("Filósofo No agregado: " + agregarFilosofo);
                }
            }
            case 4 -> {
                System.out.println("--- Modificar Filósofo ---");
                System.out.print("Id del filósofo a modificar: ");
                var idFilosofo = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Pensamiento: ");
                var pensamiento = consola.nextLine();
                var modificarFilosofo = new Filosofo(idFilosofo, nombre, apellido, pensamiento);
                var modificado = folosofoDao.modificarFilosofo(modificarFilosofo);
                if(modificado){
                    System.out.println("Filósofo modificado: " + modificarFilosofo);
                }
                else{
                    System.out.println("Filósofo No modificado: " + modificarFilosofo);
                }
            }
            case 5 -> {
                System.out.println("--- Eliminar Filósofo ---");
                System.out.print("Id del filósofo a eliminar: ");
                var idFilosofo = Integer.parseInt(consola.nextLine());
                var eliminarFilosofo = new Filosofo(idFilosofo);
                var eliminado = folosofoDao.eliminarFilosofo(eliminarFilosofo);
                if(eliminado){
                    System.out.println("Filósofo eliminado: " + eliminarFilosofo);
                }
                else{
                    System.out.println("Filósofo No eliminado: " + eliminarFilosofo);
                }
            }
            case 6 -> {
                System.out.println("Hasta Pronto!");
                salir = true;
            }
            default -> System.out.println("Opción No reconocida: " + opcion);
        }
        return salir;
    }
}
