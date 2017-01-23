官网：http://www.quartz-scheduler.org/
参考文章：http://www.cnblogs.com/wuhao1991/p/4332446.html
spring集成quartz入门配置：http://xhope.top/?p=400
quartz集成spring下的集群配置：http://xhope.top/?p=80

教程：http://www.ibm.com/developerworks/cn/opensource/os-cn-quartz/

maven打包可执行jar：http://blog.csdn.net/zml6308/article/details/39576941

trigger是用于定义调度时间的元素，即按照什么时间规则去执行任务。
job用于表示被调度的任务。
	①无状态的任务stateless
	无状态的job一般指可以并发的执行，即任务之间是独立的，不会互相干扰。
	②有状态的任务stateful
	有状态的 job不能被并行执行，只有上一次触发的任务被执行完之后，才能触发下一次执行。
	
一个job可以被多个trigger关联，但是一个trigger只能关联一个job。
1 : n


动态管理任务
1.减少spring的配置文件
2.用户可以通过页面等方式添加、启用、禁用某个任务
3.用户可以修改某个已经在运行任务的运行时间表达式CronExpression
4.


















