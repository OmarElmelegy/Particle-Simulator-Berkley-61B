import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class TestParticle {
    @Test
    public void testConstructor() {
        Particle fp = new Particle(ParticleFlavor.FIRE);
        assertThat(fp.flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(fp.lifespan).isEqualTo(10);

        Particle sp = new Particle(ParticleFlavor.SAND);
        assertThat(sp.flavor).isEqualTo(ParticleFlavor.SAND);
        assertThat(sp.lifespan).isEqualTo(-1);
    }
}
