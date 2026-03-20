import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Particle {

    public static final int PLANT_LIFESPAN = 250;
    public static final int FLOWER_LIFESPAN = 150;
    public static final int FIRE_LIFESPAN = 50;

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
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }

        else if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }

        else if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
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

    void growInto(Particle other) {

        other.flavor = this.flavor;
        other.lifespan = this.lifespan;
        
    }

    public void fall(Map<Direction, Particle> neighbours){
        Particle neighbourParticle = neighbours.get(Direction.DOWN);
        
        if (neighbourParticle != null && neighbourParticle.flavor.equals(ParticleFlavor.EMPTY)) {
            this.moveInto(neighbourParticle);
        }
    }

    public void flow(Map<Direction, Particle> neighbours) {
        int randInt = StdRandom.uniformInt(3);

        Particle leftNeighbourParticle = neighbours.get(Direction.LEFT);
        Particle rightNeighbourParticle = neighbours.get(Direction.RIGHT);

        if (randInt == 0) {
            return;
        }

        else if (randInt == 1) {
            if (rightNeighbourParticle.flavor.equals(ParticleFlavor.EMPTY)) {
                this.moveInto(rightNeighbourParticle);    
            }
        }

        else if (randInt == 2) {
            if (leftNeighbourParticle.flavor.equals(ParticleFlavor.EMPTY)) {
                this.moveInto(leftNeighbourParticle);
            }
        }
    }

    public void grow(Map<Direction, Particle> neighbours) {
        int randInt = StdRandom.uniformInt(20);

        Particle leftNeighbourParticle = neighbours.get(Direction.LEFT);
        Particle rightNeighbourParticle = neighbours.get(Direction.RIGHT);
        Particle upwardNeighourbParticle = neighbours.get(Direction.UP);

        if (randInt == 0) {
            if (upwardNeighourbParticle.flavor.equals(ParticleFlavor.EMPTY)) {
                this.growInto(upwardNeighourbParticle);
            }
        }

        else if (randInt == 1) {
            if (rightNeighbourParticle.flavor.equals(ParticleFlavor.EMPTY)) {
                this.growInto(rightNeighbourParticle);    
            }
        }

        else if (randInt == 2) {
            if (leftNeighbourParticle.flavor.equals(ParticleFlavor.EMPTY)) {
                this.growInto(leftNeighbourParticle);
            }
        }

        else if (randInt > 2 && randInt < 10){
            return;
        }
    }

    public void burn(Map<Direction, Particle> neighbours) {
        int randInt = StdRandom.uniformInt(10);

        
        if (randInt <= 4) {
            for (Particle neighbourParticle : neighbours.values()) {
                if (neighbourParticle.flavor.equals(ParticleFlavor.FLOWER) || neighbourParticle.flavor.equals(ParticleFlavor.PLANT)) {
                    this.growInto(neighbourParticle);
                }
            }    
        }

    }

    public void action(Map<Direction, Particle> neighbours) {
        if (this.flavor.equals(ParticleFlavor.EMPTY)) {
            return;
        }

        if (!this.flavor.equals(ParticleFlavor.BARRIER)) {
            fall(neighbours);
        }

        if (this.flavor.equals(ParticleFlavor.WATER)) {
            flow(neighbours);
        }

        if (this.flavor.equals(ParticleFlavor.PLANT) || this.flavor.equals(ParticleFlavor.FLOWER)) {
            grow(neighbours);
        }
    }

    public void decrementLifespan() {
        
        if (this.lifespan > 0) {
            this.lifespan -= 1;
        }

        if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }
}
