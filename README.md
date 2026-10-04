# Bridge Pattern

Assignment 3, topic A: Drawing.

Repository: https://github.com/yernururue/bridge-pattern

Base commit: `9d0154f684b86888b5bf3c2f1ca4d8193160420e`

Current stage: all seven checks are implemented, including runtime switching and the independent AsciiRenderer extension.

## Structure

| Role | Class | Source |
| --- | --- | --- |
| Abstraction | Shape | src/shapes/Shape.java |
| A1 | Circle | src/shapes/Circle.java |
| A2 | Square | src/shapes/Square.java |
| Implementor | Renderer | src/renderers/Renderer.java |
| I1 | VectorRenderer | src/renderers/VectorRenderer.java |
| I2 | RasterRenderer | src/renderers/RasterRenderer.java |
| I3 | AsciiRenderer | src/renderers/AsciiRenderer.java |
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
| T5 | Circle, VectorRenderer, RasterRenderer | sameObject=true; stateUnchanged=true; before=VECTOR circle radius=2; after=RASTER circle radius=2 |
| T6 | Circle + AsciiRenderer | ASCII circle radius=2 |
| T7 | Square + AsciiRenderer | ASCII square side=3 |

Each check compares actual results with expected values. A failed check prints the expected values. The summary counts the checks that passed.

T5 keeps a reference to the original Circle and compares it with the reference used after switching using ==. Its ID remains circle-switch and its radius remains 2.

```text
SUMMARY: 7/7 PASS
```

demo-output.txt contains the captured output. Running Main without --demo still prints the original Circle example.

## Independent extension

The base commit contains the working VectorRenderer and RasterRenderer solution with T1-T5. The extension adds AsciiRenderer and updates Main to run T6-T7. Shape, Circle, Square, Renderer, VectorRenderer, and RasterRenderer stay unchanged.

extension.diff records the Java source changes from the base commit to the committed extension. It was generated after committing the extension:

```sh
git diff 9d0154f684b86888b5bf3c2f1ca4d8193160420e HEAD -- src > extension.diff
```
