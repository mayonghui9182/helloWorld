package effectivejava.chapter4.item19;

import java.time.Instant;

// Class whose constructor invokes an overridable method. NEVER DO THIS! (Page 95)
public class Super {
    // Broken - constructor invokes an overridable method
    public static final int anInt =1;;

    public Super() {
        this.overrideMe();
    }

    public void overrideMe() {
    }
}
