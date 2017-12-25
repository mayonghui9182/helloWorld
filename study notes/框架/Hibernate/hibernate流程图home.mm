<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1513952953506" ID="ID_1749580747" MODIFIED="1514120216893" TEXT="hibernate&#x6d41;&#x7a0b;&#x56fe;home">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      hibernate&#30340;&#25972;&#20010;&#27969;&#31243;
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1513954039626" ID="ID_853336096" MODIFIED="1514128146348" POSITION="right" TEXT="Configuration cfg = new Configuration();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#21019;&#24314;&#19968;&#20010;configuration&#23545;&#35937;&#65292;&#35813;&#23545;&#35937;&#21253;&#25324;hibernate&#30340;&#25152;&#26377;&#37197;&#32622;&#20449;&#24687;&#65292;&#36825;&#20123;&#37197;&#32622;&#20449;&#24687;&#20174;&#37197;&#32622;&#25991;&#20214;&#35835;&#21462;&#20986;&#26469;&#65292;&#20026;&#21019;&#24314;sessionFactory&#20570;&#20934;&#22791;&#12290;
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1513954418810" FOLDED="true" ID="ID_121886390" MODIFIED="1514124040770" TEXT="bootstrapServiceRegistry BSR=new BootstrapServiceRegistryBuilder().build()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://blog.csdn.net/ttff2234/article/details/8545405
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1513955388941" ID="ID_487092678" MODIFIED="1513956303900" TEXT="&#x5efa;&#x7acb;final&#x5bf9;&#x8c61;HashSet&lt;ClassLoader&gt;()&#xff1a;CLs">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#19968;&#20010;classloader&#30340;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1513955436577" ID="ID_501827092" MODIFIED="1514120343289" TEXT="&#x5efa;&#x7acb;final&#x5bf9;&#x8c61;new ClassLoaderServiceImpl( CLs )&#xff1a;CLService">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#19968;&#20010;&#23553;&#35013;&#20102;classload&#20855;&#20307;&#32454;&#33410;&#30340;&#31867;&#12290;
    </p>
    <p>
      &#23427;&#26377;&#19968;&#20010;linkhashset&lt;classloader&gt;&#65306;&#25353;&#39034;&#24207;&#20998;&#21035;&#23384;&#20648;&#20102;custom classloader&#12289;hibernate&#30340;classloader&#12289;tccl&#65288;&#24403;&#21069;&#32447;&#31243;&#65289;&#30340;classloader&#12289;system classloader
    </p>
    <p>
      classloader service&#21152;&#36733;&#36164;&#28304;&#23601;&#26159;&#29992;&#25968;&#32452;&#20013;&#30340;classloader&#21152;&#36733;&#36164;&#28304;&#12290;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513955962352" ID="ID_291823532" MODIFIED="1514121347591" TEXT="&#x5efa;&#x7acb;final&#x5bf9;&#x8c61; IS = new IntegratorServiceImpl(providedIntegrators,CLService)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#29992;&#26469;&#25972;&#21512;hibernate&#30340;&#25193;&#23637;&#26381;&#21153;&#65288;Hibernate Envers / Validator / Search &#25110;&#20854;&#23427;&#31532;&#19977;&#26041;&#30340;hibernate&#25193;&#23637;&#27169;&#22359;&#65289;&#12290;
    </p>
    <p>
      &#21487;&#20197;&#36890;&#36807;ServiceLoader &#26631;&#20934;&#26426;&#21046;&#21152;&#36733;&#65292;&#20063;&#21487;&#20197;BootstrapServiceRegistryBuilder.applyIntegrator&#26041;&#27861;&#21152;&#36733;
    </p>
    <p>
      
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513956628688" ID="ID_1266869543" MODIFIED="1514121491772" TEXT="&#x5efa;&#x7acb;&#x5c5e;&#x6027;&#x67e5;&#x627e;&#x7b56;&#x7565;&#x5bf9;&#x8c61;strategySelectorBuilder.buildSelector( CLService )&#xff1a;SS">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font color="rgb(69, 69, 69)" face="PingFang SC, Microsoft YaHei, SimHei, Arial, SimSun" size="16px">&#37197;&#32622;&#20449;&#24687;&#26597;&#25214;&#31574;&#30053;&#65306;&#35753;&#23646;&#24615;&#37197;&#32622;&#26356;&#28789;&#27963;&#12290;&#21487;&#20197;&#37197;&#32622;&#20840;&#31216;&#12289;&#31616;&#31216;&#31561;&#12290;</font>
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1514121389155" ID="ID_1114658648" MODIFIED="1514121582994" TEXT="&#x5efa;&#x7acb;&#x8fd4;&#x56de;&#x5bf9;&#x8c61;new BootstrapServiceRegistryImpl(autoCloseRegistry,CLService,SS,IS)"/>
</node>
<node CREATED="1514124591850" ID="ID_929662515" MODIFIED="1514124604888" TEXT="this.bootstrapServiceRegistry = BSR;"/>
<node CREATED="1513954444830" ID="ID_1530368230" MODIFIED="1514124624787" TEXT="this.metadataSources = new MetadataSources( BSR )">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#29992;&#26469;&#21152;&#36733;Mapping&#37197;&#32622;&#25991;&#20214;
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1514121773113" ID="ID_1181281701" MODIFIED="1514121786878" TEXT="this.serviceRegistry = SR;"/>
<node CREATED="1514121773116" ID="ID_1967249483" MODIFIED="1514121790310" TEXT="this.xmlMappingBinderAccess = new XmlMappingBinderAccess( SR );">
<node CREATED="1514123304876" ID="ID_1130304162" MODIFIED="1514123331196" TEXT="this.classLoaderService = SR.getService( ClassLoaderService.class );"/>
<node CREATED="1514123304877" ID="ID_1647374294" MODIFIED="1514123708306" TEXT="this.mappingBinder = new MappingBinder(SR.getService( ClassLoaderService.class ), true );">
<node CREATED="1514123593254" ID="ID_1924545935" MODIFIED="1514123593254" TEXT="this.validateXml = validateXml;"/>
<node CREATED="1514123593251" ID="ID_1492291678" MODIFIED="1514123614871" TEXT="this.xmlResourceResolver = new LocalXmlResourceResolver( CLS );">
<node CREATED="1514123769905" ID="ID_389846" MODIFIED="1514123777786" TEXT="this.classLoaderService = CLS;"/>
</node>
</node>
</node>
</node>
<node COLOR="#006699" CREATED="1513954483136" ID="ID_4539351" MODIFIED="1514124908654" STYLE="fork" TEXT="reset() ">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      configuration&#23646;&#24615;&#30340;&#21021;&#22987;&#21270;&#24037;&#20316;&#12290;
    </p>
  </body>
</html>
</richcontent>
<edge COLOR="#808080" STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="12"/>
<node CREATED="1513954483152" ID="ID_452412660" MODIFIED="1514124827287" TEXT="this.INS = new ImplicitNamingStrategyJpaCompliantImpl();"/>
<node CREATED="1513954483152" ID="ID_247493381" MODIFIED="1514124827287" TEXT="this.PNS = new PhysicalNamingStrategyStandardImpl();"/>
<node CREATED="1513954483152" ID="ID_246592942" MODIFIED="1514124827287" TEXT="this.namedQueries = new HashMap&lt;String,NamedQueryDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_569730035" MODIFIED="1514124827287" TEXT="this.namedSqlQueries = new HashMap&lt;String,NamedSQLQueryDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_1459478139" MODIFIED="1514124827287" TEXT="this.sqlResultSetMappings = new HashMap&lt;String, ResultSetMappingDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_193055241" MODIFIED="1514124827287" TEXT="this.namedEntityGraphMap = new HashMap&lt;String, NamedEntityGraphDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_1866342065" MODIFIED="1514124827287" TEXT="this.namedProcedureCallMap = new HashMap&lt;String, NamedProcedureCallDefinition&gt;(  );"/>
<node CREATED="1513954483167" FOLDED="true" ID="ID_749027925" MODIFIED="1514128200354" TEXT="this.SSRB = new StandardServiceRegistryBuilder( BSR );">
<node CREATED="1514124422967" ID="ID_1157161739" MODIFIED="1514125422172" TEXT="this.settings = Environment.getProperties();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Environment.getProperties()&#38745;&#24577;&#26041;&#27861;&#36820;&#22238;hibernate.properties&#21644;system&#65288;JVM&#65289;&#30340;&#37197;&#32622;&#20449;&#24687;&#12290;
    </p>
    <p>
      &#35775;&#38382;Environment&#30340;&#38745;&#24577;&#26041;&#27861;&#65292;&#23548;&#33268;Environment&#30340;&#21021;&#22987;&#21270;&#25805;&#20316;&#65292; Environment&#21021;&#22987;&#21270;&#26102;&#20250;&#35835;&#21462;hibernate.properties&#25991;&#20214;&#21644;system&#65288;JVM&#65289;&#30340;&#23646;&#24615;&#12290;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1514124422969" ID="ID_683201876" MODIFIED="1514125434966" TEXT="this.bootstrapServiceRegistry = BSR;"/>
<node CREATED="1514124422971" ID="ID_1333570099" MODIFIED="1514128159704" TEXT="this.configLoader = new ConfigLoader( BSR );">
<node CREATED="1514128162055" ID="ID_828508191" MODIFIED="1514128172639" TEXT="this.bootstrapServiceRegistry = BSR;"/>
</node>
<node CREATED="1514124422972" ID="ID_605654942" MODIFIED="1514125769977" TEXT="this.aggregatedCfgXml =LoadedConfig.baseline();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      LoadedConfig.baseline()&#23601;&#26159;&#21021;&#22987;&#21270;&#19968;&#20010;new LoadedConfig( null )&#65292;&#20182;&#23601;&#26159;&#19968;&#20010;LoadedConfig&#23545;&#35937;&#12290;
    </p>
    <p>
      LoadedConfig&#23601;&#26159;&#23384;&#20648;&#21152;&#36733;&#21518;&#30340;&#37197;&#32622;&#20449;&#24687;&#30340;&#12290;
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
<node CREATED="1513954483167" ID="ID_897131090" MODIFIED="1514127994215" TEXT="entityTuplizerFactory = new EntityTuplizerFactory();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26410;&#30693;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513954483167" ID="ID_1422178651" MODIFIED="1514127024991" TEXT="interceptor = new EmptyInterceptor();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#19968;&#20010;&#31354;&#30340;&#25318;&#25130;&#22120;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513954483167" ID="ID_571175038" MODIFIED="1514128040034" TEXT="this.properties = new Properties(  );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26032;&#24314;&#31435;&#19968;&#20010;properties&#65292;&#29992;&#26469;&#23384;&#20648;hibernate&#23646;&#24615;&#12290;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513954483167" ID="ID_473441224" MODIFIED="1514128082964" TEXT="properties.putAll( SSRB.getSettings());">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;SSRB&#36890;&#36807;Environment&#24471;&#21040;&#30340;&#23646;&#24615;&#36171;&#20540;&#32473;properties
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
<node CREATED="1513954039626" ID="ID_773842602" MODIFIED="1513954039626" POSITION="right" TEXT="cfg=cfg.configure();"/>
<node CREATED="1513954039626" ID="ID_748213782" MODIFIED="1513954039626" POSITION="right" TEXT="SessionFactory sessions=cfg.buildSessionFactory();"/>
<node CREATED="1513954039626" ID="ID_372352222" MODIFIED="1513954039626" POSITION="right" TEXT="Session session = sessions.openSession();"/>
<node CREATED="1513954039626" ID="ID_850919563" MODIFIED="1513954039626" POSITION="right" TEXT="session.close();"/>
</node>
</map>
