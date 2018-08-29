package org.patrick.untitled.randomfood.controller;

import org.patrick.untitled.randomfood.entity.FoodOrRestaurant;
import org.patrick.untitled.randomfood.repository.FoodOrRestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
public class RandomfoodController {

    private Logger log = LoggerFactory.getLogger(RandomfoodController.class);

    @Autowired
    private FoodOrRestaurantRepository repository;

    @RequestMapping("/randomfood/put")
    public FoodOrRestaurant add(@RequestParam(value = "name") String name, @RequestParam(value = "note", required = false) String note) {
        return repository.save(new FoodOrRestaurant(name, note));
    }

    @RequestMapping("/randomfood/get")
    public FoodOrRestaurant get(@RequestParam("name") String name) {
        Optional opt = repository.findById(name);
        if (opt.isPresent()) {
            return (FoodOrRestaurant) opt.get();
        } else {
            return null;
        }

    }

    @RequestMapping("/randomfood/del")
    public String del(@RequestParam("name") String name) {
        try {
            repository.deleteById(name);
            return "Deleted";
        } catch (Exception e) {
            return "Not exists";
        }
    }

    @RequestMapping("/randomfood/random")
    public FoodOrRestaurant random() {
        return repository.
                findAll(PageRequest.of((new Random().nextInt() % (int) repository.count()), 1)).
                getContent().get(0);
    }
}
