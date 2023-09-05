package com.example.school.api.Service;

import com.example.school.api.Model.Student;
import com.example.school.api.DTO.StudentDTO;
import com.example.school.api.Repository.StudentRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        if (student != null && checkEmailFormat(student.getEmail()) != false){
            return studentRepository.save(student);
        }
        else{
            return null;
        }
    }


    public boolean searchStudentById(StudentDTO studentDTO) {
        val id = studentDTO.getId();
        Optional<Student> student = studentRepository.findById(id);
        if (student != null){
            return true;
        }
        return false;
    }

    public Object deleteStudent(StudentDTO studentDTO) {
        boolean studentExist = searchStudentById(studentDTO);
        if (studentExist != false){
            studentRepository.deleteById(studentDTO.getId());
        }
        return null;
    }

    public boolean checkEmailFormat(String email){
        String EMAIL_REGEX ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Student findStudentByEmail(StudentDTO studentDTO){
        Student student = studentRepository.findByEmail(studentDTO.getEmail());
        if (student != null){
            return student;
        }
        return null;
    }
}
