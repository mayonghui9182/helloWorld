## IO流

### 源

​	在学习IO流之前，我们首先明白一个问题，计算机用到的数据都是存储在那里的？磁盘，设备，内存等可以i存储数据。我们要把数据写入到这些地方，或者从这些设备中读取数据时，都需要通过IO来实现。

​	在linux系统中，把所有的设备统成为文件，用文件描述符描述每一个设备，所以这里把所有的IO流源统称为文件。计算机存储的数据只能是二进制，所以这些文件中存储的数据都是二进制。我们将现实中的不同都存储到计算机中的文件中，有的数据比较适合二进制存储，比如图片和视频，读写的时候直接以二进制进行读写，然后将二进制数据进行展现即可，但是有的数据不适合二进制进行直接读写，比如字符（文字，标点符号等），所以在读写的过程中，需要对二进制进行处理。

### 读写方式

​	以二进制进行读写的二进制文件（图片，视频）时，可以使用便于二进制读写的一套API，InputStream/OutputStream及其子类。这套API读写的都是二进制。

​	特别的，当我们读写一些字符（文字，标点符号等），需要根据编码方式将字符转与对应的二进制进行转换，常见的编码方式有ISO8859-1，GBK，UTF-8等，详情请阅读相关文档进行了解，这里默认你知道这些编码方式。我们以ISO8859-1为例，ISO8859-1是以8bit，即一个字节为单位将二进制进行编码，将对应的字符与为二进制进行转换，是一种比较简单的编码方式，只能存储256个字符。

​	为了便于字符的读写，可以使用便于字符读写的一套API，Write/Reader及其子类，这套API在从文件种读取二进制的时候，不是以bit为单位进行读写的，而是根据编码规则确定读写单位，在从文件读写对应单位的字节的同时，进行编码转换。 

### 分类

​	根据流的源不同，java实现了几种基本IO，其他IO的实现都需要建立在它们的基础上。

- 从磁盘中独写，即文件

  1. FileInputStream/FileOutPutStream
  2. FileWriter/FileReader

- 从内存独写，在内存中进行读取数据的时候，一般是以数组作为源

  1. ByteArrayInputStream/ByteArrayOutputStream
  2. CharArrayWriter/CharArrayReader

- 从其他进行中进行读写

  1. PipterInputStram/PipeOutputStream
  2. PipeWriter/PipeReader

- 网卡读写，从Internet读取数据的时候，是通过socket的输入输出流进行读写网卡的数据

  

## 参考资料

[**The Java™ Tutorials**之Basic I/O ](https://docs.oracle.com/javase/tutorial/essential/io/streams.html)

[C/C++ I/O详解](https://blog.csdn.net/nbaDWde/article/details/80841674)

[linux下的硬连接和软连接](https://www.ibm.com/developerworks/cn/linux/l-cn-hardandsymb-links/index.html)

[Linux shell标准输入，标准输出，错误输出](https://www.cnblogs.com/itsharehome/p/8503206.html)

