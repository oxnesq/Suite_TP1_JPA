package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Une méthode qui, pour un pays dont l'ID est passé en paramètre,
     * calcule sa population
     * (somme des populations des villes)
     */
    @Query(value = "SELECT sum(population) As Population "
            + "FROM Country "
            + "INNER JOIN City ON Country.id=Country_id "
            + "WHERE Country.id = :idCountry ",
            nativeQuery = true)
    public int calculPop(Integer idCountry);

    @Query(value = "SELECT Country.name As Nom, sum(population) As Population "
            + "FROM Country "
            + "INNER JOIN City ON Country.id=Country_id ",
            nativeQuery = true)
    public List<PopulationCountry> paysParPop();

}
