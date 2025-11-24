package com.LucasVicentee.request.converters;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) { // Convertendo a string strNumber para Double com number
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedOperationException("Please set a numeric value!"); // Exceção para valores não númericos
        String number = strNumber.replace(",", "."); // Repassando os separadores númericos de "," para "."
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) { // Verificando se o conteúdo digitado pelo usuário é numérico ou é String
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", "."); // Repassando os separadores númericos de "," para "."
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }
}
