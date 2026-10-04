# Bridge Pattern

Assignment 3, topic A: Drawing.

Repository: https://github.com/yernururue/bridge-pattern

Initial stage: basic classes and one example. The T1-T7 demo and AsciiRenderer will be added in later steps. The working T1-T5 base commit has not been created yet.

## Structure

| Role | Class | Source |
| --- | --- | --- |
| Abstraction | Shape | src/shapes/Shape.java |
| A1 | Circle | src/shapes/Circle.java |
| A2 | Square | src/shapes/Square.java |
| Implementor | Renderer | src/renderers/Renderer.java |
| I1 | VectorRenderer | src/renderers/VectorRenderer.java |
| I2 | RasterRenderer | src/renderers/RasterRenderer.java |
| Client | Main | src/Main.java |

Shape stores a Renderer supplied through its constructor. Circle and Square delegate drawing through that interface. setImplementation(Renderer) replaces the renderer.

## Build and run

Use JDK 17 or newer. No external dependencies are required.

```sh
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main
```

Expected output:

```text
VECTOR circle radius=2
```
