package iserba.dao.hibernate;

import iserba.Profiles;
import iserba.dao.UserQuotationsDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        return em.createNamedQuery(UserQuotations.DELETE).
                setParameter("id", id).
                setParameter("userId", userId).
                executeUpdate() != 0;
    }

    @Override
    public UserQuotations get(int id, int userId) {
        List<UserQuotations> userQuotations = em.createNamedQuery(UserQuotations.GET, UserQuotations.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(userQuotations);
    }

    @Override
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
    public List<UserQuotations> getAll(int userId) {
        return em.createNamedQuery(UserQuotations.ALL_SORTED, UserQuotations.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserQuotations.GET_BETWEEN, UserQuotations.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }

    @Override
    public int getUserId(int userQuotationsId) {
        return (int) em.createNativeQuery("SELECT user_id FROM quotations q WHERE q.id=:id")
                .setParameter("id",userQuotationsId)
                .getSingleResult();
    }
}
