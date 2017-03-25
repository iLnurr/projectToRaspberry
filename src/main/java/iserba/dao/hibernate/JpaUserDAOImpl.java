package iserba.dao.hibernate;

import iserba.Profiles;
import iserba.dao.UserDAO;
import iserba.model.User;
import iserba.model.User_;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@Profile(Profiles.HIBERNATE)
public class JpaUserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    @Transactional
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);

        criteriaDelete.where(criteriaBuilder.equal(root.get(User_.id), id));
        int deletedEntityes = em.createQuery(criteriaDelete).executeUpdate();
        return deletedEntityes > 0;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        Predicate predicate = criteriaBuilder.equal(root.get(User_.email),email);
        criteriaQuery.where(predicate);

        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public User getByName(String name) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        Predicate predicate = criteriaBuilder.equal(root.get(User_.name),name);
        criteriaQuery.where(predicate);

        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<User> getAll() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        TypedQuery<User> qry = em.createQuery(criteriaQuery);
        List<User> list = qry.getResultList();
        return list;
    }
}
