import entity.Course;
import entity.House;
import entity.HousePoints;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Consultas {

    public void alumnosDeMaestro(BufferedReader inp, EntityManager em) throws IOException {
        System.out.println("Nombre del profesor: ");
        String nombreProfesor = inp.readLine();

        String sentencia = "SELECT * " +
                                "FROM person AS p " +
                                    "INNER JOIN enrollment AS e " +
                                        "ON p.id = e.person_enrollment " +
                                "WHERE e.course_enrollment = " +
                                            "(SELECT c.id " +
                                                 "FROM course as c " +
                                                     "INNER JOIN person AS pe " +
                                                         "ON c.teacher_id = pe.id " +
                                                 "WHERE pe.first_name = '"+nombreProfesor+"')";
        Query q = em.createNativeQuery(sentencia);


        List<Person> list = (List<Person>) q.getResultList();
        for(Person p: list){
            System.out.println(p.toString());
        }

    }
    public void alumnosMasPuntos(EntityManager em) {
        Query q = em.createNamedQuery("Persona.PuntuacionMasAlta",HousePoints.class);
        List<HousePoints> l= q.getResultList();
        for (HousePoints e1 : l){
            System.out.println(e1);
        }
    }
    public void alumnosMasPuntosEntregados(EntityManager em) {
        Query q = em.createNamedQuery("Persona.PuntuacionEntregada", HousePoints.class);
        List<HousePoints> h= q.getResultList();
        for (HousePoints e1 : h){
            System.out.println(e1);
        }
    }


    public void nativeQuery(BufferedReader inp, EntityManager em) throws IOException {
        int op;
        do {
            System.out.println("Selecciona la consulta a realizar:\n" +
                    "1.Consulta con innerjoin\n" +
                    "2.Consulta con outerjoin\n" +
                    "3.Consulta con subconsulta.");
            op = Integer.parseInt(inp.readLine());

            switch (op) {
                case 1:
                    System.out.println("Introduce el first name de la persona para saber su casa: ");
                    String firstName = inp.readLine();
                    String sentencia = "SELECT h.name " +
                                            "FROM house AS h INNER JOIN person AS p " +
                                                    "ON h.id = p.house_id " +
                                            "WHERE p.first_name = '"+firstName+"'";
                    Query q = em.createNativeQuery(sentencia);
                    System.out.println((String)q.getSingleResult());

                    break;
                case 2:
                    System.out.println("Consulta creada con OUTER JOIN(LEFT)");
                    sentencia= "SELECT p.houseByHouseId, p.firstname FROM person p LEFT JOIN houses h ON p.houseByHouseId = h.id WHERE h.name='Gryffindor'";
                    q = em.createNativeQuery(sentencia);
                    List<House> h= q.getResultList();
                    for (House e1 : h){
                        System.out.println(e1);
                    }
                    break;

                case 3:
                    System.out.println("Introduce el first name del alumno para saber que profesores le dan clase: ");
                    firstName = inp.readLine();
                    sentencia = "SELECT p.first_name " +
                                    "FROM person AS p INNER JOIN course AS c " +
                                        "ON p.id = c.teacher_id " +
                                    "WHERE c.id IN " +
                                        "(SELECT e.course_enrollment " +
                                                "FROM enrollment as e INNER JOIN person as pe " +
                                                        "ON pe.id = e.person_enrollment " +
                                                "WHERE pe.id = '"+firstName+"')";
                    q = em.createNativeQuery(sentencia);
                    List<String> list =  q.getResultList();
                    for(String p: list){
                        System.out.println(p);
                    }
                    break;
            }
        }while(op == 5);
    }

}
