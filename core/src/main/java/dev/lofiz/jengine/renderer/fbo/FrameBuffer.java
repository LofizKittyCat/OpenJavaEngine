package dev.lofiz.jengine.renderer.fbo;

import org.lwjgl.opengl.GL30;

public class FrameBuffer {

    private int framebufferObject;

    private int width, height;

    public FrameBuffer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void create() {
        this.framebufferObject = GL30.glGenFramebuffers();

        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.framebufferObject);


    }

    public void onResize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
