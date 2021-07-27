/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentgroupmaker;

import java.util.ArrayList;

public class Student {

    private String fName;
    private String lName;
    private ArrayList<String> roles = new ArrayList();
    private Boolean inGroup = false;

    public Student(String fName, String lName, ArrayList<String> roles) {
        this.fName = fName;
        this.lName = lName;
        for (int i = 0; i < roles.size(); i++) {
            this.roles.add(roles.get(i));
        }
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String name) {
        this.fName = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRoles(int i) {
        return roles.get(i);
    }

    public int getRolesLength() {
        return roles.size();
    }

    public Boolean getInGroup() {
        return inGroup;
    }

    public void setInGroup(Boolean inGroup) {
        this.inGroup = inGroup;
    }

}
