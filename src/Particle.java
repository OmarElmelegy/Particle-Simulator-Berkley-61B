import java.util.Map;
import java.awt.Color;

public class Particle {

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;

    public static final Map<ParticleFlavor, Integer> LIFESPANS = Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
            ParticleFlavor.PLANT, PLANT_LIFESPAN, ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public ParticleFlavor flavor;
    public int lifespan;

    public Particle(ParticleFlavor pFlavor) {
        this.flavor = pFlavor;
        this.lifespan = LIFESPANS.getOrDefault(pFlavor, -1);
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY) {
            return Color.black;
        }

        else if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        }

        else if (flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        }

        else if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        }

        else if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        }

        else if (flavor == ParticleFlavor.PLANT) {
            return new Color(0, 255, 0);
        }

        else if (flavor == ParticleFlavor.FIRE) {
            return new Color(255, 0, 0);
        }

        else if (flavor == ParticleFlavor.FLOWER) {
            return new Color(255, 141, 161);
        }

        else
            return null;
    }

    void moveInto(Particle other) {

        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

}
