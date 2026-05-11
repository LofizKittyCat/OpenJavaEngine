package dev.lofiz.jengine.application;

import dev.lofiz.jengine.glfw.Window;
import dev.lofiz.jengine.renderer.RenderManager;

public class Application {

    private final String title;
    private final int width, height;

    private final Window window;
    private final RenderManager renderManager = new RenderManager();

    public Application(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.window = new Window(this.title, this.width, this.height);
    }

    public void main(String[] args) {
        this.init();
    }

    private void init() {
        this.window.run();
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Window getWindow() {
        return window;
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }
}
