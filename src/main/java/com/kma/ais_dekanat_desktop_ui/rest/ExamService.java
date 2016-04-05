package com.kma.ais_dekanat_desktop_ui.rest;

import com.kma.ais_dekanat_desktop_ui.model.FinalTest;
import com.kma.ais_dekanat_desktop_ui.utils.Constants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by nira on 05.04.16.
 */
public class ExamService {

    public static List<FinalTest> getAll() {
        return new RestTemplate().exchange(
                Constants.REST_API_PATH + Constants.GET_ALL_EXAMS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FinalTest>>() {}).getBody();
    }
}
