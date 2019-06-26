package data.repository;

import data.interfaces.IRepository;
import data.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AbstractRepository<E> implements IRepository<E> {

    protected String persistenceUnitName;
    protected JPAUtil jpaUtil;
    private Class<E> eClass;

    AbstractRepository(Class<E> eClass) {
        jpaUtil = new JPAUtil();
        this.eClass = eClass;
        this.persistenceUnitName = "hibernate-unit";
    }

    AbstractRepository(String persistenceUnitName, Class<E> eClass) {
        jpaUtil = new JPAUtil();
        this.eClass = eClass;
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public E get(long id) {

        EntityManager em = null;
        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            E result = em.find(eClass, id);
            E res = (E) em.createQuery("SELECT e FROM " + eClass.getName() + " e WHERE e.id = " + id ).getResultList().get(0);
            em.close();
            return result;
        } catch (Exception e){
            System.out.println("Exception while trying to get entities -> " + e.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
        }
        return null;
    }

    @Override
    public List<E> getAll() {

        EntityManager em = null;

        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            String name = eClass.getName();
            List<E> result = em.createQuery("SELECT dbo FROM " + name + " dbo").getResultList();
            em.close();
            return result;
        } catch (Exception e){
            System.out.println("Exception while trying to get entities -> " + e.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
        }
        return null;
    }

    @Override
    public boolean add(E entity) {

        EntityManager em = null;
        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
            return true;

        }catch (Exception e) {
            System.out.println("Exception while trying to add entity -> " + e.getMessage());
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
        }

        return false;
    }

    @Override
    public boolean delete(E entity) {

        EntityManager em = null;
        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;

        }catch (Exception e) {
            System.out.println("Exception while trying to delete entity -> " + e.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
        }

        return false;
    }

    @Override
    public boolean update(E entity) {

        if (entity == null ) return false;

        EntityManager em = null;

        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            em.close();
            return true;

        }catch (Exception e) {
            System.out.println("Exception while trying to update entity -> " + e.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
                em.close();
            }
        }

        return false;
    }

}
