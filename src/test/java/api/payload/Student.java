package api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    String name;
    String location;
    String phone;
    String courses[]; //changed from List of string to array of string
    
 // Constructor (optional)
    public Student(String name, String location, String phone, String[] courses) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.courses=courses;
    }
    
    
 // Default constructor (needed for deserialization)
    public Student() {}

    // Getters and Setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String [] getCourses() {
        return courses;
    }
    public void setCourses(String [] courses ) {
        this.courses = courses;
    }
}
