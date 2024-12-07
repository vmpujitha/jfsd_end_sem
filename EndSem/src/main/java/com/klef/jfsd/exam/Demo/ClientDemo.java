package com.klef.jfsd.exam.Demo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {
        ClientDemo demo = new ClientDemo();
//        demo.insertClient(); 
       demo.fetchAllClients(); 
    }

    // Method to insert a record
    public void insertClient() {
        Configuration cfg = new Configuration();
        cfg.configure("endexam.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Scanner scanner = new Scanner(System.in);

        Client client = new Client();
        System.out.println("Enter Client Name:");
        client.setName(scanner.nextLine());
        System.out.println("Enter Gender:");
        client.setGender(scanner.nextLine());
        System.out.println("Enter Age:");
        client.setAge(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Location:");
        client.setLocation(scanner.nextLine());
        System.out.println("Enter Email:");
        client.setEmail(scanner.nextLine());
        System.out.println("Enter Mobile Number:");
        client.setMobileNumber(scanner.nextLine());

        session.save(client);
        transaction.commit();

        System.out.println("Client record inserted successfully!");

        session.close();
        sessionFactory.close();
    }

    // Method to fetch all records
    public void fetchAllClients() {
        Configuration cfg = new Configuration();
        cfg.configure("endexam.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query<Client> query = session.createQuery("from Client", Client.class);
        List<Client> clientList = query.getResultList();

        System.out.println("***** All Client Records *****");
        for (Client client : clientList) {
            System.out.println(client.toString());
        }

        session.close();
        sessionFactory.close();
    }
}
