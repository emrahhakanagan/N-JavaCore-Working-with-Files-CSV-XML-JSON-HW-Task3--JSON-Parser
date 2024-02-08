package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "new_data.json";

        String json = readString(fileName);

        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }

    }

    public static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static List<Employee> jsonToList(String json) {
        List<Employee> employeeList = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;
                Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }


}