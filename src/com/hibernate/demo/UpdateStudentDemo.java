package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

           int studentId=6;

            //now get a new session
            session=factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student based on primary key
            System.out.println("getting student with id"+ studentId);
            Student myStudent=session.get(Student.class, studentId);
            System.out.println("Updating Student");
            myStudent.setFirstName("Scooby");

            session.getTransaction().commit();

            //commit transaction
            System.out.println("completed!!");

            session=factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("update email of all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();
        }finally{
            factory.close();
        }
    }
}
