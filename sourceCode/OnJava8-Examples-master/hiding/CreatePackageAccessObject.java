// hiding/CreatePackageAccessObject.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {WillNotCompile}
import hiding.packageaccess.*;

public class CreatePackageAccessObject {
  public static void main(String[] args) {
    new PublicConstructor();
  }
}
