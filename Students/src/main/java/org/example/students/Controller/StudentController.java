package org.example.students.Controller;

import org.example.students.Api.ApiResponse;
import org.example.students.Student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private ArrayList<Student> students =  new ArrayList<>();


    @GetMapping("/show")
    public ArrayList<Student>showStudents(){
        return students;
    }

    @PostMapping("/create")
    public ApiResponse createStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("student successfully created");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@RequestBody Student student, @PathVariable int index){
        students.set(index, student);
        return new ApiResponse("student successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index){
        students.remove(index);
        return new ApiResponse("student successfully deleted");
    }

    @GetMapping("/honors")
    public HashMap<Student, String> getStudentBasedOnGPA(){
        HashMap<Student, String> categories = new HashMap<>();

        for (Student student : students){
            if(student.getGPA() >= 4.5){
                categories.put(student, "first class honor");
            }else if(student.getGPA() > 3.5){
                categories.put(student, "second class honor");
            }
        }
        return categories;
    }


    @GetMapping("/gpa/up-avg")
    public ArrayList<Student> getStudentsGpaMoreThanAverage(){
        double sum = 0.0;
        ArrayList<Student> studentsAvgGpaMore = new ArrayList<>();
        for(Student student : students){
            sum += student.getGPA();
        }

        double average = sum/students.size();

        for(Student student : students){
            if(student.getGPA() > average){
                studentsAvgGpaMore.add(student);
            }
        }

        return studentsAvgGpaMore;
    }
}
