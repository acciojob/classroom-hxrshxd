package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String, Student> studentDb = new HashMap<>();
    Map<String, Teacher> teacherDb = new HashMap<>();
    Map<String, List<String>> teacherStudentDb = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student); // add student in db
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher); // add teacher in db
    }

    public void addStudentTeacherPair(String teacherName, String studentName) {
        if (studentDb.containsKey(studentName) && teacherDb.containsKey(teacherName)) { // chech if teacher and student are in their respective db

            List<String> temp = new ArrayList<>(); // create new list incase their is no existing student teacher pair in db
            if (teacherStudentDb.containsKey(teacherName)) { // their is a pair in db
                temp = teacherStudentDb.get(teacherName); // get student list of that teacher
            }
            temp.add(studentName); // add new teacher

            teacherStudentDb.put(teacherName, temp);

            // to increase student count in teacher
//            Teacher teacher = teacherDb.get(teacherName);
//            teacher.setNumberOfStudents(temp.size());
        }
    }

    public Student getStudentByName(String studentName) {
        return studentDb.get(studentName); // get student by name
    }

    public Teacher getTeacherByName(String teacherName) {
        return teacherDb.get(teacherName); // get teacher by name
    }

    public List<String> getStudentsByTeacherName(String teacherName) { // get list of students under teacher
        List<String> temp = new ArrayList<>();

        if (teacherStudentDb.containsKey(teacherName)) { // check if teacher has any student assign
            temp = teacherStudentDb.get(teacherName);
        }

        return temp;
    }

    public List<String> getAllStudentsName() { // get all student's name list
        List<String> studentList = new ArrayList<>();

        for (String s: studentDb.keySet()) {
            studentList.add(s);
        }

        return studentList;
    }

    public List<String> getAllTeachersName() {
        List<String> techerList = new ArrayList<>();

        for (String t: teacherDb.keySet()) {
            techerList.add(t);
        }

        return techerList;
    }

    public void deleteTeacher(String teacherName) {
        if (teacherDb.containsKey(teacherName)) teacherDb.remove(teacherName);
        if (teacherStudentDb.containsKey(teacherName)) teacherStudentDb.remove(teacherName);
    }

    public void deleteStudent(String studentName) {
        if (studentDb.containsKey(studentName)) studentDb.remove(studentName);
    }
}











