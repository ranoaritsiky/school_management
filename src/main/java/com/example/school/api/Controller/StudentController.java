package com.example.school.api.Controller;

import com.example.school.api.Model.Student;
import com.example.school.api.DTO.StudentDTO;
import com.example.school.api.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/find_student_by_email")
    public ResponseEntity<Student> findStudentByEmail(@RequestBody StudentDTO studentDTO){
        Student student = studentService.findStudentByEmail(studentDTO);
        if(student != null){
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("create_student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student _student = studentService.createStudent(student);
        if (_student != null){
            return ResponseEntity.ok(_student);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete_student")
    public ResponseEntity<Student> deleteStudent(@RequestBody StudentDTO studentDTO){
        Student deleteStudent = (Student) studentService.deleteStudent(studentDTO);
        if (deleteStudent != null){

            return ResponseEntity.ok(deleteStudent);
        }
        return  ResponseEntity.badRequest().build();
    }
}
