package com.LucasVicentee.Controllers;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@RestController
@RequestMapping("/math")
public class MathController {

    //http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo
    ) throws IllegalAccessException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new IllegalAccessException();
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) throws IllegalAccessException {
        if (strNumber == null || strNumber.isEmpty())  throw new IllegalAccessException();
        String number = strNumber.replace(",", "."); // Repassando os separadores númericos de "," para "."
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", "."); // Repassando os separadores númericos de "," para "."
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }

    //http://localhost:8080/math/subtraction/3/5

    //http://localhost:8080/math/division/3/5

}
