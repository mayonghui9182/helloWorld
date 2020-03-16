### 互联网协议套件

​	互联网协议套件包含了一整套网络传输协议，是一个网络通信模型。因为它最早的两个协议是TCP和IP协议，所以又叫做TCP/IP协议族，简称TCP/IP。

​	TCP/IP提供了一整套的点对点链接的机制，将数据如何封装，定址，传输，路由以及目的地如何接收，都加以标准化。它将软件通信过程抽象为四层，采取协议堆栈的方式，分别实现不同的通信协议。协议族下的通信协议，依据器实现的功能，将其归属到四层中，常被视为简化的七层OSI模型。从下往上，四层分别为网络链接层，网络互连层，网络传输层，应用层。其中IP属于网络互连层，TCP属于网络传输层，HTTP属于应用层。

### IP协议

​	IP（Internet Protocol 网络协议）属于网络互联层，是用于分组交换数据网络的一种协议，它根据源主机和目的主机地址来传输数据。为了这个目的，IP协议定义了寻址方法和数据报的封装结构。而且，IP协议最为复杂的就是寻址和路由了，寻址就是如何将IP地址在分配给各个终端节点，以及如何划分和组合子网。所有网络端点都都需要路由，尤其是网际之间的路由，路由器通常用内部网关协议和外部网关协议决定怎么发送IP数据包的。

​	IP协议是无连接的协议，在报文交换的网络中主机传输数据之前，不需要与先前未曾通信的目的主机建立链接。而是将数据直接封装为数据包，再加上必要的地址信息，即目的主机的ip地址，沿着路由，发送到目的主机。分组（即数据包）根据路由算法，通过最佳路径，到达目标主机。但不是所有相同两个主机之间的分组（即便来自于同一消息的分组）一定沿着相同的路径传送。

​	IP协议只提供尽力传送，其服务失为不可靠的，是中无连接协议。它只是分组发送给了目标主机，它不保证数据能准确的传输。数据包在到达的时候可能已经损坏，顺序错乱（与其它一起传送的报文相比），产生冗余包，或者全部丢失。要保证这些功能，需要IP协议的上一层协议：TCP协议开实现。

### 	TCP协议

​	TCP（Transmission Control Protocol 传输控制协议）是一种面向链接的，可靠的，基于字节流的传输层通信协议。它处于网络传输，保证数据传输的准确性。

​	TCP将应用层要发送的数据分割成适当长度的报文段，然后传递给IP层，由IP层通过路由将数据传输给目标主机的IP层，最后到达目标主机的TCP层。为了保证数据传输的准确性，使用序号，对收到的TCP报文段进行排序以及检测重复的数据；使用校验和检测报文段的错误，即无错传输；使用确认和计时器来检测和纠正丢包或延时；流控制（Flow control）；拥塞控制（Congestion control）；丢失包的重传。最终保证发送数据的准确性。

### HTTP协议

​	HTTP（**H**yper**T**ext **T**ransfer **P**rotocol，超文本传输协议 ），是一种用于分布式，协作式和超媒体信息系统的应用层协议。HTTP是万维网通信的基础。它是一个客户端和服务端的请求和应答标准。HTTP协议基于TCP的一种协议，但是HTTP协议并没规定必须使用或它支持的层，HTTP协议假定下层协议时可靠传输的，所以HTTP协议可以基于任何可以提供这种保证的协议。

​	在用HTTP协议进行交互时，请求的一方我们成为client，响应的以放我们成为server。client向server发送请求，server上存储着一些资源，比如html文档或者图片，server会根据client的请求内容，向client响应对应的资源。client和server之间可能存在多个中间层，比如代理，隧道，网关等。

​	HTTP协议通过HTTP message进行信息交互，HTTP message分为两种，请求消息和响应消息，格式分别如下：

~~~xml
Here's the format for a request message:

<method> <request-URL> <version>
<headers>
 
<entity-body>
    
Here's the format for a response message (note that the syntax differs only in the start line): 

<version> <status> <reason-phrase>
<headers>
 
<entity-body>

~~~

### 参考资料：

[TCP/IP协议](https://zh.wikipedia.org/wiki/TCP/IP协议族)

[TCP协议](https://zh.wikipedia.org/wiki/传输控制协议)

[IP协议](https://zh.wikipedia.org/wiki/网际协议)

[HTTP协议](https://zh.wikipedia.org/wiki/超文本传输协议)