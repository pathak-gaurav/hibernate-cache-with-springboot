package com.gaurav.repository;

import com.gaurav.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    private final EntityManager entityManager;
    private static final String cacheQueryPackage = "org.hibernate.cacheable";

    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Customer> all_customers = session.createNamedQuery("find_all_customers", Customer.class)
                .setHint(cacheQueryPackage, true).getResultList();
        return all_customers;
    }

    @Override
    public Customer findById(Long customerId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Customer where customerId=:customerId").setHint(cacheQueryPackage,true);
        query.setParameter("customerId", customerId);
        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        Session session = entityManager.unwrap(Session.class);
        session.save(customer);
        return customer;
    }

    @Override
    public void deleteByCustomerId(Long customerId) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(session.find(Customer.class, customerId));
    }
}
