## Servlet规范

​	HTTP协议规定了客户端与服务之间的通信规范。那么当client请求的数据到达服务端的时候，我们应该怎么处理它，应该以什么样的数据接口代表它呢？

​	servlet规范定义了java web处理请求的一整套接口规范，包括请求数据结构，响应数据结构，请求处理逻辑等等。

### servlet容器

​		servlet容器是web server或应用服务的一部分，该Web服务器或应用程序服务器提供通过其发送请求并返回响应的网络服务，解码基于MIME的请求并格式化基于MIME的响应。

​		servlet处理请求的逻辑：

1. 客户端向一个web服务器发送一个http请求
2. 请求被web服务器接收，并移交给servlet容器进行处理。
3. servlet容器根据servelt配置以及url决定那个servlet被调用来处理这个请求，并使用表示请求和响应的对象调用它。
4. Servlet使用请求对象来查找远程用户是谁，请求已经发送了哪些HTTP POST参数以及其他相关数据。servlet自行自定义逻辑，并通过response将响应数据发送给客户端。
5. servlet处理完请求后，servlet容器将响应对象中的数据输出到客户端，将response控制权返回给web server。

​	所以，servlet定义请求处理逻辑，request构建请求数据结构，response构建响应数据，再加上session等一些辅助对象，一起完成整个HTTP通信。

### Servlet接口

​	servlet接口是java servlet api的抽象，它定义了一系列的模板方法，用来执行处理http请求的逻辑。所有的servlet实现类必须直接或间接的实现改接口。为了减少开发者的工作量，在java servlet api中由两个实现了改接口的类，分别是GenericServlet和HttpServlet。在大多数情况下，开发者会继承HttpServlet来实现他们自己的servlet。

#### service方法请求处理方法

​	servlet容器将请求路由到一个servlet实例，然后调用service方法处理该请求。servlet开发人员需要保证service方法的并发调用，应为web容器经常（但不是必须）将并发请求映射到同一个servlet实例，再不同线程中进行并发处理。

#### 特定请求处理方法

​	HttpServlet增加了常用请求的处理方法，当HttpServlet实现类（未重写service方法）被调用的时候，service方法会根据HTTP method调用对应的方法经处理改请求。所以当开发者通过继承HttpServlet来开发自己的servlet时，它可以通过重写下列方法来处理自己关心的请求：

- doGet
- doPost
- doPut
- doDelete
- doHead
- doOptions
- doTrace

#### 生命周期

​	servlet的生命周期由loading，instantiated，initialized，handle request，destroy等几个阶段。servlet在启动或者在第一次调用的时候进行加载，实例化，初始化等操作，在每次调用的时候处理请求，在服务器判断需要销毁servlet 的时候，调用它的destroy方法，然后被gc回收。

​	servlet在各个阶段都可能会遇到exception，会抛出响应的Exception实例，由响应的处理机制。

#### 异步处理

​	有时servlet需要等待一些资源，才能对请求做出对应的响应。如果在servlet方法中阻塞不仅效率低下，还浪费了计算机资源（线程，jdbc连接其他有限的资源等）。

​	servlet3.0增加了异步处理请求的特性，让线程完成这个请求，将结果返回给容器，等待处理其他请求。异步处理的基本步骤如下：

1. 接收请求，通过filter和权限验证等，到达servlet。
2. servlet根据请求参数得出这个请求的性质。
3. servlet发出对资源或数据的请求。
4. servlet返回但是不生成response
5. 一段时间后，资源和数据获取完毕，处理该事件的线程继续处理，或者将改请求进行转发。

​	异步处理很多问题待完善！

### deployment descriptor

​	在容器启动的时候，需要知道一些参数信息，来支持程序的启动，比如由哪些servlet，以及servlet与URL的映射关系，有哪些filter，filter的映射关系等等。deployment descriptor用来保存web容器的配置信息的文件。

deployment descriptor支持下列元素进行配置程序需要的信息：

- ServletContext初始化参数
- Session configuration
- servlet Declaration
- Servlet Mapping
- Application lifecycle Listener class
- Filter Declaration and filter mapping
- MIME type mapping
- welcome file list
- error page
- local and encoding mappings
- security configuration，including login configuration，security-constraint，security-role，security-role-ref and run-as

在Java servlet 2.4及其以前，the deployment descriptor以XML方式进行配置，文件名规定为web.xml。

通过解析deployment descriptor，servlet容器获取了改web应用程序的所有信息，就可以构建一个web application。

### ServletContext

​	在解析deployment descriptor后，servlet容器构建一个ServletContext用来存储部署描述符中的这些信息。ServletContext代表一个web application，映射到一个特定的URL，所有该URL的请求都交给这个web application 进行处理。这个ServletContext又会根据URL的sub Component寻找到特定的servlet进行处理这个URL。

​	一个deployment descriptor都可以配置ServletContext的初始化参数。可以通过ServletContext接口的getInitParameter和getInitParameterNames方法访问这些参数。

​	可以用ServletContext接口的一些方法配置servlet，listener，filter和URL pattern等信息。

​	可以通过ServletContext接口的setAttribute，getAttribute，getAttributeNames，removeAttribute等方法在ServletContext中操作属性，在servlet之间进行消息传递，但是当时在一个分布式系统中的时候，需要借用其他工具来达到在所有servlet之间共享数据的目的。

​	ServletContext接口的getResource，getResourceAsStream方法可以获取web application下面的静态文件（html，jpg，git等）。这两个方法接收一个字符串作为参数。这些方法首先在web application的根路径下找寻符合参数的路径，如果找不到，会到WEB-INF/lib下的jar中的META-INF/resources路径下查找。

​	这些方法不会去寻找动态文件，比如jsp。

### request

​	servlet规范用request对象封装http请求的所有数据。并通过一定的方法来访问请求消息数据。

​	request message中有三类数据，请求行，请求头，请求体。request对象有三类数据结构用来封装request message中的数据，parameter，header，body。他们的对应关系如下：

​	请求行中的url结构如下：

​		\<scheme>://\<user>:\<password>@\<host>:\<port>/\<path>;\<params>?\<query>#\<frag>

​	其中qury部分会被封装到parameter数据集里。初次之外，http或者https的post请求中，如果请求头content type的值是 application/x-www-form-urlencoded，当调用了getParameter或相关方法时，该请求体中的数据会被放入parameter中，不可以通过request输入流获取，如果没有调用过，那么请求体里面的依然可以通过request对象的输入流获取。

​	另外一点，get请求中的路径参数没有被封装到parameter中，而是需要通过getRequestURI或getPathInfo方法访问。

​	如果请求类型是multipart/form-data，且servlet上有@MultipartConfig注解，那么可以通过getParts()或getPart(String name).访问part的内容，这两个方法都会返回Part类型，通过part对象，可以访问part中头 ，内容类型，也可以通过getInputStream内容返回part中的内容。对于使用form-data作为Content-Disposition但没有文件名的part，还可以使用part的名称通过HttpServletRequest上的getParameter / getParameterValues方法获得部件的字符串值。

​	attribute是request扩展的一个数据集，它是用来在服务端进行数据传输的，只有在服务端设置，才能在其他地方访问它。

​	servlet规范将request message中的请求头内容封装到了request对象的header数据集中，通过getHeader getHeaders
■ getHeaderNames方法可以访问这些对象。

​	在http请求中，请求行中的url结构如下：

​		\<scheme>://\<user>:\<password>@\<host>:\<port>/\<path>;\<params>?\<query>#\<frag>

​	request对象对url的结构中的path部分做了处理，将\<path>分为了三个部分，Context Path，Servlet path，PathInfo。.request到servlet的映射过程如下：

1. request请求到达servlet容器，servlet容器取出url中的path部分，servlet容器根据配置信息，将path和各个servlet context的配置前缀做对比，如果成功，这个servlet context的配置前缀就是context path；如果匹配失败，将该请求匹配到默认的servlet context，context path就是空字符串。
2. 匹配到一个servlet context上后，去除context path，我们path剩余部分继续和servlet的匹配路径做匹配。这一部分就是servlet path，如果servlet匹配路径时/*和“”，那么servlet path时空字符串。
3. path去除context path和servlet path只有即使pathinfo了。它要么时null，要么就是一个/开头的字符串。

### response

​	respone对象封装了server向client返回的所有信息，client一般通过response的响应头和响应体获取响应信息。

​	我们知道http响应消息分为响应行，响应头，响应体三部分。不像请求体时可选的，响应体时必须要有的（header方法除外），响应消息大部分都是通过响应体来传递的。而无论是响应头还是响应体，我们都是通过流的方式取读取和写入的。在server向response写入请求体时，先写入response中的一个buffer中（为了效率），这个buffer的一些参数可以通过一些方法来控制。

​	当buffer满了以后，或者调用flush方法，response方法会将buffer中数据传递给客户端，这时候，response的状态就是已提交状态，就不能reset buffer了。特别的，当response为close状态时，buffer中的内容会立即返回给客户端。以下动作会将response状态改为close。

- servlet的service方法运行结束

  - 输出内容达到ContentLength的大小。
  - sendErr方法被调用
  - sendRedirect方法被调用

  - 异步响应时，complete方法被调用。

## 	参考资料：

《servlet3.0规范》

[浏览器对form表单的处理方式](https://www.cnblogs.com/ygunoil/p/12069374.html)