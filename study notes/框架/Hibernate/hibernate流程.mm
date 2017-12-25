<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1513920262603" ID="ID_1720885534" MODIFIED="1513927175229" STYLE="bubble" TEXT="hibernate&#x6d41;&#x7a0b;">
<node CREATED="1513920276265" ID="ID_1451622557" MODIFIED="1513927175229" POSITION="right" STYLE="bubble" TEXT="configuration cfg=new configuration()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#35835;&#21462;&#37197;&#32622;&#25991;&#20214;hibernate.properties
    </p>
    <p>
      &#26500;&#24314;configuration&#23545;&#35937;
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1513927145691" ID="ID_1831369092" MODIFIED="1513928550061" STYLE="bubble" TEXT="BootstrapServiceRegistryBuilder bsrb=new BootstrapServiceRegistryBuilder()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26500;&#24314;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513927368628" ID="ID_1492665275" MODIFIED="1513927428977" TEXT="BootstrapServiceRegistry  bsr=bsrb.build()"/>
<node CREATED="1513928521679" ID="ID_262780039" MODIFIED="1513928554050" TEXT="MetadataSources metadataSources = new MetadataSources( serviceRegistry )"/>
</node>
<node CREATED="1513920298079" ID="ID_697440072" MODIFIED="1513927175229" POSITION="right" STYLE="bubble" TEXT="cfg=cfg.configure()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#21152;&#36733;hibernate.cfg.xml&#37197;&#32622;&#25991;&#20214;&#65292;&#35206;&#30422;hibernate.properties&#21442;&#25968;&#20449;&#24687;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513920333021" ID="ID_1931458968" MODIFIED="1513927175229" POSITION="right" STYLE="bubble" TEXT="SessionFactory sessions=cfg.buildSessionFactory()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26500;&#24314;sessionfactory&#23545;&#35937;
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1513926912953" ID="ID_297143677" MODIFIED="1513927175229" POSITION="right" STYLE="bubble" TEXT="Session session=sessions.openSession()">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#26500;&#24314;session&#23545;&#35937;
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</map>
