package com.opengl;

public enum Colors {
    RED(1f,0f,0f), GREEN(0f,1f,0f), BLUE(0f,0f,1f),
    ORANGE(1f,0.5f,0f), YELLOW(1f,1f,0f), PURPLE(1f,0f,1f),
    CYAN(0f,1f,1f), GRAY(0.8f,0.8f,0.8f);

    float red, blue, green;

    Colors(float red, float green, float blue) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public float getRed() {
        return red;
    }

    public float getBlue() {
        return blue;
    }

    public float getGreen() {
        return green;
    }
}
