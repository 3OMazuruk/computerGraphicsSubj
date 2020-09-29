package com.opengl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Triangle {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
}
