package dev.lofiz.jengine.renderer.shader;

import java.util.HashMap;
import java.util.Map;

public class ShaderManager {

    /**
     * Map string id to shader object
     */
    public final Map<String, Shader> shaders = new HashMap<>();

    /**
     * Cache vertex shader file content locally
     */
    public final Map<String, String> vertexShaderContentCache = new HashMap<>();

    /**
     * Cache fragment shader file content locally
     */
    public final Map<String, String> fragmentShaderContentCache = new HashMap<>();


}
