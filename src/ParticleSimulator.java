public class ParticleSimulator {

    public Particle[][] particles;
    public int width;
    public int height;

    

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


}
