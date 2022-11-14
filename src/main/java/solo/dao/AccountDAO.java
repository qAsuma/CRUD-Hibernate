package solo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import solo.models.Account;

import java.util.List;


@Component
public class AccountDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Account> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select a from Account a", Account.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Account show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);

    }

    @Transactional
    public void save(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.save(account);
    }
    @Transactional
    public void update(int id, Account updatedAccount) {
        Session session = sessionFactory.getCurrentSession();
        Account personToBeUpdated = session.get(Account.class, id);

        personToBeUpdated.setEmail(updatedAccount.getEmail());
        personToBeUpdated.setPassword(updatedAccount.getPassword());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Account.class, id));
    }


}
