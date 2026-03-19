import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import org.junit.Test;

public class TestParticleSimulator {

    @Test
    public void testConstructor_initializesEmptyGrid_usingIndices() {
        int w = 50;
        int h = 60;
        ParticleSimulator simulator = new ParticleSimulator(w, h);

        // 1. Verify the outer array length (Width)
        assertThat(simulator.particles).hasLength(w);

        // 2. Iterate using Integer Indices
        for (int x = 0; x < w; x++) {

            // Verify the inner array length (Height) for this column
            assertThat(simulator.particles[x]).hasLength(h);

            for (int y = 0; y < simulator.height; y++) {
                Particle particle = simulator.particles[x][y];

                // Verify the particle is not null
                assertThat(particle).isNotNull();

                // Verify the particle is initialized to EMPTY
                assertWithMessage("Particle at x=%s, y=%s should be EMPTY", x, y)
                        .that(particle.flavor)
                        .isEqualTo(ParticleFlavor.EMPTY);
            }
        }
    }

        @Test
    public void testValidIndex() {
        // Arrange: Create a grid of 10x20
        int width = 10;
        int height = 20;
        ParticleSimulator sim = new ParticleSimulator(width, height);

        // Assert: Valid Indices (Inside bounds)
        assertThat(sim.validIndex(0, 0)).isTrue();             // Bottom-Left Corner
        assertThat(sim.validIndex(9, 19)).isTrue();            // Top-Right Corner (width-1, height-1)
        assertThat(sim.validIndex(5, 10)).isTrue();            // Middle

        // Assert: Invalid Indices (Outside bounds)
        assertThat(sim.validIndex(-1, 0)).isFalse();           // Negative X
        assertThat(sim.validIndex(0, -1)).isFalse();           // Negative Y
        assertThat(sim.validIndex(10, 0)).isFalse();           // X == Width (Off by one)
        assertThat(sim.validIndex(0, 20)).isFalse();           // Y == Height (Off by one)
        assertThat(sim.validIndex(100, 100)).isFalse();        // Far out of bounds
    }

}
