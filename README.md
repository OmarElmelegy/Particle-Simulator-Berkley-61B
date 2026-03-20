# Physics Particle Simulator in Java (Berkeley CS61B Project 0 Hard Mode)

This repository contains an interactive falling-sand style simulator built in Java for the CS61B Project 0 hard mode variant.

The simulator maintains a 2D grid of `Particle` objects, updates them in simulation ticks, and renders the board in real time with Princeton `StdDraw`.

## What This Project Includes

- Interactive drawing of particle types with keyboard shortcuts
- Gravity-based movement and simple fluid flow
- Probabilistic growth behavior for organic particles
- Lifespan-driven particles with color fade over time
- Unit tests for board setup, indexing, neighborhood lookup, movement, and stochastic outcomes

## Core Simulation Model

The main board state lives in `ParticleSimulator.particles`, a `Particle[][]` grid.

Each tick (`ParticleSimulator.tick()`):

1. The simulator scans the board in row-major order (bottom row upward).
2. For each cell, it builds a 4-direction neighborhood map (`UP`, `DOWN`, `LEFT`, `RIGHT`).
3. It runs particle behavior (`action(...)`) for that particle.
4. It decrements lifespan (`decrementLifespan()`) and clears dead particles to `EMPTY`.

Out-of-bounds neighbors are treated as `BARRIER`, which simplifies edge handling.

## Particle Flavors and Behavior

- `EMPTY` (`.`): black; inert background cell.
- `SAND` (`s`): yellow; falls one cell down if possible.
- `WATER` (`w`): blue; falls first, then may flow sideways randomly when blocked below.
- `BARRIER` (`b`): gray; static blocker.
- `PLANT` (`p`): green gradient; can grow up/left/right with randomness; lifespan `250`.
- `FLOWER` (`r`): pink gradient; similar growth behavior; lifespan `150`.
- `FIRE` (`f`): red gradient; lifespan `50`.
- `FOUNTAIN` (`n`): cyan; currently behaves as a non-growing, gravity-affected particle type.

## Controls

- Mouse hold/drag: paint selected particle on the board.
- Keyboard keys: `s` sand, `w` water, `b` barrier, `p` plant, `f` fire, `r` flower, `n` fountain, `.` eraser (`EMPTY`).

## Project Structure

```text
src/
  Direction.java           # Neighbor directions
  ParticleFlavor.java      # Enum of particle types
  Particle.java            # Particle state + behaviors
  ParticleSimulator.java   # Board state, tick loop, UI, main()

tests/
  TestParticle.java
  TestParticleSimulator.java
```

## Build and Run

Prerequisites:

- Java 17+ (or your course-required Java version)
- `algs4.jar` on classpath (for `StdDraw` and `StdRandom`)
- JUnit and Truth dependencies on test classpath

Example compile and run from repository root:

```bash
javac -cp "algs4.jar:src" src/*.java
java -cp "algs4.jar:src" ParticleSimulator
```

## Running Tests

Tests are under `tests/` and cover:

- constructor/grid initialization
- index bounds checks
- neighbor detection and edge behavior
- deterministic falling behavior
- probabilistic flow/growth behavior sampled across many runs

Run tests with your preferred runner (IDE, Maven/Gradle, or direct JUnit CLI) as long as JUnit + Truth + `algs4.jar` are on the classpath.

## Current Notes

- The simulation loop is continuous (`while (true)`) and renders with double buffering (`StdDraw.show()` + small pause).
- String board snapshots are supported via `ParticleSimulator.toString()`, which is used heavily in tests.
- Some particle flavor hooks (for example custom fountain/fire interactions) can be extended further if you want richer gameplay rules.

---