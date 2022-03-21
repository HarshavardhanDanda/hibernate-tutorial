package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents=session.createQuery("from Student").list();

            //display the students query
            displayStudents(theStudents);

            //query students for last name="danda"
            theStudents=session.createQuery("from Student s where s.lastName='danda'").list();
            System.out.println("stuents who have last name danda");
            displayStudents(theStudents);

            //query to last name danda or first name sai
            theStudents=session.createQuery("from Student s where"+ " s.lastName='danda' OR s.firstName='sai'").list();
            System.out.println("students who have last name danda and first name sai");
            displayStudents(theStudents);

            //query to use LIKE ,lastName is danda%
            theStudents=session.createQuery("from Student s where"+ " s.lastName LIKE 'danda%'").list();
            System.out.println("students who have lastname like danda%");
            displayStudents(theStudents);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
  //method for displaying students
    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
