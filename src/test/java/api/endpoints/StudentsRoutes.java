package api.endpoints;

public class StudentsRoutes {
    public static String base_url = "http://localhost:3000";

    public static String post_url   = base_url + "/students";
    public static String get_url    = base_url + "/students/{id}";
    public static String update_url = base_url + "/students/{id}";
    public static String delete_url = base_url + "/students/{id}";
}

