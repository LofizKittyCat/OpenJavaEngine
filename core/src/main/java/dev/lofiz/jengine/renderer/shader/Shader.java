package dev.lofiz.jengine.renderer.shader;

import dev.lofiz.jengine.util.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.tinylog.Logger;

public class Shader {

    private final String vertexPath, fragmentPath;

    private int programId;

    public Shader(String vertexPath, String fragmentPath) {
        this.vertexPath = vertexPath;
        this.fragmentPath = fragmentPath;
    }

    public void loadFromPath() {
        String vContent = FileUtils.readFile(vertexPath);
        String fContent = FileUtils.readFile(fragmentPath);

        this.load(vContent, fContent);
    }

    public void load(String vertexContent, String fragmentContent) {

        int vertex = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertex, vertexContent);
        GL20.glCompileShader(vertex);

        int fragment = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragment, fragmentContent);
        GL20.glCompileShader(fragment);

        this.programId = GL20.glCreateProgram();

        GL20.glAttachShader(programId, vertex);
        GL20.glAttachShader(programId, fragment);

        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

        GL20.glDeleteShader(vertex);
        GL20.glDeleteShader(fragment);

        this.use();
    }

    public void use() {
        GL20.glUseProgram(this.programId);
    }

    public int getProgramId() {
        return programId;
    }
}
