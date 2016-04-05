package com.kma.ais_dekanat_desktop_ui.model;

/**
 * Created by nira on 05.04.16.
 */
public class FinalReport {
    private FinalTest finalTest;
    private Student student;
    private Integer grade;

    public FinalTest getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(FinalTest finalTest) {
        this.finalTest = finalTest;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
