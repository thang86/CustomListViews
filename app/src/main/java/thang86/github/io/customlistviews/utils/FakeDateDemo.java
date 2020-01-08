package thang86.github.io.customlistviews.utils;

import java.util.ArrayList;
import java.util.List;

import thang86.github.io.customlistviews.model.Student;

/**
 * @Author ThangTX2
 */
public class FakeDateDemo {

    public static List<Student> demoList() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            students.add(new Student(1, "Student  " + i, "Class " + i + "A", "08/06/1996"));
        }
        return students;

    }
}
