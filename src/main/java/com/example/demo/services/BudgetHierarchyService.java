package com.example.demo.services;

import com.example.demo.domain.Period;
import com.example.demo.domain.Structure;
import com.example.demo.repositories.StructureRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BudgetHierarchyService {

    private StructureRepository structureRepository;

    public BudgetHierarchyService(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    public void initialBudgetHierarchy() {

        Structure pantavanij = new Structure();
        pantavanij.setCode("PTVN");
        pantavanij.setName("Pantavanij");
        pantavanij.setType("Level 1");
        pantavanij.setTypeName("Business");

        Period periodPantavanij = new Period();
        periodPantavanij.setAvailableFunds(new BigDecimal(1000000));
        periodPantavanij.setEffectiveDate("2020-06-01");
        periodPantavanij.setEndDate("2020-07-31");
        periodPantavanij.setName("TEST001_Q01_2020_06_01");
        periodPantavanij.setStructure(pantavanij);

        List<Period> periods = new ArrayList<>();
        periods.add(periodPantavanij);
        pantavanij.setPeriods(periods);

        Structure development = new Structure();
        development.setCode("01");
        development.setName("Product Development");
        development.setType("Level 2");
        development.setTypeName("Team");

        Period periodDevelopment = new Period();
        periodDevelopment.setAvailableFunds(new BigDecimal(1000000));
        periodDevelopment.setEffectiveDate("2020-06-01");
        periodDevelopment.setEndDate("2020-07-31");
        periodDevelopment.setName("TEST001_Q01_2020_06_01");
        periodDevelopment.setStructure(development);

        List<Period> periodDevelopements = new ArrayList<>();
        periodDevelopements.add(periodDevelopment);
        development.setPeriods(periodDevelopements);

        List<Structure> structuresUnderPtvn = new ArrayList<>();
        structuresUnderPtvn.add(development);
        pantavanij.setStructures(structuresUnderPtvn);

        Structure capex = new Structure();
        capex.setCode("10");
        capex.setName("CAPEX");
        capex.setType("Level 3");
        capex.setTypeName("Category");

        Structure opex = new Structure();
        opex.setCode("11");
        opex.setName("OPEX");
        opex.setType("Level 3");
        opex.setTypeName("Category");

        Period periodCapex = new Period();
        periodCapex.setAvailableFunds(new BigDecimal(700000));
        periodCapex.setEffectiveDate("2020-06-01");
        periodCapex.setEndDate("2020-07-31");
        periodCapex.setName("TEST001_Q01_2020_06_01");
        periodCapex.setStructure(capex);

        List<Period> periodCapexs = new ArrayList<>();
        periodCapexs.add(periodCapex);
        capex.setPeriods(periodCapexs);

        Period periodOpex = new Period();
        periodOpex.setAvailableFunds(new BigDecimal(300000));
        periodOpex.setEffectiveDate("2020-06-01");
        periodOpex.setEndDate("2020-07-31");
        periodOpex.setName("TEST001_Q01_2020_06_01");
        periodOpex.setStructure(opex);

        List<Period> periodOpexs = new ArrayList<>();
        periodOpexs.add(periodOpex);
        opex.setPeriods(periodOpexs);

        List<Structure> structuresUnderDev = new ArrayList<>();
        structuresUnderDev.add(capex);
        structuresUnderDev.add(opex);
        development.setStructures(structuresUnderDev);

        structureRepository.save(pantavanij);
    }

    public void updateFunds(Long planingId, String code) {
        List<String> codes = Arrays.asList(code.split("\\."));
        List<Structure> structures = structureRepository.queryWithCode(codes);
        structures.forEach(structure -> {
            BigDecimal avaliableFunds = structure.getPeriods().get(0).getAvailableFunds();
            avaliableFunds = avaliableFunds.subtract(new BigDecimal(500));
            structure.getPeriods().get(0).setAvailableFunds(avaliableFunds);
        });

        structureRepository.saveAll(structures);
    }


    public Iterable<Structure> initialBudgetAmountGet() {
        return structureRepository.findAll();
    }
}
