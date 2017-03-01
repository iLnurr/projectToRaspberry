package iserba.dao.hibernate;

import iserba.Profiles;
import iserba.dao.UserDAO;
import iserba.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return em.createNamedQuery(User.BY_EMAIL, User.class).setParameter(1, email).getSingleResult();
    }

    @Override
    @Transactional
    public User getByName(String name) {
        return em.createNamedQuery(User.BY_NAME, User.class).setParameter(1, name).getSingleResult();
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return em.createQuery("from " + User.class.getName()).getResultList();
    }
}
