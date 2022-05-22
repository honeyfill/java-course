package edu.au.covidreporter.repository;

import edu.au.covidreporter.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data Repository for ReportEntity
 */
@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

    @Query("select tab " +
            "from edu.au.covidreporter.model.ReportEntity as tab " +
            "order by tab.fileName asc")
    public List<ReportEntity> getAllReports();

}
