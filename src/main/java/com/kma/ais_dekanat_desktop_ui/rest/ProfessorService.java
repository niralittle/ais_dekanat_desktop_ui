package com.kma.ais_dekanat_desktop_ui.rest;

import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import com.kma.ais_dekanat_desktop_ui.utils.Constants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by nira on 05.04.16.
 */
public class ProfessorService {

    public static List<Professor> getAll() {
        return new RestTemplate().exchange(
                Constants.REST_API_PATH + Constants.GET_ALL_PROFESSORS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Professor>>() {}).getBody();
    }

}
