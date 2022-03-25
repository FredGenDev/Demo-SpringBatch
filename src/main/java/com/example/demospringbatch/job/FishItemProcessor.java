package com.example.demospringbatch.job;

import com.example.demospringbatch.domain.Fish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class FishItemProcessor implements ItemProcessor<Fish,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fish.class);

    @Override
    public String process(Fish fish) throws Exception {
        String retStr = "Name = " + fish.getName() + " -- taille min = " + fish.getSizeMin() + " -- taille max = " + fish.getSizeMax();
        LOGGER.info("{}",retStr);
        return retStr;
    }
}
