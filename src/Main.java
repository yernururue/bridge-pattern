import renderers.RasterRenderer;
import renderers.VectorRenderer;
import shapes.Circle;
import shapes.Shape;
import shapes.Square;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            Shape circle = new Circle("circle-1", 2, new VectorRenderer());
            System.out.println(circle.execute());
        }
    }

    private static void runDemo() {
        Circle vectorCircle = new Circle("circle-1", 2, new VectorRenderer());
        Circle rasterCircle = new Circle("circle-1", 2, new RasterRenderer());
        Square vectorSquare = new Square("square-1", 3, new VectorRenderer());
        Square rasterSquare = new Square("square-1", 3, new RasterRenderer());

        int passed = 0;
        passed += check("T1", "Circle + VectorRenderer", vectorCircle.execute(), "VECTOR circle radius=2");
        passed += check("T2", "Circle + RasterRenderer", rasterCircle.execute(), "RASTER circle radius=2");
        passed += check("T3", "Square + VectorRenderer", vectorSquare.execute(), "VECTOR square side=3");
        passed += check("T4", "Square + RasterRenderer", rasterSquare.execute(), "RASTER square side=3");
        passed += checkSwitch();

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    private static int check(String testId, String classes, String actual, String expected) {
        boolean passed = expected.equals(actual);
        System.out.println(testId + " " + (passed ? "PASS" : "FAIL") + " | " + classes + " | result=" + actual);
        if (!passed) {
            System.out.println(" expected=" + expected);
        }
        return passed ? 1 : 0;
    }

    private static int checkSwitch() {
        Circle circle = new Circle("circle-switch", 2, new VectorRenderer());
        Circle original = circle;
        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();
        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());
        String after = circle.execute();

        boolean sameObject = original == circle;
        boolean stateUnchanged = idBefore.equals(circle.getId()) && radiusBefore == circle.getRadius();
        boolean passed = sameObject && stateUnchanged
                && before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2");

        System.out.println("T5 " + (passed ? "PASS" : "FAIL")
                + " | Circle + VectorRenderer -> RasterRenderer"
                + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
        System.out.println(" before=" + before + " | after=" + after);
        if (!passed) {
            System.out.println(" expected: sameObject=true | stateUnchanged=true"
                    + " | before=VECTOR circle radius=2 | after=RASTER circle radius=2");
        }
        return passed ? 1 : 0;
    }
}
