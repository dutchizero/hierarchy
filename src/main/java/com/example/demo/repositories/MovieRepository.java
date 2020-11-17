package com.example.demo.repositories;

import com.example.demo.domain.Movie;
import com.example.demo.domain.Structure;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Michael J. Simons
 */
public interface MovieRepository extends Neo4jRepository<Movie, Long> {

	Movie findByTitle(@Param("title") String title);

	Collection<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a")
	Collection<Movie> graph(@Param("limit") int limit);

	@Query("MATCH (s1:Structure)-[r:PARENT]->(s2:Structure)-[p:PLANNED]->(pe:period) RETURN s1, r, s2, pe")
	Collection<Structure> query();
}