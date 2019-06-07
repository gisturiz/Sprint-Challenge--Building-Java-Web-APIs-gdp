package com.lambdaschool.apigdp.controller;

import com.lambdaschool.apigdp.ApigdpApplication;
import com.lambdaschool.apigdp.exception.ResourceNotFoundException;
import com.lambdaschool.apigdp.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/data")
public class CountryController
{
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);


    //localhost:8080/data/names
    @GetMapping(value = "/names",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries(HttpServletRequest request)
    {
        logger.info(request.getRequestURI());

        ApigdpApplication.ourCountryList.gdpList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(ApigdpApplication.ourCountryList.gdpList, HttpStatus.OK);
    }

    //localhost:8080/data/economy
    @GetMapping(value = "/economy",
            produces = {"application/json"})
    public ResponseEntity<?> getAllGDP(HttpServletRequest request)
    {
        logger.info(request.getRequestURI());

        ApigdpApplication.ourCountryList.gdpList.sort((e1, e2) -> (int)(e2.getGdp() - e1.getGdp()));
        return new ResponseEntity<>(ApigdpApplication.ourCountryList.gdpList, HttpStatus.OK);
    }

    //localhost:8080/data/country/stats/median
    @GetMapping(value = "/country/stats/median",
            produces = {"application/json"})
    public ResponseEntity<?> getMedianGDP(HttpServletRequest request)
    {
        logger.info(request.getRequestURI());

        ApigdpApplication.ourCountryList.gdpList.sort((e1, e2) -> (int)(e1.getGdp() - e2.getGdp()));

        GDP rtnGDP = ApigdpApplication.ourCountryList.gdpList.get((ApigdpApplication.ourCountryList.gdpList.size() / 2 + 1));
        return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
    }


    // localhost:8080/data/country/2
    @GetMapping(value = "/country/{id}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountryDetail(HttpServletRequest request,
            @PathVariable
                    long id) throws ResourceNotFoundException
    {
        logger.info(request.getRequestURI());

        GDP rtnEmp;
        if (ApigdpApplication.ourCountryList.findCountry(e -> (e.getId() == id)) == null)
        {
            throw new ResourceNotFoundException("Country with id " + id + " not found");
        } else
        {
            rtnEmp = ApigdpApplication.ourCountryList.findCountry(e -> (e.getId() == id));
        }
        return new ResponseEntity<>(rtnEmp, HttpStatus.OK);
    }


    // localhost:8080/data/economy/table
    @GetMapping(value = "/economy/table")
    public ModelAndView displayGDPTable(HttpServletRequest request)
    {
        logger.info(request.getRequestURI());

        ApigdpApplication.ourCountryList.gdpList.sort((e1, e2) -> (int)(e2.getGdp() - e1.getGdp()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("country");
        mav.addObject("gdpList", ApigdpApplication.ourCountryList.gdpList);

        return mav;
    }
}
