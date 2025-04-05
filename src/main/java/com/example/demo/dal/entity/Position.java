package com.example.demo.dal.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    private String title;
    private int hierarchyLevel;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position(Long positionId, String title, int hierarchyLevel) {
        this.positionId = positionId;
        this.title = title;
        this.hierarchyLevel = hierarchyLevel;
    }

    public Position() {}

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(int hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
