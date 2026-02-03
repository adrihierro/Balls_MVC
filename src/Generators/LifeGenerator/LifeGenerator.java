package Generators.LifeGenerator;

import controller.Controller;

/**
 * Generador automático de vida (bolas/asteroides).
 * Solo se comunica con el Controller, respetando la separación del patrón MVC.
 */
public class LifeGenerator implements Runnable {
    private final Controller controller;
    private final int intervalMs;
    private Thread thread;
    private volatile boolean running;
    private volatile boolean paused;

    /**
     * Constructor del generador de vida.
     * @param controller El controlador al que notificar para crear bolas
     * @param intervalMs Intervalo en milisegundos entre cada generación
     */
    public LifeGenerator(Controller controller, int intervalMs) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller no puede ser null");
        }
        if (intervalMs <= 0) {
            throw new IllegalArgumentException("El intervalo debe ser mayor a 0");
        }
        this.controller = controller;
        this.intervalMs = intervalMs;
        this.running = false;
        this.paused = false;
    }

    /**
     * Inicia el generador de vida.
     */
    public void start() {
        if (!running) {
            running = true;
            paused = false;
            thread = new Thread(this, "LifeGenerator-Thread");
            thread.start();
        }
    }

    /**
     * Detiene el generador de vida.
     */
    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }

    /**
     * Pausa la generación de vida.
     */
    public void pause() {
        this.paused = true;
    }


    public void resume() {
        this.paused = false;
    }

    /**
     * Verifica si el generador está activo.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Verifica si el generador está pausado.
     */
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (!paused) {
                    //call to the controller for generate a new Random Ball
                    controller.generateRandomBall();
                }

                // Esperar el intervalo configurado
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
}