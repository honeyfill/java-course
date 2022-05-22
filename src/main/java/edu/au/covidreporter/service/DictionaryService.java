package edu.au.covidreporter.service;

import edu.au.covidreporter.repository.CovidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("dictionaryService")
public class DictionaryService {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CovidDataRepository covidDataRepository;

    @Autowired
    public DictionaryService(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }

	/**
	 * Returns all different countries from the 'covid_data' table sorted ascending.
	 * @return all different countries sorted ascending
	 */
	public List<String> getAllCountries() {
        return covidDataRepository.getAllCountries();
	}

}
