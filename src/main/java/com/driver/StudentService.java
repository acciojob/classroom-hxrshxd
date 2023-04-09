package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student) { // add student in db
        studentRepository.addStudent(student);
    }

    public void addTeacher(Teacher teacher) { // add teacher in db
        studentRepository.addTeacher(teacher);
    }

    public void addStudentTeacherPair(String teacherName, String studentName) {
        studentRepository.addStudentTeacherPair(teacherName, studentName); // add student teacher pair in db
    }

    public Student getStudentByName(String studentName) {
        return studentRepository.getStudentByName(studentName); // get student by name
    }

    public Teacher getTeacherByName(String teacherName) {
        return studentRepository.getTeacherByName(teacherName); // get teacher by name
    }

    public List<String> getStudentsByTeacherName(String teacherName) { // get list of students under teacher
        return studentRepository.getStudentsByTeacherName(teacherName);
    }

    public List<String> getAllStudentsNameList() { // get all student's name list
        return studentRepository.getAllStudentsName();
    }

    public void deleteTeacherByName(String teacherName) {
        List<String> studentList = studentRepository.getStudentsByTeacherName(teacherName);

        studentRepository.deleteTeacher(teacherName);

        for (String s: studentList) {
            studentRepository.deleteStudent(s);
        }
    }

    public void deleteAllTeachers() {
        List<String> teacherList = studentRepository.getAllTeachersName();

        for (String teacher: teacherList) {
            deleteTeacherByName(teacher);
        }
    }
}
