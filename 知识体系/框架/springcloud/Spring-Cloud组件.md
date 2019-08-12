# Spring-Cloud组件：eureka

### 服务治理需要解决的问题

一个相关的功能形成一个模块，即一个微服务，以一个进程的形式在电脑上运行；模块之间通过restful进行交互。但是当模块过多，即服务过多的时候，就会衍生出一系列其他问题。

- 服务的注册与发现：正常的服务调用，通常直接以IP：PORT方式调用，这样一来，更换IP，增加服务，增加实例就 会很麻烦。

  那么我们是不是可以以一种类似spring IOC的方式，在每个服务内内置一个组件（我们暂且叫它客户端）,当启动一个实例的时候，客户端收集服务示例的基本信息（实例ID，实例名称，ip，端口，服务版本号、通讯协议等），将其（服务的基本信息）注册到一个叫做服务管理中心的东西上。其中服务实例ID是服务实例的唯一标识，服务名称是服务的唯一标识，每一个服务可以启动多个服务实例，类似tomcat多实例部署。

  同时，客户端从服务端拉去其他客户端注册到服务注册中心的服务。这样一来，当我们需要调用其他服务的时候，我们可以通过服务名找寻服务的所有实例，然后利用负载均衡算法选用一个实例，将服务名换成选中实例的ip和端口等信息，调用该服务。

  每一个服务配置一个客户端，服务每启动一个服务，由配套的客户端将实例信息注册到服务注册中心，然后从服务注册中心拉取其他资料。这样，服务的添加就不会影响到其他服务了。

  EurekaServer实现的就是服务注册中心的功能，EurekaClient实现的就是客户端的功能。

- 服务启动状态的监听：有了服务的注册和发现，还有一个问题，就是服务的删除，或者当一个服务宕机的时候，我们应该怎么处理，如果服务注册中心还依然维护着这个服务信息，当调用到这个服务的时候，就会出现服务调用失败的情况。

  所以需要服务端实现一个监控功能，时刻监控服务的状态，当服务端宕机的时候，剔除这个服务实例。既然每个服务都有一个配套的客户端，客户端和服务就是同生共死的关系，我们可以通过监听客户端的状态来实现服务状态的监听，这样用来就实现了于业务服务的解耦。至于监控的实现，可以使用心跳检测机制，

  EurekaClient和EurekaServer通过心跳检测机制实现对服务状态的监听

- 负载均衡：服务之间调用的时候，如果启动多个实例，可能就会出现负载不均衡的问题。前面已经说过，服务之间的调用是由客户端拉取服务注册中心的服务信息，由客户端取调用的，那么我们就可以在客户端调用其他服务的时候增加一个负载均衡的功能呢。

  springcloud借助ribbon实现eurekaClient的负载均衡

- 降级，熔断：多个微服务之间调用可以形成一个调用链，如果调用联中的其中一环出问题，调用失败，那么所有的请求就会在问题服务的上一个节点报错。我们需要一个更加柔和的方法，比如返回一个固定的信息告诉你上一个服务，我挂了，信息的返回我们可以通过调用一个固定的方法方法实现，我们暂且叫这个方法为fallBack。但是如果一个服务一直出问题，每次都需要调用到挂掉服务的上一个服务才会返回失败信息，服务调用失败，如果挂掉服务处在调用链的深处，比如说第五个环节，那么前四个环节的调用就是浪费。我们需要一种功能，当调用要给服务总是失败导致fallBack方法被调用，我们就可以略过前四个服务，直接调用fallBack方法，从而节省资源。

  hystrix就是处理服务降级，熔断功能的组件。

- 网关，限流，过滤：以上服务间的调用，我们只是eureka已注册服务的相互调用，那么如果我们由一个非eureka注册服务，或者是前后端分离的项目，前端调用eureka服务，那么我们需要怎么调用呢？通过服务名肯定是不行的，应为eureka外部客户端无法解析服务名。如果直接以IP：PORT的方式调用，那么就会很冗余。

  我们可以再增加一个客户端，代理其他所有注册的服务，作为接口暴露出去，我们暂且叫他路由。当外部需要调用服务的时候，统一调用路由，然后由路由分发到各个具体的服务。这样像现实中的路由器一样，不仅可以实现路由功能，还可以实现过滤，限流等功能。

  springCloud集成了netflix的zuul实现这个功能。

  以上是我对springcloud的一些组件的理解，下面是我在理解过程中收集的一些资料整理而来（复制粘贴+我的整理）。

### eureka客户端和服务端关系示例图

![eureka服务治理](.\eureka服务治理.png)

### eureka客户端和服务端部署示例图

![server-client关系图](D:/Study/githup/helloWorld/%E7%9F%A5%E8%AF%86%E4%BD%93%E7%B3%BB/%E6%A1%86%E6%9E%B6/springcloud/server-client%E5%85%B3%E7%B3%BB%E5%9B%BE.png)

### 服务治理

在服务治理框架中，通常都会构建一个注册中心，每个服务单元向注册中心登记自己提供的服务，包括服务的主机与端口号、服务版本号、通讯协议等一些附加信息。注册中心按照服务名分类组织服务清单，同时还需要以心跳检测的方式去监测清单中的服务是否可用，若不可用的服务从服务清单中剔除，以达到排除故障服务的效果。

在服务治理框架下，服务间的调用不再通过指定具体的实例地址来实现，而是通过服务名发起请求调用实现。服务调用方通过服务名从服务注册中心的服务清单中获取服务实例的列表清单，通过指定的负载均衡策略取出一个服务实例位置来进行服务调用。

### Eureka服务治理

#### EurekaServer（待验证）

 Eureka服务端，即服务注册中心。它同其他服务注册中心一样，支持高可用配置。依托于强一致性提供良好的服务实例可用性，可以应对多种不同的故障场景。

Eureka服务端支持集群模式部署，当集群中有分片（我的理解是一个服务节点，不太理解分片的概念）发生故障的时候，Eureka会自动转入自我保护模式。它允许在分片发生故障的时候继续提供服务的发现和注册，当故障分配恢复时，集群中的其他分片会把他们的状态再次同步回来。集群中的的不同服务注册中心通过异步模式互相复制各自的状态，这也意味着在给定的时间点每个实例关于所有服务的状态可能存在不一致的现象。

服务提供者在启动的时候会通过REST请求的方式将自己注册到Eureka Server上，同时带上自身服务的一些元数据信息。Eureka Server接收到这个Rest请求之后，将元数据信息存储在一个双层结构的Map中，其中第一层的key是服务名。第二层的key 是具体服务的实例名。

从eureka服务治理体系架构图中可以看到，不同的服务提供者可以注册在不同的服务注册中心上，它们的信息被不同的服务注册中心维护。同时，由于多个服务注册中心互相注册为服务，当服务提供者发送注册请求到一个服务注册中心时，它会将该请求转发给集群中相连的其他注册中心，从而实现服务注册中心之间的服务同步。通过服务同步，提供者的服务信息就可以通过集群中的任意一个服务注册中心获得。

在系统运行过程中必然会面临关闭或重启服务的某个实例的情况，在服务关闭操作时，会触发一个服务下线的Rest服务请求给Eureka Server，告诉服务注册中心：“我要下线了。”服务端在接收到该请求后，将该服务状态置位下线（DOWN），并把该下线事件传播出去。

在eureka客户端和服务端部署示例图示例图中，server0/1/2/3/4/5为四个服务端，他们可以相互注册，例如server0和server2相互注册。相互注册的服务端可以相互同步客户端信息，所以server0/1/2/3/4/5都拥有clientA/B/C/D的信息，任一个客户端从注册的客户端拉去其他服务信息时，都可以得到全部的服务信息。

当一个Server解析配置的集群地址时，会过滤掉自身的地址，这样服务同步时就不需要同步自身了。我们配置多个Server时，不需要手动的排除Server自身的发现地址。

就像上图的Server 0,1,2的配置，每个节点可以都加上他们3个的服务发现地址，但他们在实际初始化时，每个Server里只会生成2个用于数据同步的内置Node，比如 Server 0 初始化时生成1和2的数据同步Node，类名叫 PeerEurekaNode。

#### EurekaClient

Eureka客户端，主要处理服务的注册和发现。客户端服务通过注册和参数配置的方式，嵌入在客户端应用程序的代码中。在应用程序启动时，Eureka客户端向服务注册中心注册自身提供的服务，并周期性的发送心跳来更新它的服务租约。同时，他也能从服务端查询当前注册的服务信息并把它们缓存到本地并周期行的刷新服务状态。

消费者服务启动时，会发送一个Rest请求给服务注册中心，来获取上面注册的服务清单。为了性能考虑，Eureka Server会维护一份只读的服务注册清单来返回给客户端，同时该缓存清单默认会每隔30秒更新一次。

Client是作为客户端，将自己注册到服务的，同时从服务端拉取其他客户端的信息，比如clientC会将信息注册到server3，同时从server3拉去其他服务的信息。客户端只会注册到一个服务端，即使配置了多个服务端，比如clientA只会将信息注册到server0或者server2中的一台。注册时，会先尝试向第一个服务端注册，注册失败尝试向第二个服务端注册，直至成功，全部失败报异常。

服务消费者在获取服务清单后，通过服务名可以获取具体提供服务的实例名和该实例的元数据信息。因为有这些服务实例的详细信息，所以客户端可以根据自己的需要决定具体调用哪个实例，在Ribbon中会默认采用轮询的方式进行调用，从而实现客户端的负载均衡。

每一个Eureka Server Node都内置了一个Eureka Client，也就是说一个Server Node节点可以接受其他Client的注册，也可以作为一个Client注册到其他Server上，被其他Client发现和调用。

客户端会将自己的信息注册到这四个server中的一个，，比如

### 服务端、客户端之间的注册、发现和同步

Server之间的数据同步和Server与Client间的数据交互使用的是同一个Http接口，比如注册，心跳，状态更新，关闭服务等等。只是Server与Server之间同步时，会有一个Header参数，**x-netflix-discovery-replication = true** ,Server通过这个标识来判断当前请求是来自Server还是来自Client。如果 x-netflix-discovery-replication不存在，则指明请求来自Client，Server在处理此请求时还会将请求广播给配置上的其他Server节点，在广播请求时，Header带上x-netflix-discovery-replication=true。当其他Server节点接受到此请求时，通过此Header参数判断是一个Server同步请求，因此只处理此请求，而不再广播。**Server之间的数据同步只传播一次**！！！

如上图所示，根据以上原则：

- ClientD如果选择和Server4交互，那么Server4将服务信息同步给Server0，ClientA能发现ClientD;
- ClientD如果选择和Server1交互，那么Server4将服务信息同步给Server0,2，ClientA，B都能发现ClientD;
- ClientC无论如何也不能发现ClientD
- ClientC因为只和Server3交互，当Server3宕机时，其就失去了与Server集群的联系，但因为其还保存有ClientA的服务信息，所以不影响其与ClientA的调用 ( 前提是ClientA正常与Server2交互，如果其一直与Server0交互，那么Server2不会传播ClientA的服务信息 )。但ClientA发生的变化其得不到通知，而且后续新增的其他服务也发现不了。
- 在Server3宕机时，Server2当时还保存着ClientC的服务信息，在那个时间，其他服务还是能通过Server2发现ClientC的，但是因为ClientC的不能续约，在90S后Server2将删除ClientC的服务信息。ClientC与服务集群基本隔离。

### 服务端的服务信息回收

Server每隔60S执行一次服务信息回收，移除那些心跳时间超时的。能够回收有3个前提：

1. 心跳信息超时，也就是回收时间距离上次心跳时间超过90S。
2. 开启了租约过期功能，默认是开启的。
3. 未触发自我保护机制。所谓的自我保护机制，指的是上一分钟内，服务实际发送心跳的总数超过预计总数的85%，可能近似理解为正常存活的Client超过85%。

我在上一篇关于Server端的定时任务里对自我保护机制有较详细的说明，这里就不再复述了。

那需要注意些什么？如果你的Client个数较少，比如就5个，或者说同一个Server对应的Client就5个，那么当其中的一个宕机了，1/5=20%，直接就触发了自我保护机制，宕机的服务信息会一直存在，不会被回收。对于这种情况，可以设置Server触发自我保护机制的临界值，renewalPercentThreshold = 0.85，默认是85%，可以修改成适当的值，比如0.5

### Server节点间的服务信息同步的流程

Server在初始化时，会根据配置信息生成与其他的Server同步的客户端。每当Server接收到Client的服务请求时，会先处理请求，然后将自身作为一个Client的角色，用相同的请求信息去请求配置里的那些Server节点（eureka服务端默认配置defalutZone为http://localhost:8761，如果你配置的不是8761，server端的配置就会请求不通报错，所以将自己作为服务端配置）。会将同步请求封装成一个Task，然后存入一个Queue中，Server定时的提取Queue里的任务，批量的处理它们。

也就是说Server之间的服务同步是异步执行的，而不像Zookeeper一样，每个操作都需要过半数的节点执行成功后才返回给Client。同时Server之间的同步只会传播一次，它们通过Header里的一个参数来表名是来自Client的请求还是Server的请求。如果是Server的请求，那么接收到此请求后不会再进行传播。就好像上图中的Server 0接收到Client A的服务请求，然后将此请求传播给Server 1，2，4。这3个Server接收到请求后，通过Header参数判断是Server间的数据同步，就不会再讲此请求传播给他们各自配置上的节点，比如Server 2不会传播给Server 3，也就时Client A的服务新不可能传播到Server 3上，Client C不可能发现Client A。

### region（地域）与zone（可用区）

region和zone（或者Availability Zone）均是AWS的概念。在非AWS环境下，我们可以简单地将region理解为地域，zone理解成机房。一个region可以包含多个zone，可以理解为一个地域内的多个不同的机房。不同地域的距离很远，一个地域的不同zone间距离往往较近，也可能在同一个机房内。

region可以通过配置文件进行配置，如果不配置，会默认使用us-east-1。同样Zone也可以配置，如果不配置，会默认使用defaultZone。

Eureka Server通过eureka.client.serviceUrl.defaultZone属性设置Eureka的服务注册中心的位置。

**指定region和zone的属性如下：**

（1）eureka.client.availabilityZones.myregion=myzone # myregion是region

（2）eureka.client.region=myregion

​     Ribbon的默认策略会优先访问通客户端处于同一个region中的服务端实例，只有当同一个zone中没有可用服务端实例的时候才会访问其他zone中的实例。所以通过zone属性的定义，配合实际部署的物理结构，我们就可以设计出应对区域性故障的容错集群。

### 安全验证

我们启动了Eureka Server，然后在浏览器中输入http://localhost:8761/后，直接回车，就进入了[**spring**](http://lib.csdn.net/base/javaee) cloud的服务治理页面，这么做在生产环境是极不安全的，下面，我们就给Eureka Server加上安全的用户认证.

（1）pom文件中引入依赖

~~~xml
<dependency> 

   <groupId>org.springframework.boot</groupId> 

   <artifactId>spring-boot-starter-security</artifactId>

</dependency> 
~~~



（2）serviceurl中加入安全校验信息

  ```properties
eureka.client.serviceUrl.defaultZone=http://<username>:<password>@${eureka.instance.hostname}:${server.port}/eureka/
  ```



### 配置详解

![eureka配置详解](.\eureka配置详解.png)

 在spring-cloud项目里面加入依赖：

  Eureka客户端：spring-cloud-starter-netflix-eureka-client

  Eureka服务端：spring-cloud-starter-netflix-eureka-server

eureka服务端项目里面加入以下配置：

~~~yml
server:
  port: 3000 #服务端占用的端口
eureka:
  server:
    enable-self-preservation: false  #关闭自我保护机制。默认情况下，如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障(比如网络故障或频繁的启动关闭客户端)，Eureka Server自动进入自我保护模式。不再剔除任何服务，当网络故障恢复后，该节点自动退出自我保护模式
    eviction-interval-timer-in-ms: 4000 #eureka server清理无效节点的时间间隔，默认60000毫秒，即60秒
    renewal-threshold-update-interval-ms: 15 * 60 * 1000 #阈值更新的时间间隔，单位为毫秒，默认为15 * 60 * 1000
    renewal-percent-threshold: 0.85 #阈值因子，默认是0.85，如果阈值比最小值大，则自我保护模式开启
    delta-retention-timer-interval-in-ms: 30000 #清理任务程序被唤醒的时间间隔，清理过期的增量信息，单位为毫秒，默认为30 * 1000
    
  instance:
    hostname: localhost
    instance-id: power-1 #此实例注册到eureka服务端的唯一的实例ID 
    prefer-ip-address: true #是否显示IP地址
    leaseRenewalIntervalInSeconds: 10 #eureka客户需要多长时间发送心跳给eureka服务器，表明它仍然活着,默认为30 秒 (与下面配置的单位都是秒)。每隔一秒续租一次
    leaseExpirationDurationInSeconds: 30 #Eureka服务器在接收到实例的最后一次发出的心跳后，需要等待多久才可以将此实例删除，默认为90秒。2s不续租说明我退房了


  client:
    registerWithEureka: false #是否要注册到其他Server上。如果我的Server上其实开放了一些Http接口供调用，那么就需要注册，这样其他的Client才能发现我的服务，才能通过RPC调用我提供的Http接口。如果我的Server没有提供对外Http接口，那么这个参数可以设置为false
    fetchRegistry: false  #是否需要拉取服务信息。和是否注册一样，如果我的Server需要调用其他的Client的Http接口，那么就需要获取相应的服务发现信息，这样才能正常的调用。同时这个参数还有一个重要的作用，就是决定**Server在初始化时是否立即全量同步其他节点的服务信息**！！！Server初始化时会先初始化其内置的Client。若配置了fetchRegistry=true，那么Client在初始化时会从其他Server全量拉取服务信息，放进Client容器中。Server在初始化时会尝试同步Client容器里的服务信息，如果fetchRegistry=false，服务信息不存在，只能被动的等其他Server节点以增量的形式同步过来(**Client在执行注册和心跳时对应的注册Server节点会广播此事件，同步给其他的Server节点。当其他Server节点还没有此服务信息时，改为注册此服务信息**)。当然正常的通过心跳来同步，最多也仅需要30S而已，是否需要设置此参数就看各自的需求了。 
	healthcheck:
      enabled: true #开启健康检查（需要spring-boot-starter-actuator依赖）如果需要更细粒度健康检查，可实现com.netflix.appinfo.HealthCheckHandler接口 。 EurekaHealthCheckHandler 已实现了该接口
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
 
spring:
  application:
    name: server-power #此实例注册到eureka服务端的name 多个实例可以共用一个名称，用于负载均衡


~~~

##  服务实例类配置

###  端点配置

eureka实例的状态页面和健康监控的url默认为spring boot actuator提供的/info端点和/health端点。我们必须确保Eureka客户端的/health端点在发送元数据的时候，是一个能够被注册中心访问到的地址，否则服务注册中心不会根据应用的健康检查来更改状态（仅当开启了healthcheck功能时，以该端点信息作为健康检查标准）。而如果/info端点不正确的话，会导致在Eureka面板中单击服务时，无法访问到服务实例提供的信息接口。

大多数情况下，我们不需要修改这个几个url配置。但是当应用不使用默认的上下文(context path或servlet path，比如配置server.servletPath=/test），或者管理终端路径（比如配置management.contextPath=/admin）时，我们需要修改健康检查和状态页的url地址信息。


 application.yml配置文件如下：

server.context-path=/helloeureka

//下面配置为相对路径，也支持配置成绝对路径，例如需要支持https

```properties
eureka.instance.health-check-url-path=${server.context-path}/health

eureka.instance.status-page-url-path=${server.context-path}/info
```

### 元数据

元数据是Eureka客户端在向服务注册中心发送注册请求时，用来描述自身服务信息的对象，其中包含了一些标准化的元数据，比如服务名称、实例名称、实例IP、实例端口等用于服务治理的重要信息；以及一些用于负载均衡策略或是其他特殊用途的自定义元数据信息。

我们可以通过eureka.instance.<properties>=<value>的格式对标准化元数据直接进行配置，其中<properties>就是EurekaInstanceConfigBean对象中的成员变量。而对于自定义元数据，可以通过eureka.instance.metadataMap.<key>=<value>的格式来进行配置。比如：

eureka.instance.metadataMap.zone=tianjin

//随机生成实例

eureka.instance.metadataMap.instanceId=${spring.application.name}:${random.value}

###  健康检测

默认情况下，Eureka中各个服务实例的健康检测并不是通过spring-boot-acturator模块的/health端点来实现的，而是依靠客户端心跳的方式来保持服务实例的存活。在Eureka的服务续约与剔除机制下，客户端的健康状态从注册到注册中心开始都会处于UP状态，除非心跳终止一段时间之后，服务注册中心将其剔除。默认的心跳实现方式可以有效检查客户端进程是否正常运作，但却无法保证客户端应用能够正常提供服务

在Spring Cloud Eureka中，可以把Eureka客户端的健康检测交给spring-boot-actuator模块的health端点，以实现更加全面的健康状态维护，设置方式如下：

（1）      在pom.xml中引入spring-boot-starter-actuator模块的依赖

（2）      在application.properties中增加参数配置eureka.client.healthcheck.enabled=true

###  其他配置

​      除了上述配置参数外，下面整理了一些EurekaInstanceConfigBean中定义的配置参数以及对应的说明和默认值，这些参数均以eureka.instance为前缀。

  ![其他配置](.\其他配置.png)

##  通讯协议

默认情况下，Eureka使用Jersey和XStream配合JSON作为Server与Client之间的通讯协议。也可以选择实现自己的协议来代替。

参考资料：

[**Spring Cloud Eureka详解**](https://blog.csdn.net/qq_38289534/article/details/82146675)

[**Eureka服务发现的常见问题(使用的注意事项)**]( https://blog.csdn.net/qq_38289534/article/details/82146939)

[Spring Cloud Eureka 自我保护机制](https://www.cnblogs.com/xishuai/p/spring-cloud-eureka-safe.html)