package generics;// generics/InstantiateGenericType.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.function.*;

class ClassAsFactory<T> implements Supplier<T> {
  Class<T> kind;
  ClassAsFactory(Class<T> kind) {
    this.kind = kind;
  }
  @Override
  public T get() {
    try {
      return kind.newInstance();
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}

class EmployeeOfInstantiateGenericType {
  @Override
  public String toString() { return "EmployeeOfInstantiateGenericType"; }
}

public class InstantiateGenericType {
  public static void main(String[] args) {
    ClassAsFactory<EmployeeOfInstantiateGenericType> fe =
      new ClassAsFactory<>(EmployeeOfInstantiateGenericType.class);
    System.out.println(fe.get());
    ClassAsFactory<Integer> fi =
      new ClassAsFactory<>(Integer.class);
    try {
      System.out.println(fi.get());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
/* Output:
EmployeeOfInstantiateGenericType
java.lang.InstantiationException: java.lang.Integer
*/
