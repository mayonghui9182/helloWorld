package functional;// functional/UnboundMethodReference.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Method reference without an object

import java.util.stream.IntStream;

class X {
    String f() {
        return "X::f()";
    }
}

interface MakeString {
    String make();
}

interface TransformX {
    String transform(X x);
}

public class UnboundMethodReference {
    public static void main(String[] args) {
        // MakeString ms = X::f; // [1]
        TransformX sp = X::f;
        X x = new X();
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // Same effect
    }
}
/* Output:
X::f()
X::f()
*/

/**
 * @创建人 mayh
 * @描述 【】
 * @创建时间 2019/5/25
 * @修改人和其它信息
 * @see streams/Matching.java
 */
interface MethodInvoker {
    void invoke(MethodReferece mr);
}

class MethodInvokerImp implements MethodInvoker {

    @Override
    public void invoke(MethodReferece mr) {
        System.out.println("MethodInvokerImp");
    }
}

interface MethodReferece {
    void test();
}

interface InnerImpMR<R, T> {
    void invoerSelf(R mi, T mr);
}

class InnerImpMRMInvoer {
    static void show(InnerImpMR<MethodInvoker, MethodReferece> iimr, int val) {
        iimr.invoerSelf(new MethodInvokerImp(), () -> System.out.println("123"));
    }

    public static void main(String[] args) {
        show(MethodInvoker::invoke, 2);
    }
}
