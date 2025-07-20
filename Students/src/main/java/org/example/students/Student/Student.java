package org.example.students.Student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Student {

    //ID, name, age, degree, GPA.
    private int ID;
    private String name;
    private String age;
    private String degree;
    private double GPA;
}
