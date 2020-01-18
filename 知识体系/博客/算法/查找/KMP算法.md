# KMP算法

## 简化：

​	第一步简化，找寻主串**S**中和模式串**P**第一个字母相同的字母，这个需要遍历主串，无法优化，在比较的时候寻找就行。

​	第二部优化，优化模式串移动的位置，朴素匹配法移动一个位置。但在前一轮的比较中，我们已经知道了P的前(j-1)位与S中间对应的(i-1)个元素已经匹配成功了。这就意味着，在一轮的尝试匹配中，我们get到了主串的部分内容，我们能否利用这些内容，让P多移几位(MP算法优化点)，减少遍历的趟数呢？答案是肯定的。

​	分析可知（具体可参考文章底部博文链接），每次失配，S串的索引**i**不动，**P串的索引j定位到某个数**。时间效率明显提高。而这“定位到某个数”，这个数就是接下来引入的**next**值。

难点在求next()值这。

## next():

### 公式如下

![在这里插入图片描述](https://img-blog.csdn.net/20181005224812581?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3OTY5NDMz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 推理：

有公式可知，next值的重点就是求p<sub>1</sub>...p<sub>k--1</sub>=p<sub>j-k+1</sub>...p<sub>j-1</sub>时k的最大值，即模式串**首（p<sub>1</sub>...p<sub>k--1</sub>）尾（p<sub>j-k+1</sub>...p<sub>j-1</sub>）重合**部分的最大值。

有定义可知

当**j=1**时

​	next[1] = 0;

没有任何匹配的情况下

​	next[j] = 1;

首尾重合的时候：

当j+1时，它的最大首尾重合部分有可能为

当p<sub>1</sub>...p<sub>j--1</sub>=p<sub>2</sub>...p<sub>j</sub> => next[j] = j

当p<sub>1</sub>...p<sub>j--2</sub>=p<sub>3</sub>...p<sub>j-1</sub> => next[j] = j-1

当p<sub>1</sub>...p<sub>j--3</sub>=p<sub>4</sub>...p<sub>j-1</sub> => next[j] = j-2

。。。

当p<sub>1</sub>p<sub>j2</sub>=p<sub>j-1</sub>...p<sub>j</sub> => next[j] = 3

当p<sub>1</sub>=p<sub>j-1</sub>=> next[j] = 2

当p<sub>1</sub>!=p<sub>j-1</sub>=> next[j] = 1

如果我们已经知道 next[j] = k，即 p<sub>1</sub>...p<sub>k-1</sub> = p<sub>j-k+1</sub>...p<sub>j-1</sub> 时，此时 next[j+1]是多少呢？有两种情况：

当p<sub>k</sub>=p<sub>j</sub>，即：p<sub>1</sub>...p<sub>k</sub> = p<sub>j-k+1</sub>...p<sub>j</sub>，（1<k<j）且不存在大于k的数字，使p<sub>1</sub>...p<sub>k-</sub>=p<sub>j-k+1</sub>...p<sub>j</sub>成立。此时为next[j+1] = k+1。（代码第四行）。

这里加一句话：读到这里时我有个疑问，当p<sub>k</sub>=p<sub>j</sub>，如果主串的S<sub>i</sub>!=p<sub>j</sub>，那么S<sub>i</sub>!=p<sub>k</sub>，那么此时next[j+1] = k+1就不成立了，后来一想，如果S<sub>i</sub>!=p<sub>j</sub>，那也就不会去比较S<sub>i+1</sub>!=p<sub>j+1</sub>，也就和next[j+1]没什么关系了。

当p<sub>k</sub>!=p<sub>j</sub>，即：p<sub>1</sub>...p<sub>k</sub> != p<sub>j-k+1</sub>...p<sub>j</sub>，我们需要在p<sub>j-k+1</sub>...p<sub>j</sub>中找寻一个子串，假设长度为**k**'，使p<sub>1</sub>...p<sub>k‘</sub> = p<sub>j-k’+1</sub>...p<sub>j</sub>成立，也就是在p<sub>1</sub>...p<sub>k</sub>中存在一个子串，使p<sub>1</sub>...p<sub>k‘</sub> = p<sub>k-k’+1</sub>...p<sub>k</sub>，这个等式换个角度一看，把k’当k，k当j，你会发现，这个其实就是求j = k的next值，因为k<j，所以在前面我们已经求过，直接拿过来用就可。（代码第五行）

又上述过程得出传说中的5行代码：

~~~java
void getNext(char[] chars,int len,int[] next){
	next[1] = 0; int i=1,j=0;
	while(i<len){
		if(j == 0 || chars[i] == chars[j]) next[++i] = ++j;
		else j = next[j];
	}
}
~~~

## 参考博文：

https://blog.csdn.net/qq_37969433/article/details/82947411

《数据结构（c语言版）》，严蔚敏老师版。