package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
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

            //delete the student
            //System.out.println("Deleting student:"+myStudent);//commented bcoz it is already deleted from table
            //session.delete(myStudent);

            //delete bunch of rows
            session.createQuery("delete from Student where id=7").executeUpdate();


            session.getTransaction().commit();

            //commit transaction
            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
}
