package com.lambdaschool.apigdp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class GDP
{
    private static final Logger logger = LoggerFactory.getLogger(GDP.class);

    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String name;
    private String gdp;

    public GDP(String name, String gdp)
    {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = gdp;

        logger.info("We created an country");
        logger.debug("Yes we created a country with id " + this.id);
    }

    public GDP()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getGdp()
    {
        return Long.parseLong(gdp);
    }

    public void setGdp(String gdp)
    {
        this.gdp = gdp;
    }

    public long getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "GDP{" + "id=" + id + ", name='" + name + '\'' + ", gdp=" + gdp + '}';
    }
}


