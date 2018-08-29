package org.patrick.untitled.randomfood.controller;

import org.patrick.untitled.randomfood.entity.FoodOrRestaurant;
import org.patrick.untitled.randomfood.repository.FoodOrRestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@Controller
@CrossOrigin("*")
public class RandomfoodController {

    private Logger log = LoggerFactory.getLogger(RandomfoodController.class);

    @Autowired
    private FoodOrRestaurantRepository repository;

    @RequestMapping("/randomfood")
    public String randomfood() {
        return "index.html";
    }

    @RequestMapping("/randomfood/put")
    @ResponseBody
    public FoodOrRestaurant add(@RequestParam(value = "name") String name, @RequestParam(value = "note", required = false) String note) {
        return repository.save(new FoodOrRestaurant(name, note));
    }

    @RequestMapping("/randomfood/get")
    @ResponseBody
    public FoodOrRestaurant get(@RequestParam("name") String name) {
        Optional opt = repository.findById(name);
        if (opt.isPresent()) {
            return (FoodOrRestaurant) opt.get();
        } else {
            return null;
        }

    }

    @RequestMapping("/randomfood/del")
    @ResponseBody
    public String del(@RequestParam("name") String name) {
        try {
            repository.deleteById(name);
            return "Deleted";
        } catch (Exception e) {
            return "Not exists";
        }
    }

    @RequestMapping("/randomfood/random")
    @ResponseBody
    public FoodOrRestaurant random() {
        int random = Math.abs(new Random().nextInt());
        int count = (int) repository.count();

        if(count == 0) {
            return null;
        }

        return repository.
                findAll(PageRequest.of(random % count, 1)).
                getContent().
                get(0);
    }
}
