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
                    "1.consulta parametrizada con JPQL que permita encontrar todos los personajes que han sido alumnos de un maestro.\n");
            op = Integer.parseInt(inp.readLine());

            switch (op) {
                case 1:
                    c.alumnosDeMaestro(inp);
                    break;
            }
        }while(op == 5);
    }
}
