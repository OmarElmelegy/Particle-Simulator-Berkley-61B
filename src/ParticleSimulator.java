import java.util.Map;

import edu.princeton.cs.algs4.StdDraw;

public class ParticleSimulator {

    public int width;
    public int height;
    public Particle[][] particles;

    public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
            's', ParticleFlavor.SAND,
            'b', ParticleFlavor.BARRIER,
            'w', ParticleFlavor.WATER,
            'p', ParticleFlavor.PLANT,
            'f', ParticleFlavor.FIRE,
            '.', ParticleFlavor.EMPTY,
            'n', ParticleFlavor.FOUNTAIN,
            'r', ParticleFlavor.FLOWER);

    public ParticleSimulator(int width, int height) {

        this.width = width;
        this.height = height;

        particles = new Particle[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                particles[i][j] = new Particle(ParticleFlavor.EMPTY);
            }
        }
    }

    public void drawParticles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public boolean validIndex(int x, int y) {
        if ((x <= (this.width - 1)) && (x >= 0)) {
            if ((y <= (this.height - 1)) && (y >= 0)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);

        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                Character keyInput = StdDraw.nextKeyTyped();

                nextParticleFlavor = LETTER_TO_PARTICLE.getOrDefault(keyInput, nextParticleFlavor);
            }

            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();

                particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
            }

            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }
    }
}
