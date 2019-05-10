package chapter8.lesson3;

public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}

class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Before Glyph.draw()");
        draw();
        System.out.println("After Glyph.draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int radius) {
        System.out.println("RoundGlyph.RoundGlyph(), radius = " + this.radius);
        this.radius = radius;
        System.out.println("RoundGlyph.RoundGlyph(), radius = " + this.radius);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph.draw(), radius = " + this.radius);
    }
}
