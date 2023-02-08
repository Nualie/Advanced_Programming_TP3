package model;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Collection;

@Stateless
public class EmployeeSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    EntityManager entityManager = entityManagerFactory.createEntityManager();


    public Collection<EmployeeEntity> getAllEmployees(){
        Query query = entityManager.createQuery("select e from EmployeeEntity e");
        return query.getResultList();
    }
}
