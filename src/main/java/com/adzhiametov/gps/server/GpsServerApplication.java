package com.adzhiametov.gps.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@SpringBootApplication
public class GpsServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GpsServerApplication.class, args);
        Path path = Paths.get("src/main/resources/database.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    @GetMapping("/")
    public String saveCoordinate(@RequestParam(value = "coordinate") String coordinate) throws IOException {
        Path path = Paths.get("src/main/resources/database.txt");
        Files.write(path, coordinate.getBytes(), StandardOpenOption.APPEND);
        Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
        return String.format("Координаты получены: %s", coordinate);
    }

    @GetMapping("/getall")
    public String getCoordinates() throws IOException {
        Path path = Paths.get("src/main/resources/database.txt");
        List<String> coordinates = Files.readAllLines(path);
        StringBuilder result = new StringBuilder();
        for (String coordinate : coordinates) {
            result.append(coordinate);
            result.append("\n");
        }
        return result.toString();
    }

    @GetMapping("/deleteall")
    public String removeCoordinates() throws IOException {
        Path path = Paths.get("src/main/resources/database.txt");
        Files.write(path, "".getBytes());
        return "Все записи удалены.";
    }

    @GetMapping("/map")
    public ModelAndView getMap() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("map.html");
        return modelAndView;
    }

}
//localhost:8080/?coordinate=4509.55219,N,03414.15137,E
//localhost:8080/getall
//localhost:8080/deleteall
//localhost:8080/map