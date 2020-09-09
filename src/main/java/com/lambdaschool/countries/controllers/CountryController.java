package com.lambdaschool.countries.controllers;


import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.respositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController
{

    @Autowired
    private CountryRepository emprepos;

    private List<Country> findCountry(List<Country> myList, CheckCountry tester)
    {
        List<Country> tempList = new ArrayList<>();

        for (Country c: myList)
        {
            if (tester.test(c))
            {
                tempList.add(c);
            }
        }

        return tempList;
    }

    // http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> myList = new ArrayList<>();

        emprepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/u

    // http://localhost:2019/population/total

    // http://localhost:2019/population/min

    // http://localhost:2019/population/max

    // http://localhost:2019/popluation/median

}
