package models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.Utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class School {
private String name;
Map<String, Manager> departments;// = new HashMap<String, String>();

    public School(String name) {
        if(Utilities.max30Chars(name)){
        this.name = name;}
        else {
            this.name = name.substring(0,30);
        }

        this.departments = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Utilities.max30Chars(name)){
            this.name = name;}
    }

    public Map<String, Manager> getDepartments() {
        return departments;
    }

    public void setDepartments(HashMap<String, Manager> departments) {
        this.departments = departments;
    }
    public Manager getManager(String name){
            return departments.get(name);
    }
    public boolean replaceManager(String key, Manager newManager){
        if(departments.containsKey(key)){
            departments.replace(key, newManager);
            return true;
        }else{
            return false;
        }
    }
    public String listDepartments(){
        String list = "";
        for(String key: departments.keySet()){
            Manager manager = departments.get(key);
            list += key + ", Manager = " + manager.getFirstName() + " " + manager.getSecondName() + "\n";
        }
        return list;
    }
    public boolean addDept(String name, Manager manager) {
        if(!departments.containsKey(name)){
        departments.put(name, manager);
        return true;}
        else{
            return false;
        }
    }
    public Manager deleteDept(String name){
        if(departments.containsKey(name)){
        return departments.remove(name);
        }
        else{
            return null;
        }
    }
    public String displayManagerName(String name){
        if(departments.containsKey(name)){
    return departments.get(name).getFirstName() +" "+departments.get(name).getSecondName();}
        else{
            return "No Departments Called "+ name;
        }

    }
    public String displayAllEmployeesFromDept(String name){
        if(departments.containsKey(name)) {
            String allEmployees ="Department Manager = "+ displayManagerName(name) + "\n";
            for(Employee employee: departments.get(name).getDept()) {
                allEmployees += employee + "\n";
            }
            return allEmployees;
        }else {
            return "No Departments Called "+ name;
        }
    }
    public int noDepartments(){
        return departments.size();
    }
    /**
     * Loads data from an xml which is persistent data
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("departments.xml"));
        departments = (HashMap<String, Manager>) is.readObject();
        is.close();
    }

    /**
     * Saves data to an xml file which is persistent storage
     *
     * @throws Exception
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("departments.xml"));
        out.writeObject(departments);
        out.close();
    }

    @Override
    public String toString() {
        return "School:" +
                "Name = " + name + '\n' +
                "Departments = " + departments;
    }
}
