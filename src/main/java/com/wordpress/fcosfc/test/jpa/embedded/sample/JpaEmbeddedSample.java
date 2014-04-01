package com.wordpress.fcosfc.test.jpa.embedded.sample;

import com.wordpress.fcosfc.test.jpa.embedded.sample.entity.Country;
import com.wordpress.fcosfc.test.jpa.embedded.sample.entity.Location;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class JpaEmbeddedSample {

    private static final Logger logger = Logger.getLogger(JpaEmbeddedSample.class.getName());

    public static void main(String[] args) {
        JpaEmbeddedSample jpaEmbeddedSample = new JpaEmbeddedSample();

        jpaEmbeddedSample.execute();
    }

    public void execute() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction transaction = null;
        Country country = null;

        try {
            emf = Persistence.createEntityManagerFactory("TestPU");
            em = emf.createEntityManager();

            List<Country> countryList = em.createQuery("SELECT c FROM Country c ORDER BY c.name").getResultList();
            if (countryList.isEmpty()) {
                Location location;

                System.out.println("The list of countries is empty");

                transaction = em.getTransaction();
                try {
                    transaction.begin();

                    country = new Country("es", "Spain");                    
                    em.persist(country);
                    location = new Location("esalg", "Algeciras", country);
                    em.persist(location);
                    location = new Location("esceu", "Ceuta", country);
                    em.persist(location);
                                        
                    country = new Country("ma", "Morocco");
                    em.persist(country);
                    location = new Location("matng", "Tangier", country);
                    em.persist(location);

                    transaction.commit();

                    System.out.println("Countries and locations created");
                } catch (PersistenceException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    
                    transaction.rollback();
                }
            } else {
                System.out.println("Countries and locations list:");
                
                for (Country c : countryList) {
                    System.out.println(c);
                    
                    for (Location location : c.getLocationsList()) {
                        System.out.println("   " + location);
                    }
                }
            }
            
            // Bean validation example
            try {
                transaction = em.getTransaction();
                
                transaction.begin();
                
                country = new Country("u", null);
                em.persist(country);
                
                transaction.commit();
            } catch (Exception  ex) {
                if (ex.getCause() instanceof javax.validation.ConstraintViolationException) {
                    System.err.println(country + " is not valid");
                    for (ConstraintViolation constraintViolation : ((ConstraintViolationException) ex.getCause()).getConstraintViolations()) {
                        System.err.println("   " + constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());                          
                    }
                } else {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);            
        } finally {
            if (em != null) {
                try {
                    em.close();
                } catch (Exception ignore) {

                }
            }

            if (emf != null) {
                try {
                    em.close();
                } catch (Exception ignore) {

                }
            }
        }
    }
}
