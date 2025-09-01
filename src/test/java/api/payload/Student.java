package api.payload;

public class Student {

    String name;
    String location;
    String phone;
    String courses[]; //changed from List of string to array of string

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
