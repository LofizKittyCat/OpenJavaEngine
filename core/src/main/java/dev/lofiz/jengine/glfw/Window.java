package dev.lofiz.jengine.glfw;

import dev.lofiz.jengine.event.EventBus;
import dev.lofiz.jengine.glfw.events.KeyCallbackEvent;
import dev.lofiz.jengine.glfw.events.MouseButtonCallbackEvent;
import org.lwjgl.Version;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.tinylog.Logger;

import java.nio.IntBuffer;

public class Window {

    private final String title;
    private final int width, height;

    private long window;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void run() {
        Logger.info("LWJGL Version: {}!", Version.getVersion());

        this.init();
        this.loop();

        Callbacks.glfwFreeCallbacks(this.window);
        glfwDestroyWindow(this.window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {

        if(!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.window = glfwCreateWindow(this.width, this.height, this.title, 0L, 0L);

        if(this.window == 0L) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
            EventBus.publish(new KeyCallbackEvent(window, key, scancode, action, mods));
        });

        glfwSetMouseButtonCallback(this.window, (window, button, action, mods) -> {
            EventBus.publish(new MouseButtonCallbackEvent(window, button, action, mods));
        });

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        while(!glfwWindowShouldClose(this.window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(this.window);

            glfwPollEvents();
        }
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

    public long getWindow() {
        return window;
    }
}
