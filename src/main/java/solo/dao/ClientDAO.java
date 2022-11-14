package solo.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import solo.models.Client;

import java.util.List;


@Component
public class ClientDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Client> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select c from Client c", Client.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Client show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);

    }

    @Transactional
    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.save(client);
    }
    @Transactional
    public void update(int id, Client updatedClient) {
        Session session = sessionFactory.getCurrentSession();
        Client clientToBeUpdated = session.get(Client.class, id);

        clientToBeUpdated.setName(updatedClient.getName());
        clientToBeUpdated.setSurname(updatedClient.getSurname());
        clientToBeUpdated.setAge(updatedClient.getAge());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Client.class, id));
    }
}