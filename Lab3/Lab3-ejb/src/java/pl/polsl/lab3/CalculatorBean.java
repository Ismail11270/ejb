package pl.polsl.lab3;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.lab3.model.Student;

@Stateless
@LocalBean
public class CalculatorBean {

    // Injects the entity manager so thet it does not have to be created through the EntityManagerFactory
    @PersistenceContext
    private EntityManager em;
    
    public int add(int arg1, int arg2) {
        return arg1 + arg2;
    }

    public void createStudent(String name) {
        Student student = new Student();
        student.setName(name);
        // No transaction is needed
        em.persist(student);
    }

    public List<Student> findAllStudents() {
        return em.createQuery("SELECT s FROM Student s",
                Student.class).getResultList();
    }
}
