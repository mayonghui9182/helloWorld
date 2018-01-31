<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1509267871193" ID="ID_1635890055" MODIFIED="1509283547399" STYLE="bubble" TEXT="GIT&#x77e5;&#x8bc6;&#x7ed3;&#x6784;&#x56fe;">
<node CREATED="1509267937419" ID="ID_199549013" MODIFIED="1509283547398" POSITION="right" STYLE="bubble" TEXT="&#x547d;&#x4ee4;">
<edge COLOR="#808080" STYLE="bezier" WIDTH="thin"/>
<node CREATED="1509283386175" ID="ID_761893405" MODIFIED="1513127852489" STYLE="bubble" TEXT="&#x672c;&#x5730;&#x547d;&#x4ee4;local">
<node CREATED="1509283440679" ID="ID_214593636" MODIFIED="1509283547399" STYLE="bubble" TEXT="&#x521d;&#x59cb;&#x5316;&#x8bbe;&#x7f6e;">
<node CREATED="1509283482606" ID="ID_1557742894" MODIFIED="1509283547399" STYLE="bubble" TEXT="&#x521d;&#x59cb;&#x5316;&#x7248;&#x672c;&#x5e93;">
<node CREATED="1509283693564" ID="ID_1572179050" MODIFIED="1510931359495" TEXT="git init">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#21019;&#36896;&#19968;&#20010;&#31354;&#30340;&#20179;&#24211;&#65292;&#25110;&#32773;&#37325;&#26032;&#21021;&#22987;&#21270;&#19968;&#20010;&#24050;&#32463;&#23384;&#22312;&#30340;&#20179;&#24211;&#12290;&#20854;&#23454;&#23601;&#26159;&#29983;&#25104;&#19968;&#20010;.git&#25991;&#20214;&#22841;&#12290;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1509373512012" ID="ID_1352543521" MODIFIED="1509373516793" TEXT="&#x4e2d;&#x6587;&#x4e71;&#x7801;&#x95ee;&#x9898;">
<node CREATED="1509373521867" ID="ID_1373776847" MODIFIED="1510931359527" TEXT="windows&#x5e73;&#x53f0;&#x4e0b;git status&#x4e2d;&#x6587;&#x4e71;&#x7801; ">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font color="rgb(51, 51, 51)" face="Source Code Pro, monospace">git config --</font><font color="rgb(0, 0, 136)" face="Source Code Pro, monospace">global</font><font color="rgb(51, 51, 51)" face="Source Code Pro, monospace">&#160;core.quotepath </font><font color="rgb(0, 0, 136)" face="Source Code Pro, monospace">false</font>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509373580435" ID="ID_498816502" MODIFIED="1510931359542" TEXT="ls &#x4e2d;&#x6587;&#x4e71;&#x7801;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font color="rgb(0, 0, 136)" face="Source Code Pro, monospace">alias </font><font color="rgb(51, 51, 51)" face="Source Code Pro, monospace">ls=</font><font color="rgb(0, 136, 0)" face="Source Code Pro, monospace">'ls --show-control-chars --color=auto'</font>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290254690" ID="ID_515153474" MODIFIED="1513128153920" TEXT="&#x89e3;&#x51b3;git commit&#x65e0;&#x6cd5;&#x8f93;&#x5165;&#x4e2d;&#x6587;&#x6ce8;&#x91ca;&#x7684;&#x95ee;&#x9898;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#35299;&#20915;git commit&#26080;&#27861;&#36755;&#20837;&#20013;&#25991;&#27880;&#37322;&#30340;&#38382;&#39064;
    </p>
    <p>
      set output-meta on
    </p>
    <p>
      set convert-meta off
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290371064" ID="ID_1814174527" MODIFIED="1513128165843" TEXT="&#x89e3;&#x51b3;git log&#x547d;&#x4ee4;&#x4e2d;&#x6587;&#x6ce8;&#x91ca;&#x4e3a;&#x4e71;&#x7801;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#35299;&#20915;git log&#21629;&#20196;&#20013;&#25991;&#27880;&#37322;&#20026;&#20081;&#30721;&#65288;&#21482;bash&#25511;&#21046;&#21488;&#22909;&#29992;&#65289;
    </p>
    <p>
      &#26368;&#21518;&#36861;&#21152;
    </p>
    <p>
      export LESSCHARSET= iso8859
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1509283514150" ID="ID_1657378757" MODIFIED="1509283547399" STYLE="bubble" TEXT="&#x5168;&#x5c40;&#x53d8;&#x91cf;">
<node CREATED="1509284637008" ID="ID_935159230" MODIFIED="1510931359589" TEXT="git config ">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git config --global user.name &quot;Your Name&quot;&#160;&#160;&#35774;&#32622;&#21517;&#23383;
    </p>
    <p>
      git config --global user.email &quot;email@example.com&quot; &#35774;&#32622;email
    </p>
    <p>
      git config --global alias.st status &#20026;status&#21019;&#24314;&#21035;&#21517;st&#65292;--global&#38024;&#23545;&#24403;&#21069;&#29992;&#25143;&#36215;&#20316;&#29992;&#30340;&#65292;&#22914;&#26524;&#19981;&#21152;&#65292;&#37027;&#21482;&#38024;&#23545;&#24403;&#21069;&#30340;&#20179;&#24211;&#36215;&#20316;&#29992;&#12290;&#27599;&#20010;&#20179;&#24211;&#30340;Git&#37197;&#32622;&#25991;&#20214;&#37117;&#25918;&#22312;config&#25991;&#20214;&#20013;&#65292;&#37197;&#32622;&#21035;&#21517;&#20063;&#21487;&#20197;&#30452;&#25509;&#20462;&#25913;&#36825;&#20010;&#25991;&#20214;&#65292;&#22914;&#26524;&#25913;&#38169;&#20102;&#65292;&#21487;&#20197;&#21024;&#25481;&#25991;&#20214;&#37325;&#26032;&#36890;&#36807;&#21629;&#20196;&#37197;&#32622;&#12290;
    </p>
    <p>
      &#20854;&#20182;&#21442;&#32771;config&#25991;&#20214;&#27880;&#37322;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1509283442822" ID="ID_1230449827" MODIFIED="1509283547399" STYLE="bubble" TEXT="&#x65e5;&#x5e38;&#x64cd;&#x4f5c;">
<node CREATED="1509290875291" ID="ID_1553867490" MODIFIED="1513255885485" TEXT="&#x589e;&#x52a0;&#x6587;&#x4ef6;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git add filename&#22686;&#21152;&#29305;&#23450;&#25991;&#20214;
    </p>
    <p>
      git add -A &#22686;&#21152;&#25152;&#26377;&#25991;&#20214;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1515207814968" ID="ID_250088958" MODIFIED="1515207840894" TEXT="&#x5220;&#x9664;&#x6587;&#x4ef6;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git rm &#25991;&#20214;&#21517;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1515207843919" ID="ID_1146146905" MODIFIED="1515207866739" TEXT="&#x64a4;&#x9500;&#x4fee;&#x6539;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git checkout -- &#25991;&#20214;&#21517;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290960499" ID="ID_1949763886" MODIFIED="1513217670290" TEXT="&#x63d0;&#x4ea4;&#x6587;&#x4ef6;">
<richcontent TYPE="NOTE">&lt;html&gt;
  &lt;head&gt;
    
    
  &lt;/head&gt;
  &lt;body&gt;
    &lt;p&gt;
      git commit -m ''
    &lt;/p&gt;
    &lt;p&gt;
      &amp;#26412;&amp;#22320;&amp;#24037;&amp;#20316;&amp;#23436;&amp;#25104;&amp;#21518;&amp;#65292;commit&amp;#21487;&amp;#20197;&amp;#23558;&amp;#24403;&amp;#21069;&amp;#24037;&amp;#20316;&amp;#20445;&amp;#23384;&amp;#21040;&amp;#20179;&amp;#24211;&amp;#65292;&lt;content ename=&quot;content&quot;&gt;&amp;#27492;&amp;#26102;&amp;#20250;&amp;#21442;&amp;#25968;&amp;#19968;&amp;#20010;commit-id&amp;#12290;&amp;#36825;&amp;#26159;&amp;#33021;&amp;#21807;&amp;#19968;&amp;#26631;&amp;#35782;&amp;#19968;&amp;#20010;&amp;#29256;&amp;#26412;&amp;#30340;&amp;#24207;&amp;#21015;&amp;#21495;&amp;#12290;
    &lt;/p&gt;
    &lt;p&gt;
      
    &lt;/p&gt;
  &lt;/body&gt;
&lt;/html&gt;</richcontent>
</node>
<node CREATED="1513255717635" ID="ID_87454021" MODIFIED="1513255943414" TEXT="&#x65e5;&#x5fd7;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git reflog &#26597;&#30475;&#25552;&#20132;&#26085;&#24535;&#20449;&#24687;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290966418" ID="ID_611040369" MODIFIED="1513256049262" TEXT="&#x64a4;&#x9500;&#x63d0;&#x4ea4;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git reset --head commit-id
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1513256054727" ID="ID_1481929320" MODIFIED="1513260138185" TEXT="&#x5408;&#x5e76;&#x63d0;&#x4ea4;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git rebase -i head~11
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290998361" ID="ID_893339129" MODIFIED="1513217262168" TEXT="&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git&#22810;&#29256;&#26412;&#20998;&#25903;&#31649;&#29702;&#36719;&#20214;&#12290;
    </p>
    <p>
      &#20998;&#25903;&#26102;&#20026;&#20102;&#21333;&#29420;&#35760;&#24405;&#36719;&#20214;&#26576;&#20010;&#21457;&#24067;&#29256;&#26412;&#32780;&#23384;&#22312;&#30340;&#65292;
    </p>
  </body>
</html></richcontent>
<node CREATED="1510931765604" ID="ID_1508320211" MODIFIED="1513215536043" TEXT="&#x67e5;&#x770b;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git branch &#26597;&#30475;&#20998;&#25903;
    </p>
    <p>
      -a &#26597;&#30475;&#36828;&#31243;&#21644;&#26412;&#22320;&#25152;&#26377;&#20998;&#25903;
    </p>
    <p>
      -r &#26597;&#30475;&#36828;&#31243;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931792673" ID="ID_392021462" MODIFIED="1510931801456" TEXT="&#x521b;&#x5efa;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git branch &lt;name&gt; &#21019;&#24314;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931811117" ID="ID_519802001" MODIFIED="1510931830768" TEXT="&#x5207;&#x6362;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git checkout &lt;name&gt; &#20999;&#25442;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931831256" ID="ID_785382392" MODIFIED="1510931856570" TEXT="&#x521b;&#x5efa;&#x5e76;&#x5207;&#x6362;&#x5230;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git checkout -b &lt;name&gt; &#21019;&#24314;+&#20999;&#25442;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931857586" ID="ID_430092321" MODIFIED="1513217268262" TEXT="&#x5408;&#x5e76;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git merge &lt;name&gt; &#21512;&#24182;&#26576;&#20998;&#25903;&#21040;&#24403;&#21069;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931875743" ID="ID_1754331646" MODIFIED="1510931895684" TEXT="&#x5220;&#x9664;&#x5206;&#x652f;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git branch -d &lt;name&gt; &#21024;&#38500;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1510931896088" ID="ID_808831203" MODIFIED="1510931960716" TEXT="&#x67e5;&#x770b;&#x5206;&#x652f;&#x5408;&#x5e76;&#x56fe;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git log --graph &#21629;&#20196;&#21487;&#20197;&#30475;&#21040;&#20998;&#25903;&#21512;&#24182;&#22270;&#12290;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1509291009442" ID="ID_179319457" MODIFIED="1509291223231" TEXT="&#x51b2;&#x7a81;"/>
<node CREATED="1509291228159" ID="ID_768650347" MODIFIED="1509291234668" TEXT="&#x67e5;&#x770b;"/>
<node CREATED="1509291242574" ID="ID_1257443147" MODIFIED="1509291247538" TEXT="&#x5bfc;&#x51fa;"/>
</node>
</node>
<node CREATED="1509283405286" ID="ID_103762219" MODIFIED="1513217124675" STYLE="bubble" TEXT="&#x8fdc;&#x7a0b;&#x547d;&#x4ee4;remote">
<richcontent TYPE="NOTE">&lt;html&gt;
  &lt;head&gt;
    
    
  &lt;/head&gt;
  &lt;body&gt;
    &lt;p&gt;
      git&amp;#26159;&amp;#19968;&amp;#20010;&amp;#20998;&amp;#24067;&amp;#24335;&amp;#30340;&amp;#32467;&amp;#26500;&amp;#65292;&amp;#36825;&amp;#24847;&amp;#21619;&amp;#30528;&amp;#26412;&amp;#22320;&amp;#21644;&amp;#36828;&amp;#31243;&amp;#26159;&amp;#19968;&amp;#20010;&amp;#30456;&amp;#23545;&amp;#27010;&amp;#24565;&amp;#12290;
    &lt;/p&gt;
    &lt;p&gt;
      &amp;#29992;git remote&amp;#21629;&amp;#20196;&amp;#21487;&amp;#20197;&lt;content ename=&quot;content&quot;&gt;&amp;#23436;&amp;#25104;&amp;#26412;&amp;#22320;&amp;#21644;&amp;#36828;&amp;#31243;&amp;#30340;&amp;#37197;&amp;#23545;
    &lt;/p&gt;
  &lt;/body&gt;
&lt;/html&gt;</richcontent>
<node CREATED="1513128037029" ID="ID_1875671219" MODIFIED="1517412738090" TEXT="&#x67e5;&#x770b;&#x7ed1;&#x5b9a;&#x7684;&#x8fdc;&#x7a0b;&#x4ed3;&#x5e93;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git remote -v
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1510931032652" ID="ID_240959900" MODIFIED="1513133603701" TEXT="&#x7ed1;&#x5b9a;&#x8fdc;&#x7a0b;&#x4ed3;&#x5e93;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git&#21040;&#36828;&#31243;&#20179;&#24211;&#25903;&#25345;http&#21644;git&#21327;&#35758;&#65292;http&#21327;&#35758;push&#30340;&#26102;&#20505;&#38656;&#35201;&#36755;&#20837;&#29992;&#25143;&#21517;&#21644;&#23494;&#30721;&#65292;git&#19981;&#38656;&#35201;
    </p>
    <p>
      git url&#26684;&#24335;git@github.com:mayonghui2112/helloWorld.git
    </p>
    <p>
      http url&#26684;&#24335;https://github.com/mayonghui2112/helloWorld
    </p>
    <p>
      git remote add origin git@server-name:path/repo-name.git &#20851;&#32852;&#36828;&#31243;&#20179;&#24211;
    </p>
    <p>
      git remote origin set-url [url]
    </p>
    <p>
      git remote rm origin
    </p>
    <p>
      git remote add origin [url]
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1513136810474" ID="ID_383212273" MODIFIED="1513215332012" TEXT="rsa&#x7ed1;&#x5b9a;&#x5230;githup">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#22312;git bash&#19978;&#25191;&#34892; ssh-keygen&#21629;&#20196;&#65292;&#19981;&#24102;git
    </p>
    <p>
      &#36755;&#20837;&#20445;&#23384;&#22320;&#22336; &#65292;&#19968;&#30452;&#25353;&#22238;&#36710;&#38190;
    </p>
    <p>
      &#29983;&#25104;&#30340;key&#22312;c://&#23478;&#30446;&#24405;/.ssh/id_rsa.pub&#20013;
    </p>
    <p>
      &#22797;&#21046;id_rsa.pub&#25991;&#20214;&#20869;&#23481;&#21040;githup&#30340;account setting--&gt;ssh key--&gt;add ssh key &#30340;key&#20013;&#65292;&#36755;&#20837;&#19968;&#20010;title&#65292;&#20445;&#23384;&#21363;&#21487;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509291380741" ID="ID_1604184295" MODIFIED="1513217748302" TEXT="&#x4e0b;&#x8f7d;&#x8fdc;&#x7a0b;&#x4ed3;&#x5e93;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#23558;commit-id&#19979;&#36733;&#21040;&#26412;&#22320;
    </p>
  </body>
</html></richcontent>
<node CREATED="1513215552282" ID="ID_1907403232" MODIFIED="1513215563588" TEXT="pull">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#31532;&#19968;&#27425;
    </p>
    <p>
      git pull --rebase origin master
    </p>
    <p>
      &#20197;&#21518;
    </p>
    <p>
      git pull
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1513215555938" ID="ID_433308239" MODIFIED="1513216855029" TEXT="fetch">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      https://www.cnblogs.com/ToDoToTry/p/4095626.html
    </p>
    <p>
      &#26356;&#26032;commit-id
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1509291393125" ID="ID_274428136" MODIFIED="1513217710728" TEXT="&#x4e0a;&#x4f20;&#x5230;&#x8fdc;&#x7a0b;&#x4ed3;&#x5e93;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      push&#21629;&#20196;&#23558;commit-id&#21516;&#27493;&#21040;&#36828;&#31243;&#12290;
    </p>
    <p>
      git push -u origin master &#31532;&#19968;&#27425;&#25512;&#36865;master&#20998;&#25903;&#30340;&#25152;&#26377;&#20869;&#23481;&#65292;&#21152;&#19978;&#20102;-u&#21442;&#25968;&#65292;Git&#19981;&#20294;&#20250;&#25226;&#26412;&#22320;&#30340;master&#20998;&#25903;&#20869;&#23481;&#25512;&#36865;&#30340;&#36828;&#31243;&#26032;&#30340;master&#20998;&#25903;&#65292;&#36824;&#20250;&#25226;&#26412;&#22320;&#30340;master&#20998;&#25903;&#21644;&#36828;&#31243;&#30340;master&#20998;&#25903;&#20851;&#32852;&#36215;&#26469;&#65292;&#22312;&#20197;&#21518;&#30340;&#25512;&#36865;&#25110;&#32773;&#25289;&#21462;&#26102;&#23601;&#21487;&#20197;&#31616;&#21270;&#21629;&#20196;
    </p>
    <p>
      git push origin --delete Su-modify &#21024;&#38500;&#36828;&#31243;&#20998;&#25903;
    </p>
    <p>
      git push origin master &#25512;&#36865;&#26368;&#26032;&#20462;&#25913;
    </p>
    <p>
      git push origin branch-name &#20174;&#26412;&#22320;&#25512;&#36865;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509291400397" ID="ID_669401793" MODIFIED="1513255693454" TEXT="&#x514b;&#x9686;&#x8fdc;&#x7a0b;&#x4ed3;&#x5e93;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      git clone git@github.com:michaelliao/gitskills.git &#25226;&#36828;&#31243;&#20179;&#24211;&#20811;&#38534;&#21040;&#26412;&#22320;
    </p>
    <p>
      git clone https://github.com/mayonghui2112/helloWorld&#25226;helloword&#20179;&#24211;&#22797;&#21046;&#21040;&#26412;&#22320;&#12290;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1509278949445" HGAP="28" ID="ID_918497093" MODIFIED="1509283547399" POSITION="left" STYLE="bubble" TEXT="&#x72b6;&#x6001;" VSHIFT="-26">
<node CREATED="1509278993457" ID="ID_840192249" MODIFIED="1510931359624" STYLE="bubble" TEXT="Untracked files">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      untracked files&#65306;&#26032;&#24314;&#30340;&#25991;&#20214;&#65292;&#29992;add&#21629;&#20196;&#23558;&#25991;&#20214;&#21152;&#20837;&#26242;&#23384;&#21306;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509279003124" ID="ID_1793201843" MODIFIED="1510931359639" STYLE="bubble" TEXT="Changes to be committed:">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      changes to be committed&#65306;&#25991;&#20214;&#24050;&#22312;&#26242;&#23384;&#21306;&#65292;&#20294;&#25991;&#20214;&#23384;&#22312;&#20462;&#25913;&#30340;&#20869;&#23481;&#21152;&#20837;&#26242;&#23384;&#21306;&#65292;&#23578;&#26410;&#25552;&#20132;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509279003700" ID="ID_1973196665" MODIFIED="1510931359655" STYLE="bubble" TEXT="Changes not staged for commit">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      changes not staged for commit&#65306;&#25991;&#20214;&#24050;&#22312;&#26242;&#23384;&#21306;&#65292;&#20294;&#25991;&#20214;&#23384;&#22312;&#20462;&#25913;&#30340;&#20869;&#23481;&#27809;&#26377;&#21152;&#20837;&#26242;&#23384;&#21306;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1509283351639" ID="ID_968257159" MODIFIED="1509283942623" POSITION="left" STYLE="bubble" TEXT="&#x6587;&#x4ef6;">
<node CREATED="1509279504319" ID="ID_805823455" MODIFIED="1517412969432" STYLE="bubble" TEXT=".gitignore">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font face="&#x5b8b;&#x4f53;" size="3">&#27880;&#24847;&#65306;&#22914;&#26524;git&#31649;&#29702;&#25991;&#20214;&#20197;&#21518;&#65292;&#20877;&#25226;&#25991;&#20214;&#28155;&#21152;&#21040;gitignore&#25991;&#20214;&#20013;&#23601;&#19981;&#20877;&#29983;&#25928;&#20102;&#65292;&#25152;&#20197;&#38656;&#35201;git rm&#21629;&#20196;&#20174;git&#25991;&#20214;&#31227;&#38500;</font>
    </p>
    <p>
      <font face="&#x5b8b;&#x4f53;" size="3">.gitignore&#65306;&#20869;&#23481;&#20026;&#19981;&#38656;&#35201;git&#31649;&#29702;&#30340;&#25991;&#20214;. </font>
    </p>
    <p>
      <font face="&#x5b8b;&#x4f53;" size="3">&#21629;&#20196;&#65306; </font>
    </p>
    <p>
      echo *.jpg&gt;.gitignore
    </p>
    <p>
      add/commit .gitignore
    </p>
    <p>
      
    </p>
    <p style="margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; color: rgb(46, 48, 51); font-family: Arial, Microsoft YaHei, &#x5fae;&#x8f6f;&#x96c5;&#x9ed1;, &#x5b8b;&#x4f53;, Malgun Gothic, sans-serif; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 18px; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255)">
      <font face="&#x5b8b;&#x4f53;" size="3">&#25991;&#20214; .gitignore &#30340;&#26684;&#24335;&#35268;&#33539;&#22914;&#19979;&#65306; </font>
    </p>
    <ul>
      <li>
        <font face="&#x5b8b;&#x4f53;" size="3">&#160;&#160;&#25152;&#26377;&#31354;&#34892;&#25110;&#32773;&#20197;&#27880;&#37322;&#31526;&#21495; &#65283; &#24320;&#22836;&#30340;&#34892;&#37117;&#20250;&#34987; Git &#24573;&#30053;&#12290; </font>
      </li>
      <li>
        <font face="&#x5b8b;&#x4f53;" size="3">&#160;&#160;&#21487;&#20197;&#20351;&#29992;&#26631;&#20934;&#30340; glob &#27169;&#24335;&#21305;&#37197;&#12290; </font>
      </li>
      <li>
        <font face="&#x5b8b;&#x4f53;" size="3">&#160;&#160;&#21305;&#37197;&#27169;&#24335;&#26368;&#21518;&#36319;&#21453;&#26012;&#26464;&#65288;/&#65289;&#35828;&#26126;&#35201;&#24573;&#30053;&#30340;&#26159;&#30446;&#24405;&#12290; </font>
      </li>
      <li>
        <font face="&#x5b8b;&#x4f53;" size="3">&#160;&#160;&#35201;&#24573;&#30053;&#25351;&#23450;&#27169;&#24335;&#20197;&#22806;&#30340;&#25991;&#20214;&#25110;&#30446;&#24405;&#65292;&#21487;&#20197;&#22312;&#27169;&#24335;&#21069;&#21152;&#19978;&#24778;&#21497;&#21495;&#65288;!&#65289;&#21462;&#21453;&#12290; </font>
      </li>
    </ul>
    <p style="margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; color: rgb(46, 48, 51); font-family: Arial, Microsoft YaHei, &#x5fae;&#x8f6f;&#x96c5;&#x9ed1;, &#x5b8b;&#x4f53;, Malgun Gothic, sans-serif; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 18px; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255)">
      <font face="&#x5b8b;&#x4f53;" size="3">&#25152;&#35859;&#30340; glob &#27169;&#24335;&#26159;&#25351; shell &#25152;&#20351;&#29992;&#30340;&#31616;&#21270;&#20102;&#30340;&#27491;&#21017;&#34920;&#36798;&#24335;&#12290;&#26143;&#21495;&#65288;*&#65289;&#21305;&#37197;&#38646;&#20010;&#25110;&#22810;&#20010;&#20219;&#24847;&#23383;&#31526;&#65307;[abc] &#21305;&#37197;&#20219;&#20309;&#19968;&#20010;&#21015;&#22312;&#26041;&#25324;&#21495;&#20013;&#30340;&#23383;&#31526;&#65288;&#36825;&#20010;&#20363;&#23376;&#35201;&#20040;&#21305;&#37197;&#19968;&#20010; a&#65292;&#35201;&#20040;&#21305;&#37197;&#19968;&#20010; b&#65292;&#35201;&#20040;&#21305;&#37197;&#19968;&#20010; c&#65289;&#65307;&#38382;&#21495;&#65288;?&#65289;&#21482;&#21305;&#37197;&#19968;&#20010;&#20219;&#24847;&#23383;&#31526;&#65307;&#22914;&#26524;&#22312;&#26041;&#25324;&#21495;&#20013;&#20351;&#29992;&#30701;&#21010;&#32447;&#20998;&#38548;&#20004;&#20010;&#23383;&#31526;&#65292;&#34920;&#31034;&#25152;&#26377;&#22312;&#36825;&#20004;&#20010;&#23383;&#31526;&#33539;&#22260;&#20869;&#30340;&#37117;&#21487;&#20197;&#21305;&#37197;&#65288;&#27604;&#22914; [0-9] &#34920;&#31034;&#21305;&#37197;&#25152;&#26377; 0 &#21040; 9 &#30340;&#25968;&#23383;&#65289;&#12290;</font>
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1509279886322" ID="ID_103960625" MODIFIED="1510931359733" STYLE="bubble" TEXT="readme.md">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      gi&#20179;&#24211;&#30340;&#25551;&#36848;&#25991;&#20214;
    </p>
    <p>
      init&#21021;&#22987;&#21270;&#19968;&#20010;&#20179;&#24211;&#21518;&#65292;&#21487;&#20197;&#29992;git pull --rebase&#23558;&#36828;&#31243;&#20179;&#24211;&#30340;readme.md&#25991;&#20214;&#21152;&#36733;&#36827;&#26469;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509279933617" ID="ID_42917430" MODIFIED="1509283937957" STYLE="bubble" TEXT="license"/>
<node CREATED="1509283945832" ID="ID_667577138" MODIFIED="1509283949596" TEXT=".git">
<node CREATED="1509283951784" ID="ID_1817714978" MODIFIED="1510931359764" TEXT="config">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#20179;&#24211;&#30340;&#37197;&#32622;&#25991;&#20214;&#65292;&#20855;&#20307;&#23646;&#24615;&#26377;&#65306;
    </p>
    <p>
      global&#65306;&#20840;&#23616;&#21464;&#37327;&#65292;&#35774;&#32622;&#20840;&#23616;&#23646;&#24615;&#25110;&#32773;&#21035;&#21517;&#12290;user.name&#65292;user.email&#35774;&#32622;&#30331;&#24405;&#21517;&#21644;email&#12290;color.ui&#12290;alias&#35774;&#32622;&#21035;&#21517;&#12290;i18n.commitencoding ISO-8859&#21644;i18n.logoutputencoding ISO-8859&#35299;&#20915;gitk&#20013;&#27880;&#37322;&#20013;&#25991;&#20081;&#30721;&#38382;&#39064;
    </p>
    <p>
      core&#65306;&#22522;&#26412;&#23646;&#24615;
    </p>
    <p>
      remote&#65306;&#36828;&#31243;&#23646;&#24615;&#65292;&#21035;&#20837;&#36828;&#31243;url
    </p>
    <p>
      branch&#65306;&#20998;&#25903;&#65292;&#20027;&#20998;&#25903;master&#21644;&#20854;&#20182;&#20998;&#25903;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1509290228770" ID="ID_1984398114" MODIFIED="1509290237119" POSITION="left" TEXT="GIT">
<node CREATED="1509290244634" ID="ID_677172673" MODIFIED="1509290252607" TEXT="etc">
<node CREATED="1509290254690" ID="ID_929218586" MODIFIED="1510931359795" TEXT="inputrc">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#35299;&#20915;git commit&#26080;&#27861;&#36755;&#20837;&#20013;&#25991;&#27880;&#37322;&#30340;&#38382;&#39064;
    </p>
    <p>
      set output-meta on
    </p>
    <p>
      set convert-meta off
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1509290371064" ID="ID_370368684" MODIFIED="1509290484587" TEXT="profile">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#35299;&#20915;git log&#21629;&#20196;&#20013;&#25991;&#27880;&#37322;&#20026;&#20081;&#30721;&#65288;&#21482;bash&#25511;&#21046;&#21488;&#22909;&#29992;&#65289;
    </p>
    <p>
      &#26368;&#21518;&#36861;&#21152;
    </p>
    <p>
      export LESSCHARSET= iso8859
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
</map>
