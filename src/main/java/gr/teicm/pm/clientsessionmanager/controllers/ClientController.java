/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.pm.clientsessionmanager.controllers;

import gr.teicm.pm.clientsessionmanager.HibernateUtil;
import gr.teicm.pm.clientsessionmanager.model.Client;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author user
 */
public class ClientController {

    List<Client> clients;
    SessionFactory sessionFactory;
    private Session session;

    public ClientController() {
        clients = new ArrayList<>();
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();

        // sessionFactory.close();
    }

    public void getClientDetailsFromForm(String clientName, String caseDetails, String chargingRate, long elapsedTime) {
        try {
            clients.add(new Client(clientName, caseDetails, Integer.valueOf(chargingRate), elapsedTime));
        } catch (NullPointerException e) {

        }
    }

    public DefaultTableModel getClientsModel() {
        DefaultTableModel model = (DefaultTableModel) new javax.swing.table.DefaultTableModel(new Object[][]{},
                new String[]{
                    "Client Name", "Charging rate", "Elapsed time", "Price"
                }
        );
        session.beginTransaction();
        for (Client c : clients) {
            session.save(c);
            session.getTransaction().commit();
        }
        clients = session.createCriteria(Client.class).list();
        for (Client c : clients) {
            model.addRow(new Object[]{c.getName(), Long.toString(c.getChargingRate()), Long.toString(c.getElapsedTime()), Long.toString(c.getElapsedTime() * c.getChargingRate())});
        }
        return (model);
    }

    public List<Client> getClients() {
        return (clients);
    }

}
