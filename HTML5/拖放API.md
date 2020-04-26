# 拖放API

　　拖放是一种常见的特性，即抓取对象以后拖到另一个位置。在 HTML5 中，**拖放是标准的一部分**，任何元素都能够拖放。

　　拖放的过程分为源对象和目标对象。源对象是指你即将拖动元素，而目标对象则是指拖动之后要放置的目标位置。

**拖放的源对象(可能发生移动的)可以触发的事件——3个**：

dragstart：拖动开始

drag：拖动中

dragend：拖动结束

整个拖动过程的组成： dragstart*1 + drag*n + dragend*1

 

**拖放的目标对象(不会发生移动)可以触发的事件——4个**：

dragenter：拖动着进入

dragover：拖动着悬停

dragleave：拖动着离开

drop：释放

整个拖动过程的组成1： dragenter*1 + dragover*n + dragleave*1

整个拖动过程的组成2： dragenter*1 + dragover*n + drop*1

 

**dataTransfer：用于数据传递的“拖拉机”对象；**

 在拖动源对象事件中使用e.dataTransfer属性保存数据：

e.dataTransfer.setData( k,  v )

 在拖动目标对象事件中使用e.dataTransfer属性读取数据：

var value = e.dataTransfer.getData( k )