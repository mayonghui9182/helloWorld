package functional;
interface WithOneArgs {
    String detailed(String head);
}
public class MyLambdaExpressions {
    private static final String CONSTANT_STRING = "constant string!";
    private   String INSTANCE_STRING = "instance string!";
    private static WithOneArgs staticLambda = h -> " constant string!";
    private static WithOneArgs staticLambda2 = h -> h + CONSTANT_STRING;
    private WithOneArgs instanceLambda = h -> h + h +" constant string!";
    private WithOneArgs instanceLambda2 = h -> h + h + h  + INSTANCE_STRING;
    public void lambdaMethod(){
        String test = "local string!";
        WithOneArgs localLambda = h -> h + h + h + h+ test;
        WithOneArgs localLambda1 = h -> h + h + h + h + h+ "local string!";
        WithOneArgs localLambda2= h -> h + h + h + h + h + h+CONSTANT_STRING;
        System.out.println(localLambda.detailed("staticLambda!"));
        System.out.println(localLambda1.detailed("staticLambda!"));
        System.out.println(localLambda2.detailed("staticLambda!"));

    }
    public static void main(String[] args) {
        WithOneArgs localLambda = h -> h + h + h + h + h + h + "main";
        System.out.println(staticLambda.detailed("staticLambda!"));
        System.out.println(staticLambda2.detailed("staticLambda2!"));
        MyLambdaExpressions myLambdaExpressions = new MyLambdaExpressions();
        System.out.println(myLambdaExpressions.instanceLambda.detailed("instanceLambda!"));
        System.out.println(myLambdaExpressions.instanceLambda2.detailed("instanceLambda2!"));
        myLambdaExpressions.lambdaMethod();
    }
}
