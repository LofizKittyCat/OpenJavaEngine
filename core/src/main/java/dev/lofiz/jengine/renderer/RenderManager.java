package dev.lofiz.jengine.renderer;

import dev.lofiz.jengine.renderer.shader.Shader;

import java.util.concurrent.CopyOnWriteArrayList;

public class RenderManager {

    private final CopyOnWriteArrayList<Shader> activeShaders = new CopyOnWriteArrayList<>();

    public void renderShader(Shader shader) {
        //TODO: Render Shader
    }

    public void putShader(Shader shader) {
        this.activeShaders.add(shader);
    }

    public CopyOnWriteArrayList<Shader> getActiveShaders() {
        return activeShaders;
    }
}
