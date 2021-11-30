import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Consultas c = new Consultas();
        int op;
        do {
            System.out.println("Selecciona la consulta a realizar:\n" +
                    "1.Consulta parametrizada con JPQL que permita encontrar todos los personajes" +
                    " que han sido alumnos de un maestro.\n" +
                    "3.Consultas nativeQuery inventadas.");
            op = Integer.parseInt(inp.readLine());

            switch (op) {
                case 1:
                    c.alumnosDeMaestro(inp, em);
                    break;
                case 2:
                    System.out.println("1.consultas nominales (namedQuery) con sintaxis JPSQL del personaje con mas puntos.\n" +
                            "2.consultas nominales (namedQuery) con sintaxis JPSQL del personaje con mas puntos entregados.");
                    int opc = Integer.parseInt(inp.readLine());
                    switch (opc){
                        case 1:
                            c.alumnosMasPuntos(em);
                            break;
                        case 2:
                            c.alumnosMasPuntosEntregados(em);
                            break;
                    }
                    break;

                case 3:
                    c.nativeQuery(inp,em);
            }
        }while(op == 5);
    }
}
