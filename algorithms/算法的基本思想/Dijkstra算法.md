ijkstra算法

 

Dijkstra算法算是**贪心思想**实现的，首先把起点到所有点的距离存下来找个最短的，然后松弛一次再找出最短的，所谓的松弛操作就是，遍历一遍看通过刚刚找到的距离最短的点作为中转站会不会更近，如果更近了就更新距离，这样把所有的点找遍之后就存下了起点到其他所有点的最短距离。

 

问题引入：

指定一个点（源点）到其余各个顶点的最短路径，也叫做“单源最短路径”。例如求下图中的1号顶点到2、3、4、5、6号顶点的最短路径。

![img](https://img-blog.csdnimg.cn/20181120091000307.png)

 

![img](https://img-blog.csdnimg.cn/20181120091021734.png) ![img](https://img-blog.csdnimg.cn/20181120091042107.png)

![img](https://img-blog.csdnimg.cn/20181120091116943.png) 

![img](https://img-blog.csdnimg.cn/20181120091129718.png)![img](https://img-blog.csdnimg.cn/20181120091149585.png)

![img](https://img-blog.csdnimg.cn/20181120091305622.png) 

![img](https://img-blog.csdnimg.cn/20181120091320332.png) 

 下面我们来模拟一下：

![img](https://img-blog.csdnimg.cn/20181120091404344.png)

![img](https://img-blog.csdnimg.cn/20181120091752405.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xicGVyZmVjdDEyMw==,size_16,color_FFFFFF,t_70)



 这就是Dijkstra算法的基本思路：

接下来是代码：

已经把几个过程都封装成了基本模块：

~~~c
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<iostream>
#define Inf 0x3f3f3f3f
 
using namespace std;
 
int map[1005][1005];
 
int vis[1005],dis[1005];
int n,m;//n个点，m条边
 
void Init ()
{
	memset(map,Inf,sizeof(map));
	for(int i=1;i<=n;i++)
	{
		map[i][i]=0;
	}
}
 
void Getmap()
{
	int u,v,w;
    for(int t=1;t<=m;t++)
	{
	  	scanf("%d%d%d",&u,&v,&w);
	  	if(map[u][v]>w)
		  {
		  map[u][v]=w;
		  map[v][u]=w;
	      }
	}	
	
}
 
void Dijkstra(int u)
{
	memset(vis,0,sizeof(vis));
	for(int t=1;t<=n;t++)
	{
		dis[t]=map[u][t];
	}
	vis[u]=1;
	for(int t=1;t<n;t++)
	{
		int minn=Inf,temp;
		for(int i=1;i<=n;i++)
		{
			if(!vis[i]&&dis[i]<minn)
			{
				minn=dis[i];
				temp=i;
			}
		}
		vis[temp]=1;
		for(int i=1;i<=n;i++)
		{
			if(map[temp][i]+dis[temp]<dis[i])
			{
				dis[i]=map[temp][i]+dis[temp];
			}
		}	
	}
	
}
 
int main()
{
	
	scanf("%d%d",&m,&n);
	Init();
	Getmap();
	Dijkstra(n);
	printf("%d\n",dis[1]);
	
	
	return 0;
}
~~~

