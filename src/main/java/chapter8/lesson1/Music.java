package chapter8.lesson1;

public class Music {

    public static void tune(Instrument instrument) {
        instrument.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }
}

class Instrument {
    public void play(Note note) {
        System.out.println("Instrument.play " + note);
    }
}

class Wind extends Instrument {
    @Override
    public void play(Note note) {
        System.out.println("Wind.play " + note);
    }
}

class Stringed extends Instrument {
    @Override
    public void play(Note note) {
        System.out.println("Stringed.play " + note);
    }
}

class Brass extends Instrument {
    @Override
    public void play(Note note) {
        System.out.println("Brass.play " + note);
    }
}
