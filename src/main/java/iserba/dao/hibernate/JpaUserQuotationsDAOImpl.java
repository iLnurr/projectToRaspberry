package iserba.dao.hibernate;

import iserba.Profiles;
import iserba.dao.UserQuotationsDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.model.UserQuotations_;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by ilnur on 01.03.17.
 */
@Repository
@Transactional(readOnly = true)
@Profile(Profiles.HIBERNATE)
public class JpaUserQuotationsDAOImpl implements UserQuotationsDAO{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserQuotations save(UserQuotations userQuotations, int userId) {
        if (!userQuotations.isNew() && get(userQuotations.getId(), userId) == null) {
            return null;
        }
        userQuotations.setUser(em.getReference(User.class, userId));
        if (userQuotations.isNew()) {
            em.persist(userQuotations);
            return userQuotations;
        } else {
            return em.merge(userQuotations);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaDelete<UserQuotations> criteriaDelete = criteriaBuilder.createCriteriaDelete(UserQuotations.class);
        Root<UserQuotations> root = criteriaDelete.from(UserQuotations.class);

        Predicate predicate1 = criteriaBuilder.equal(root.get(UserQuotations_.id),id);
        Predicate predicate2 = criteriaBuilder.equal(root.get(UserQuotations_.user),userId);

        criteriaDelete.where(predicate1, predicate2);
        int deletedEntityes = em.createQuery(criteriaDelete).executeUpdate();
        return deletedEntityes > 0;
    }

    @Override
    @Transactional
    public UserQuotations get(int id, int userId) {
        return em.find(UserQuotations.class, id);
    }

    @Override
    @Transactional
    public List<UserQuotations> getAll() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<UserQuotations> criteriaQuery = criteriaBuilder.createQuery(UserQuotations.class);

        Root<UserQuotations> root = criteriaQuery.from(UserQuotations.class);
        criteriaQuery.select(root);
        TypedQuery<UserQuotations> qry = em.createQuery(criteriaQuery);
        List<UserQuotations> list = qry.getResultList();
        return list;
    }

    @Override
    @Transactional
    public List<UserQuotations> getAll(int userId) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<UserQuotations> criteriaQuery = criteriaBuilder.createQuery(UserQuotations.class);
        Root<UserQuotations> root = criteriaQuery.from(UserQuotations.class);
        criteriaQuery.select(root);

        Predicate predicate = criteriaBuilder.equal(root.get(UserQuotations_.user),userId);
        criteriaQuery.where(predicate);

        TypedQuery<UserQuotations> qry = em.createQuery(criteriaQuery);
        List<UserQuotations> list = qry.getResultList();
        return list;
    }

    @Override
    @Transactional
    public List<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<UserQuotations> criteriaQuery = criteriaBuilder.createQuery(UserQuotations.class);
        Root<UserQuotations> root = criteriaQuery.from(UserQuotations.class);
        criteriaQuery.select(root);

        Predicate predicate1 = criteriaBuilder.equal(root.get(UserQuotations_.user),userId);
        Predicate predicate2 = criteriaBuilder.between(root.get(UserQuotations_.dateTime), startDate, endDate);
        criteriaQuery.where(predicate1, predicate2);

        TypedQuery<UserQuotations> qry = em.createQuery(criteriaQuery);
        List<UserQuotations> list = qry.getResultList();

        return list;
    }

    @Override
    @Transactional
    public int getUserId(int userQuotationsId) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<UserQuotations> criteriaQuery = criteriaBuilder.createQuery(UserQuotations.class);
        Root<UserQuotations> root = criteriaQuery.from(UserQuotations.class);
        criteriaQuery.select(root);

        Predicate predicate1 = criteriaBuilder.equal(root.get(UserQuotations_.id),userQuotationsId);
        criteriaQuery.where(predicate1);

        TypedQuery<UserQuotations> query = em.createQuery(criteriaQuery);
        return query.getSingleResult().getUser().getId();
    }
}
