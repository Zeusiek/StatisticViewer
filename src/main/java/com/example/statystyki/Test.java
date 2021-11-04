package com.example.statystyki;

public class Test {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 516; i++){
            builder.append("UPDATE perfumy SET id_perfum = ").append(i).append(" WHERE id_perfum = 'p_" ).append(i).append("';\n");
        }
        System.out.println(builder);
    }
}
