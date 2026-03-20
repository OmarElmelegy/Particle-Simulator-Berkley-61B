import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

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
}
