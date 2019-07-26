<center>BPMN简介</center>

## process

​	一个工作流程一个process

​	id：一个流程的唯一表示，可以用`startProcessInstanceByKey` 启动一个流程实例。注意`startProcessInstanceById` 方法需要的id与是在部署的时候由流程引擎生成的，是**key:version**形式，此处暂且叫**deployId**其中，key即为本id因为deployId做大长度为64位的约束，所以id要适当。

​	name：一个易读的名字，用于页面显示