<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1513952953506" ID="ID_1749580747" MODIFIED="1514171530160" TEXT="hibernate&#x6d41;&#x7a0b;&#x56fe;home">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      hibernate&#30340;&#25972;&#20010;&#27969;&#31243;
    </p>
  </body>
</html></richcontent>
<node CREATED="1513954039626" ID="ID_853336096" MODIFIED="1514196876034" POSITION="right" TEXT="Configuration cfg = new Configuration();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#21019;&#24314;&#19968;&#20010;configuration&#23545;&#35937;&#65292;&#35813;&#23545;&#35937;&#21253;&#25324;hibernate&#30340;&#25152;&#26377;&#37197;&#32622;&#20449;&#24687;&#65292;&#36825;&#20123;&#37197;&#32622;&#20449;&#24687;&#20174;&#37197;&#32622;&#25991;&#20214;&#35835;&#21462;&#20986;&#26469;&#65292;&#20026;&#21019;&#24314;sessionFactory&#20570;&#20934;&#22791;&#12290;
    </p>
  </body>
</html></richcontent>
<node CREATED="1513954418810" ID="ID_121886390" MODIFIED="1514196904590" TEXT="bootstrapServiceRegistry BSR=new BootstrapServiceRegistryBuilder().build()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://blog.csdn.net/ttff2234/article/details/8545405
    </p>
  </body>
</html></richcontent>
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
</html></richcontent>
</node>
<node CREATED="1513955962352" ID="ID_291823532" MODIFIED="1514196994724" TEXT="&#x5efa;&#x7acb;final&#x5bf9;&#x8c61; IS = new IntegratorServiceImpl(providedIntegrators,CLService)">
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
      &#40664;&#35748;&#21152;&#36733;&#30340;&#65306;BeanValidationIntegrator&#12289;JaccIntegrator&#12289;CollectionCacheInvalidator
    </p>
  </body>
</html></richcontent>
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
</html></richcontent>
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
</html></richcontent>
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
<node COLOR="#006699" CREATED="1513954483136" ID="ID_4539351" MODIFIED="1514171418680" STYLE="fork" TEXT="reset() ">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      configuration&#23646;&#24615;&#30340;&#21021;&#22987;&#21270;&#24037;&#20316;&#12290;
    </p>
  </body>
</html></richcontent>
<edge COLOR="#808080" STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="12"/>
<node CREATED="1513954483152" ID="ID_452412660" MODIFIED="1514124827287" TEXT="this.INS = new ImplicitNamingStrategyJpaCompliantImpl();"/>
<node CREATED="1513954483152" ID="ID_247493381" MODIFIED="1514124827287" TEXT="this.PNS = new PhysicalNamingStrategyStandardImpl();"/>
<node CREATED="1513954483152" ID="ID_246592942" MODIFIED="1514124827287" TEXT="this.namedQueries = new HashMap&lt;String,NamedQueryDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_569730035" MODIFIED="1514124827287" TEXT="this.namedSqlQueries = new HashMap&lt;String,NamedSQLQueryDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_1459478139" MODIFIED="1514124827287" TEXT="this.sqlResultSetMappings = new HashMap&lt;String, ResultSetMappingDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_193055241" MODIFIED="1514124827287" TEXT="this.namedEntityGraphMap = new HashMap&lt;String, NamedEntityGraphDefinition&gt;();"/>
<node CREATED="1513954483152" ID="ID_1866342065" MODIFIED="1514124827287" TEXT="this.namedProcedureCallMap = new HashMap&lt;String, NamedProcedureCallDefinition&gt;(  );"/>
<node CREATED="1513954483167" ID="ID_749027925" MODIFIED="1514171538618" TEXT="this.SSRB = new StandardServiceRegistryBuilder( BSR );">
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
</html></richcontent>
</node>
<node CREATED="1514124422969" ID="ID_683201876" MODIFIED="1514125434966" TEXT="this.bootstrapServiceRegistry = BSR;"/>
<node CREATED="1514124422971" ID="ID_1333570099" MODIFIED="1514172033346" TEXT="this.configLoader = new ConfigLoader( BSR );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#22312;&#21021;&#22987;&#21270;&#26102;&#20250;&#21021;&#22987;&#21270;jaxbProcessorHolder&#12290;
    </p>
  </body>
</html></richcontent>
<node CREATED="1514128162055" ID="ID_828508191" MODIFIED="1514128172639" TEXT="this.bootstrapServiceRegistry = BSR;"/>
<node CREATED="1514171799941" ID="ID_673390077" MODIFIED="1514173600135" TEXT="this.jaxbProcessorHolder=new ValueHolder&lt;JaxbCfgProcessor&gt;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      new ValueHolder&lt;JaxbCfgProcessor&gt;&#26159;JaxbCfgProcessor&#30340;&#29305;&#27530;&#21333;&#20363;&#24037;&#21378;&#65292;JaxbCfgProcessor&#22312;ValueHolder&#26500;&#36896;&#26102;<font color="#ff3333">&#36171;&#20540;</font>&#65292;&#25110;&#32773;&#22312;&#31532;&#19968;&#27425;getValue&#26102;&#21021;&#22987;&#21270;&#20540;&#65288;&#36825;&#26159;&#38656;&#35201;&#22312;ValueHolder&#26500;&#36896;&#26102;&#20256;&#20837;&#19968;&#20010;&#21021;&#22987;&#21270;&#22120;&#65289;&#12290;
    </p>
    <p>
      jaxbProcessorHolder&#20540;&#22312;&#21021;&#22987;&#21270;&#26102;&#21021;&#22987;&#21270;&#12290;
    </p>
    <p>
      JaxbCfgProcessor&#30340;&#21021;&#22987;&#21270;&#65306;
    </p>
    <p>
      new JaxbCfgProcessor( bootstrapServiceRegistry.getService( ClassLoaderService.class ) );
    </p>
  </body>
</html></richcontent>
<node CREATED="1514173543265" ID="ID_1933195616" MODIFIED="1514173543265" TEXT="this.xmlResourceResolver = new LocalXmlResourceResolver( classLoaderService );"/>
<node CREATED="1514173543254" ID="ID_355609808" MODIFIED="1514173543254" TEXT="this.classLoaderService = classLoaderService;"/>
</node>
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
</html></richcontent>
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
</html></richcontent>
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
</html></richcontent>
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
</html></richcontent>
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
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1513954039626" FOLDED="true" ID="ID_773842602" MODIFIED="1514191410782" POSITION="right" TEXT="cfg=cfg.configure();">
<node CREATED="1514171626634" ID="ID_1655644979" MODIFIED="1514171647381" TEXT="SSRB.configure( resource );">
<node CREATED="1514171675300" ID="ID_1005087860" MODIFIED="1514172052681" TEXT="loadedConfig=configLoader.loadConfigXmlResource( resource)">
<node CREATED="1514172156080" ID="ID_562471576" MODIFIED="1514172411671" TEXT="in = BSR.getService( CLS.class ).locateResourceStream( Resource )">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#33719;&#24471;&#20027;&#37197;&#32622;&#25991;&#20214;&#30340;&#36755;&#20837;&#27969;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1514172270840" ID="ID_331592174" MODIFIED="1514191052490" TEXT="jaxbCfg=jaxbProcessorHolder.getValue().unmarshal(in,new Origin(SourceType.RESOURCE,Resource)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#21033;&#29992;jaxbProcessor&#25226;&#20027;&#37197;&#32622;&#25991;&#20214;&#35299;&#26512;&#20026;JaxbCfgHibernateConfiguration&#23545;&#35937;jaxbCfg&#12290;
    </p>
    <p>
      jaxbProcessor&#21033;&#29992;stax&#30340;&#22522;&#20110;&#36845;&#20195;&#22120;&#30340;api&#23545;xml&#36827;&#34892;&#35299;&#26512;&#12290;&#20808;&#23558;xml&#35299;&#26512;&#25104;JaxbCfgHibernateConfiguration&#23545;&#35937;&#12290;
    </p>
    <p>
      JaxbCfgHibernateConfiguration&#23384;&#20648;&#26377;cfg.xml&#30340;&#25152;&#26377;&#20449;&#24687;&#65288;properties&#12289;mapping&#31561;&#65289;&#12290;
    </p>
    <p>
      &#28982;&#21518;&#25226;JaxbCfgHibernateConfiguration&#23545;&#35937;&#36716;&#25442;&#20026;loadedconfig&#23545;&#35937;&#12290;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1514172373582" ID="ID_1329613351" MODIFIED="1514172500311" TEXT="LoadedConfig.consume( jaxbCfg )">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#25226;jaxbCfg&#35299;&#26512;&#25104;LoadedConfig&#23545;&#35937;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1514171680722" ID="ID_1356405378" MODIFIED="1514191293261" TEXT="this.onfigure( loadedConfig)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23384;&#20648;&#35299;&#26512;&#20986;&#26469;&#30340;&#20449;&#24687;&#12290;
    </p>
  </body>
</html></richcontent>
<node CREATED="1514191200564" ID="ID_891576128" MODIFIED="1514191331254" TEXT="this.aggregatedCfgXml.merge( loadedConfig );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;&#21152;&#36733;&#30340;loadedConfig&#20449;&#24687;&#21512;&#24182;&#36827;&#20837;SSRB&#30340;loadedConfig&#37324;&#38754;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1514191200565" ID="ID_206365431" MODIFIED="1514191352700" TEXT="settings.putAll( loadedConfig.getConfigurationValues() );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;&#21152;&#36733;&#30340;properties&#20449;&#24687;&#21512;&#24182;&#36827;&#20837;SSRB&#30340;properties&#37324;&#38754;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1514171631368" ID="ID_1033730799" MODIFIED="1514191395111" TEXT="properties.putAll(SSRB.getSettings() );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;&#21152;&#36733;&#30340;properties&#20449;&#24687;&#21512;&#24182;&#36827;&#20837;configuration&#30340;properties&#37324;&#38754;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1513954039626" ID="ID_748213782" MODIFIED="1513954039626" POSITION="right" TEXT="SessionFactory sessions=cfg.buildSessionFactory();">
<node CREATED="1514191605854" ID="ID_1800285776" MODIFIED="1514191684575" TEXT="this.SSRB.applySettings( properties );">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;config&#30340;properties&#21152;&#36733;&#36827;SSRB&#37324;&#38754;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1514191709210" ID="ID_507169394" MODIFIED="1514192160602" TEXT="StandardServiceRegistryImpl SSR=SSRB.build()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#24314;&#31435;&#19968;&#20010;&#26631;&#20934;&#30340;&#26381;&#21153;&#27880;&#20876;&#34920;&#23545;&#35937;
    </p>
  </body>
</html></richcontent>
<node CREATED="1514192218443" ID="ID_481286470" MODIFIED="1515206907730" TEXT="applyServiceContributingIntegrators();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26159;&#21542;&#21551;&#21160;StandardJaccServiceImpl extends jaccService
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1514192218443" ID="ID_1225082890" MODIFIED="1514196759898" TEXT="applyServiceContributors();">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26159;&#21542;&#21152;&#36733;ServiceContributor
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1514192218444" ID="ID_1278460011" MODIFIED="1514193584923" TEXT="final Map settingsCopy = new HashMap();&#xff08;&#x56db;&#x6b65;&#x8f6c;&#x5230;&#x5907;&#x6ce8;&#xff09;">
<richcontent TYPE="NOTE">&lt;html&gt;
  &lt;head&gt;
    
    
  &lt;/head&gt;
  &lt;body&gt;
    &lt;p&gt;
      &amp;#24314;&amp;#31435;&amp;#19968;&amp;#20010;map&amp;#23545;&amp;#35937;&amp;#65292;&amp;#32531;&amp;#23384;&amp;#25152;&amp;#26377;&amp;#30340;properties&amp;#21644;loadedconfig&amp;#65292;&amp;#24182;&amp;#23545;map&amp;#36827;&amp;#34892;&amp;#39564;&amp;#35777;&amp;#12289;&amp;#22788;&amp;#29702;&amp;#12290;
    &lt;/p&gt;
    &lt;p&gt;
      settingsCopy.putAll( settings );//&amp;#23384;&amp;#20648;properties
    &lt;/p&gt;
    &lt;p&gt;
      settingsCopy.put( CfgXmlAccessService.LOADED_CONFIG_KEY, 
      aggregatedCfgXml );&amp;#23384;&amp;#20648;loadedconfig
    &lt;/p&gt;
    &lt;p&gt;
      Environment.verifyProperties( settingsCopy );//&amp;#26816;&amp;#27979;&amp;#26159;&amp;#21542;&amp;#26377;&amp;#24223;&amp;#24323;&amp;#25110;&amp;#37325;&amp;#21629;&amp;#21517;&amp;#30340;&amp;#23646;&amp;#24615;&amp;#21517;&amp;#31216;&amp;#12290;
    &lt;/p&gt;
    &lt;p&gt;
      ConfigurationHelper.resolvePlaceHolders( settingsCopy 
      );//&amp;#22788;&amp;#29702;&amp;#23646;&amp;#24615;&amp;#23545;&amp;#35937;&amp;#20013;&amp;#25152;&amp;#26377;&amp;#26465;&amp;#30446;&amp;#30340;&amp;#20869;&amp;#25554;&amp;#22788;&amp;#29702;&amp;#12290;&amp;#20174;System.getProperty&amp;#20013;&amp;#21462;&amp;#20540;&lt;content ename=&quot;content&quot;&gt;&amp;#26367;&amp;#25442;$&amp;#21462;&amp;#20540;&amp;#31526;&amp;#21495;
    &lt;/p&gt;
    &lt;p&gt;
      
    &lt;/p&gt;
  &lt;/body&gt;
&lt;/html&gt;</richcontent>
</node>
<node CREATED="1514192357717" ID="ID_772558668" MODIFIED="1514192398721" TEXT="new SSR(autoCloseRegistry,BSR,initiators,providedServices,settingsCopy)"/>
</node>
<node CREATED="1514191633353" ID="ID_295302892" MODIFIED="1514192186147" TEXT="this.buildSessionFactory(SSR )">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#24314;&#31435;SessionFactory&#23545;&#35937;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1513954039626" ID="ID_372352222" MODIFIED="1513954039626" POSITION="right" TEXT="Session session = sessions.openSession();"/>
<node CREATED="1513954039626" ID="ID_850919563" MODIFIED="1513954039626" POSITION="right" TEXT="session.close();"/>
</node>
</map>
