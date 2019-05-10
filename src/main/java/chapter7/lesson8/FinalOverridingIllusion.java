package chapter7.lesson8;

class WithFinals {
    static void d() {
        System.out.println("WithFinals.d()");
    }

    static void e() {
        d();
        System.out.println("WithFinals.e()");
    }

    private final void f() {
        e();
        System.out.println("WithFinals.f()");
    }

    public void g() {
        f();
        System.out.println("WithFinals.g()");
    }

    public void h() {
        g();
        f();
        e();
    }
}

class OverridingPrivate extends WithFinals {
    static void e() {
        d();
        System.out.println("OverridingPrivate.e()");
    }

    private final void f() {
        e();
        System.out.println("OverridingPrivate.f()");
    }

    public void g() {
        f();
        System.out.println("OverridingPrivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {
    public final void f() {
        System.out.println("OverridingPrivate2.f()");
    }

    public void g() {
        f();
        System.out.println("OverridingPrivate2.g()");
    }
}

public class FinalOverridingIllusion {
    public static void main(String[] args) {
        OverridingPrivate op = new OverridingPrivate();
        WithFinals wf = op;
        op.h();
        wf.h();
    }
}
