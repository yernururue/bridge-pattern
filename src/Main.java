import renderers.VectorRenderer;
import shapes.Circle;
import shapes.Shape;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle("circle-1", 2, new VectorRenderer());
        System.out.println(circle.execute());
    }
}
