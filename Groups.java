/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentgroupmaker;

import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Groups {

    ArrayList<Student> students = new ArrayList();
    int groupNumber = 1;

    public Groups(ArrayList<Student> students, int groupNumber) {
        this.groupNumber = groupNumber;
        for (int i = 0; i < students.size(); i++) {
            this.students.add(students.get(i));
        }
    }

    public Student getStudents(int i) {
        return students.get(i);
    }

    public int size() {
        return students.size();
    }

    public int getGroupNumber() {
        return groupNumber;
    }

}
