SELECT   *     FROM   #t 
where @xml.exist('//item[@value=sql:column("class")]')=1