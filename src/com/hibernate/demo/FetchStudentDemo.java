package com.hibernate.demo;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{
            //use the session object to save java objects
            System.out.println("Creating new student object....");
            Student tempStudent=new Student("daffy","duck","daffyduck@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //find out student primary key
            System.out.println("saved student. generated id:"+ tempStudent.getId());

            //now get a new session
            session=factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student based on primary key
            System.out.println("getting student with id"+ tempStudent.getId());
            Student myStudent=session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete"+myStudent);

            session.getTransaction().commit();

            //commit transaction
            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
}
