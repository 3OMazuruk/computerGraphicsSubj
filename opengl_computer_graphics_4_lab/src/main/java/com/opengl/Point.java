package com.opengl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private double x;
    private double y;
    private Colors colors;

    public static void main(String[] args) {
        Complex complex = new Complex(3,-3);

        System.out.println(complex.pow(complex, 4));
    }
}
