package com.gaurav;

import com.gaurav.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
public class HibernateWithSpringbootApplication implements CommandLineRunner {

    public HibernateWithSpringbootApplication(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateWithSpringbootApplication.class, args);
    }


    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Session session = entityManager.unwrap(Session.class);

        session.save(new Customer("Zack", "Anderson"));
        session.save(new Customer("Cody", "Anderson"));
        session.save(new Customer("Zoella", "Sugg"));
        session.save(new Customer("Jeremy", "Corbyn"));
    }
}
