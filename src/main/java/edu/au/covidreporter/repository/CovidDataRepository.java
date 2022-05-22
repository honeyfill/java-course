package edu.au.covidreporter.repository;

import edu.au.covidreporter.model.CovidDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data Repository for CovidDataEntity
 */
@Repository
public interface CovidDataRepository extends JpaRepository<CovidDataEntity, Integer> {

    @Query( "select distinct table.countryRegion " +
            "from edu.au.covidreporter.model.CovidDataEntity as table " +
            "order by table.countryRegion asc")
    List<String> getAllCountries();

    @Query( "select covid " +
            "from edu.au.covidreporter.model.CovidDataEntity as covid " +
            "where covid.countryRegion = :country and covid.date between :from and :to " +
            "order by covid.date")
    List<CovidDataEntity> getFromTo(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("country") String country);

}
