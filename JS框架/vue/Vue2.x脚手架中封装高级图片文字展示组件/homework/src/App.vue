<template>
  <div id="app">
      <div class='outNode'>
        <div class='topNode'>
          <div v-if='img.length<=4?false:true' class='left' @click='leftCl' @mousedown.prevent>左</div>
          <div v-if='img.length<=4?false:true' class='right' @click='rightCl' @mousedown.prevent>右</div>
          <div class='moveDiv'>
            <ul :style='{width:img.length*800+"px",left:index*-800+"px"}'>
              <li v-for='i in img'>
                <img :src="i" :style='{transform:"scale("+transitionlast+")"}' v-drag='1'>
              </li>
            </ul>
          </div>
          <div class='textDiv'>
            <span class='text'>{{msg[index]}}</span>
            <div class='fdj'>
              <span @click='small' @mousedown.prevent>小</span>
              <span @click='big' @mousedown.prevent>大</span>
            </div>
          </div>
        </div>
        <div class='bottomNode'>
          <div v-if='img.length<=4?false:true' class='left' @click='leftCl' @mousedown.prevent>左</div>
          <div v-if='img.length<=4?false:true' class='right' @click='rightCl' @mousedown.prevent>右</div>
          <ol :style='{width:img.length*200+"px",left:index>=4?(index-3)*-200+"px":0}'>
            <li v-for='(i,needIndex) in img' :class='index==needIndex?"active":""' @click='index=needIndex'>
              <img :src="i">
            </li>
          </ol>
        </div>
      </div>
  </div>
</template>

<script>
//import Vue from 'vue'
var Vue = require('vue').default;

Vue.directive('drag',(el,val)=>{
  //console.log(el,val)
  el.onmousedown = function(e){
    var ev = e || event;
    var l = ev.clientX - el.offsetLeft;
    var t = ev.clientY - el.offsetTop;
    document.onmousemove = function(e){
      var ev = e || event;
      el.style.left = ev.clientX - l + 'px';
      el.style.top = ev.clientY - t + 'px';
    };
    document.onmouseup = function(){
      this.onmousemove = this.onmouseup = null;
    }
    return false;
  }
})

export default {
  name: 'app',
  data () {
    return {
      img:['img/1.jpg','img/2.jpg','img/3.jpg','img/4.jpg','img/5.jpg','img/6.jpg'],
      msg: ['图片1','图片2','图片3','图片4','图片5','图片6'],
      index:0,
      transitionlast:1
    }
  },
  methods:{
    rightCl(){
      this.index++;
      this.index == this.img.length && (this.index = 0);
      this.transitionlast = 1; 
    },
    leftCl(){
      this.index--;
      this.index == -1 && (this.index = this.img.length-1);
      this.transitionlast = 1;
    },
    small(){
      if(this.transitionlast<=0.4){
        alert('已经到极限了');
        return;
      }
      this.transitionlast-=0.2;

    },
    big(){
      this.transitionlast+=0.2;

    }
  }
}
</script>

<style>
*{margin:0;padding: 0;list-style: none;}
.outNode{
  width: 800px;height: 640px;border:1px solid black;
}

.topNode{
  width: 100%;float: left;height: 440px;position: relative;
}
.left{position: absolute;left: 0;top: 40%;z-index: 5;color: white;font-size: 50px;cursor: pointer;background: red;}
.right{position: absolute;right: 0;top: 40%;z-index: 5;color: white;font-size: 50px;cursor: pointer;background: red;}


.topNode .moveDiv{position: relative;overflow: hidden;}
.topNode .moveDiv>ul{height: 400px;position: absolute;left: 0;top: 0;transition:.3s;}
.topNode .moveDiv>ul>li{width: 800px;float: left;height: 100%;overflow: hidden;position: relative;}
.topNode .moveDiv>ul>li img{width: 100%;height: 100%;position: absolute;}
.topNode .moveDiv{width: 100%;height: 400px;float: left;}
.topNode .textDiv{width: 100%;height: 40px;float: left;background: #0000ff;line-height: 40px;}
.text{float: left;color: white;margin-left: 20px;}
.bottomNode{
  width: 100%;float: left;height: 200px;position: relative;overflow: hidden;
}
.bottomNode>ol{
  height:100%;position: absolute;left: 0;top: 0;
  transition:.3s;
}
.bottomNode>ol li{
  width: 160px;height: 160px;float: left;
  border:20px black solid;
  transition:.3s;
}
.bottomNode>ol li.active{
  border-color:orange;
}
.bottomNode>ol li img{
  width: 100%;height: 100%;
}
.fdj{
  float: right;color: white;margin-right: 20px;
}
</style>
