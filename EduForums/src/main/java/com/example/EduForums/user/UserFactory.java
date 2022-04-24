package com.example.EduForums.user;

import com.example.EduForums.student.Student;
import com.example.EduForums.teacher.Teacher;

public class UserFactory {
    // private String StudentOrTeacher;

    public User getUser(String userType, String name, Dept dept, String email,String password,String rn, String sem)
    {
        if(userType=="student")
            return new Student(name, dept, email, password, rn, sem);
        else if(userType=="teacher")
            return new Teacher(name, dept, email, password, rn, sem);
        else
            return new User();
    }
}
