1、对象不同。Application用于保存所有用户的公共数据信息，Session用于保存每个复用户的专用信息；

2、信息量大小不同。Application适用制于任何大小的数据，Session只适用于少量、简单的数据；

3、保存时间不同。Application保存期贯穿于整个应用程序的生命期百，Session保存期一般是度用户活动时间+一段延迟时间，大多数情况下为20分钟；



[![img](https://iknow-pic.cdn.bcebos.com/1f178a82b9014a904acb48f2a4773912b21bee8c?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1)](https://iknow-pic.cdn.bcebos.com/1f178a82b9014a904acb48f2a4773912b21bee8c)





4、知应用范围不同。Application适用于所有用户，Session只用于单个用道户；

5、session是每个用户都有一个，application是所有用户公用一个。