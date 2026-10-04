# Bridge Pattern

Assignment 3, topic A: Drawing.

Repository: https://github.com/yernururue/bridge-pattern

Current stage: the two-by-two solution and runtime switching pass T1-T5. AsciiRenderer and T6-T7 will be added in the extension step.

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

The bridge field, constructor, execute() declaration, and setImplementation(Renderer) are in src/shapes/Shape.java. The delegated execute() methods are in Circle.java and Square.java. The T5 check is checkSwitch() in src/Main.java.

## Build and run

Use JDK 17 or newer. No external dependencies are required.

```sh
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

| Check | Classes | Expected result |
| --- | --- | --- |
| T1 | Circle + VectorRenderer | VECTOR circle radius=2 |
| T2 | Circle + RasterRenderer | RASTER circle radius=2 |
| T3 | Square + VectorRenderer | VECTOR square side=3 |
| T4 | Square + RasterRenderer | RASTER square side=3 |
| T5 | Circle, VectorRenderer, RasterRenderer | Same object using ==; ID circle-switch and radius 2 stay unchanged; VECTOR output becomes RASTER output |

Each check compares actual results with expected values. A failed check prints the expected values. The summary counts the checks that passed.

```text
SUMMARY: 5/5 PASS
```

demo-output.txt contains the captured output. Running Main without --demo still prints the original Circle example.
