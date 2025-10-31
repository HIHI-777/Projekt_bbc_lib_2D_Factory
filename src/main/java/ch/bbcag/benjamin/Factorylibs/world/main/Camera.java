package ch.bbcag.benjamin.Factorylibs.world.main;

public class Camera {
    private int x, y;
    private int width, height;

    public Camera(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void moveUp(int amount) {
        this.y -= amount;
    }

    public void moveDown(int amount) {
        this.y += amount;
    }

    public void moveLeft(int amount) {
        this.x += amount;
    }

    public void moveRight(int amount) {
        this.x -= amount;
    }


}
