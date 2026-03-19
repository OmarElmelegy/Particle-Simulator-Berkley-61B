import edu.princeton.cs.algs4.StdDraw;

public class ParticleSimulator {

    public int width;
    public int height;
    public Particle[][] particles;

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

    public static void main(String[] args) {

        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);

        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {
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
