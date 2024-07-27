package br.com.java.tcc.util;

import org.apache.commons.lang3.StringUtils;

public final class Util {
    public static boolean validationDocument(String value){
        if(value.isBlank()){
            return false;
        }

        if(value.length() == 11){
            return isValidCPF(value);
        }
        return isValidCNPJ(value);
    }
    public static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += (cpf.charAt(i) - '0') * (10 - i);
        int digit1 = 11 - (sum % 11);
        if (digit1 > 9) digit1 = 0;
        sum = 0;
        for (int i = 0; i < 9; i++) sum += (cpf.charAt(i) - '0') * (11 - i);
        sum += digit1 * 2;
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9) digit2 = 0;
        return cpf.charAt(9) - '0' == digit1 && cpf.charAt(10) - '0' == digit2;
    }

    public static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;
        int[] weight = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        for (int i = 0; i < 12; i++) sum += (cnpj.charAt(i) - '0') * weight[i + 1];
        int digit1 = 11 - (sum % 11);
        if (digit1 > 9) digit1 = 0;
        sum = 0;
        for (int i = 0; i < 13; i++) sum += (cnpj.charAt(i) - '0') * weight[i];
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9) digit2 = 0;
        return cnpj.charAt(12) - '0' == digit1 && cnpj.charAt(13) - '0' == digit2;
    }
}
