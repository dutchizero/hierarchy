package com.example.demo.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;

@NodeEntity(label = "Period")
@Data
public class Period {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal availableFunds;

    private String effectiveDate;

    private String endDate;

    private String name;

    private String periodType;

    private Integer planingId;

    @Relationship(type = "PLANNED", direction = Relationship.INCOMING)
    private Structure structure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(BigDecimal availableFunds) {
        this.availableFunds = availableFunds;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Integer getPlaningId() {
        return planingId;
    }

    public void setPlaningId(Integer planingId) {
        this.planingId = planingId;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}