package com.example.demo.repositories;

import com.example.demo.domain.Structure;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface StructureRepository extends Neo4jRepository<Structure, Long> {

    @Query("MATCH (s1:Structure)-[r:PARENT]->(s2:Structure)-[p:PLANNED]->(pe:Period) RETURN s1, r, s2, pe")
    Collection<Structure> query();

    @Query("MATCH (s2:Structure)-[r:PLANNED]->(pe:Period) WHERE s2.code in $code RETURN s2, r, pe")
    List<Structure> queryWithCode(@Param("code") List<String> code);
}