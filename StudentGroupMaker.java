package studentgroupmaker;

import gui.LoginScreen;
import gui.StudentPickerGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Tom
 */
public class StudentGroupMaker {

    static private ArrayList<Student> students = new ArrayList();
    static private ArrayList<Groups> groups = new ArrayList();

    public static void createStudent(String fName, String lName, ArrayList<String> roles) {
        students.add(new Student(fName, lName, roles));
    }

    public static Student getStudent(int i) {
        return students.get(i);
    }

    public static int getStudentSize() {
        return students.size();
    }

    public static void createGroup(ArrayList<Student> students, int groupNumber) {
        groups.add(new Groups(students, groupNumber));
    }

    public static Groups getGroup(int i) {
        return groups.get(i);
    }

    public static int getGroupsSize() {
        return groups.size();
    }

    public static void clearGroups() {
        groups.clear();
        StudentPickerGUI.groupsListManager.clear();
    }

    private static void loadData() throws FileNotFoundException {
        File students = new File("Groups.txt");
        Scanner studentScan = new Scanner(students);
        String fName = "defaultValue";
        String lName = "defaultValue";
        ArrayList<String> skills = new ArrayList();
        ArrayList<Student> group = new ArrayList();
        String currentLine;
        int skillsNumber = 0;
        int groupNumber = 0;

        currentLine = studentScan.nextLine();
        while (studentScan.hasNext()) {
            if (currentLine.charAt(0) == 'A') {
                if (skillsNumber != 0) {
                    createStudent(fName, lName, skills);
                    group.add(getStudent(getStudentSize() - 1));
                    skillsNumber = 0;
                    skills.clear();
                    fName = "defaultValue";
                }
                fName = currentLine;
            }
            if (currentLine.equals("Group Number")) {
                if (groupNumber != 0) {
                    if (skillsNumber != 0) {
                        createStudent(fName, lName, skills);
                        group.add(getStudent(getStudentSize() - 1));
                        skillsNumber = 0;
                        skills.clear();
                        fName = "defaultValue";
                    }
                    createGroup(group, groupNumber);
                    group.clear();
                }
                currentLine = studentScan.nextLine();
                groupNumber = Integer.parseInt(currentLine);
            } else if (currentLine.equals("Team Leader") || currentLine.equals("Requirments") || currentLine.equals("UI Design") || currentLine.equals("System Design") || currentLine.equals("Development") || currentLine.equals("Testing")) {
                skills.add(currentLine);
                skillsNumber++;
            }
            currentLine = studentScan.nextLine();
        }
        if (currentLine.equals("Team Leader") || currentLine.equals("Requirments") || currentLine.equals("UI Design") || currentLine.equals("System Design") || currentLine.equals("Development") || currentLine.equals("Testing")) {
            skills.add(currentLine);
            skillsNumber++;
        }
        createStudent(fName, lName, skills);
        group.add(getStudent(getStudentSize() - 1));
        if (groupNumber != 0) {
            createGroup(group, groupNumber);
            group.clear();
        }

        for (int j = 0; j < getStudentSize(); j++) {
            gui.StudentPickerGUI.studentsListManager.addElement(getStudent(j).getfName());
        }
        if (getGroupsSize() != 0) {
            for (int l = 0; l < getGroupsSize(); l++) {
                for (int j = 0; j < getGroup(l).size(); j++) {
                    StudentPickerGUI.groupsListManager.addElement("Group " + getGroup(l).getGroupNumber() + ": " + getGroup(l).getStudents(j).getfName());
                }
            }
        }
        studentScan.close();

    }

    public static void saveData() throws FileNotFoundException, IOException {
        FileWriter groupsWriter = new FileWriter("Groups.txt");
        String saveData = "";

        if (getGroupsSize() != 0) {
            for (int j = 0; j < getGroupsSize(); j++) {
                saveData = saveData.concat("Group Number" + "\n");
                saveData = saveData.concat(getGroup(j).getGroupNumber() + "\n");
                for (int k = 0; k < getGroup(j).size(); k++) {
                    saveData = saveData.concat(getGroup(j).getStudents(k).getfName() + "\n");
                    for (int l = 0; l < getGroup(j).getStudents(k).getRolesLength(); l++) {
                        saveData = saveData.concat(getGroup(j).getStudents(k).getRoles(l) + "\n");
                    }
                }
            }
        }
        groupsWriter.write(saveData);
        groupsWriter.close();

    }

    public static void makeGroups() {
        boolean hasLeader = false;
        boolean hasRequirments = false;
        boolean hasUIDesign = false;
        boolean hasSystemDesign = false;
        boolean hasDevelopment = false;
        boolean hasTesting = false;
        ArrayList<Student> groupList = new ArrayList();
        int size = getStudentSize();
        int groupNumber = 1;

        while (size >= 6) {
            for (int i = 0; i < getStudentSize(); i++) {
                if (!getStudent(i).getInGroup()) {
                    if (!hasLeader && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("Team Leader")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasLeader = true;
                                break;
                            }
                        }
                    }
                    if (!hasRequirments && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("Requirments")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasRequirments = true;
                                break;
                            }
                        }
                    }
                    if (!hasUIDesign && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("UI Design")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasUIDesign = true;
                                break;
                            }
                        }
                    }
                    if (!hasSystemDesign && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("System Design")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasSystemDesign = true;
                                break;
                            }
                        }
                    }
                    if (!hasDevelopment && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("Development")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasDevelopment = true;
                                break;
                            }
                        }
                    }
                    if (!hasTesting && !getStudent(i).getInGroup()) {
                        for (int j = 0; j < getStudent(i).getRolesLength(); j++) {
                            if (getStudent(i).getRoles(j).equals("Testing")) {
                                groupList.add(getStudent(i));
                                getStudent(i).setInGroup(true);
                                hasTesting = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (hasTesting) {
                createGroup(groupList, groupNumber);
                groupList.clear();
                hasLeader = false;
                hasRequirments = false;
                hasUIDesign = false;
                hasSystemDesign = false;
                hasDevelopment = false;
                hasTesting = false;
            }
            size = size - 6;
            groupNumber++;
        }
        if (size < 6) {
            groupList.clear();
            for (int i = 0; i < getStudentSize(); i++) {
                if (!getStudent(i).getInGroup()) {
                    groupList.add(getStudent(i));
                    getStudent(i).setInGroup(true);
                }
            }
            createGroup(groupList, groupNumber);
        }
        for (int i = 0; i < getGroupsSize(); i++) {
            for (int j = 0; j < getGroup(i).size(); j++) {
                StudentPickerGUI.groupsListManager.addElement("Group " + getGroup(i).getGroupNumber() + ": " + getGroup(i).getStudents(j).getfName());
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
        try {
            loadData();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentGroupMaker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
