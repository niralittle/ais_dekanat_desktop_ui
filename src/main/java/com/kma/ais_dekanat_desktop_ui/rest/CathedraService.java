package com.kma.ais_dekanat_desktop_ui.rest;

import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import com.kma.ais_dekanat_desktop_ui.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CathedraService {
    public static List<Cathedra> getCathedraByDepartmentId(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cathedra[]> response = restTemplate.getForEntity(
                Constants.REST_API_PATH + Constants.CATHEDRA_BY_DEPARTMENT_ID + "?id=" + id, Cathedra[].class);
        List<Cathedra> cathedraList = Arrays.asList(response.getBody());
        return cathedraList;
    }
    public static List<Department> getAllDepartment(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Department[]> response = restTemplate.getForEntity(
                Constants.REST_API_PATH + Constants.DEPARTMENT_LIST, Department[].class);
        List<Department> cathedraList = Arrays.asList(response.getBody());
        return cathedraList;
    }
    public static void removeDepartment(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id.toString());
        restTemplate.delete(Constants.REST_API_PATH+Constants.DELETE_DEPARTMENT+"/{id}", map);
    }
    public static Professor getProfessorById(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Professor> response = restTemplate.getForEntity(
                Constants.REST_API_PATH + Constants.PROFESSOR_BY_ID + "?id=" + id, Professor.class);
        Professor professor =response.getBody();
        return professor;
    }
}
