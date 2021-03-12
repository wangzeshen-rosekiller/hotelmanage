<template>
    <div id="top">
      <div class="toptop">
        <div class="toptopdiv">
        <ul class="toptopleft">
          <li><h4>Dream</h4></li>
          <li><span>Hi,想去哪里玩？</span></li>
          <li><a @click="gotologin" v-if="!loginif">请登录</a>
            <span v-if="loginif">{{loginemail}}@qq.com，欢迎你</span></li>
          <li><a @click="gotologin" v-if="!loginif">免费注册</a></li>
        </ul>
        <ul class="toptopright">
          <li><a @click="gotopc" v-if="loginif">个人中心</a></li>
          <li><a @click="gotooutlogin" v-if="loginif">退出登录</a></li>
        </ul>
        </div>
      </div>
      <div class="topmid">
        <div class="logo">
            <img src="../../dist/img/timg.jpg" width="150" height="40">
        </div>
        <div class="searchbox">
          <span class="sspan">所有产品</span>
          <input class="sinput" type="text" id="keyword-input" value="目的地/酒店/旅行社等"  autocomplete="off">
          <button class="sbutton">搜索</button>
        </div>
        <div class="tel">
            <div class="num">消费者热线(国内)<em>9510208</em></div>
            <div class="anothernum"><em>400-1688-688</em></div>
            <div class="aboardnum">消费者热线(国际)<em>+86-571-56888688</em></div>
        </div>
      </div>
      <div class="topbuttom">
        <div>
          <ul class="nav">
            <li @click="gotohome">首页</li>
            <li>酒店</li>
            <li>客栈公寓</li>
            <li>旅游度假</li>
            <li>定制游</li>
            <li>我的旅行</li>
          </ul>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
      name: "Hometop",
      data(){
          return{
            loginemail:null,
            loginif:false
          }
      },
      methods:{
        gotologin(){
          this.$router.push({path: '/userlogin'});
        },
        gotooutlogin(){
          this.loginemail=null;
          this.loginif=false;
          this.$router.push({path: '/userlogin'});
          sessionStorage.setItem("loginemail",null);
        },
        gotopc(){
          if(this.loginif){
            this.$router.push({path: '/usercenter'});
          }
          else{
            this.$router.push({path: '/userlogin'});
          }
        },
        gotohome(){
          this.$router.push({path: '/'});
        }
      },
      created() {
        let temp=sessionStorage.getItem("loginemail");
        if(temp=="null"||temp==null) {
          this.loginif=false;
        }
        else {
          this.loginif = true;
          this.loginemail = temp;
        }
      }
    }
</script>

<style scoped>
  ul{list-style: none;}
  a {color: #3983e5;
    text-decoration: none !important;
    cursor:pointer;
  }
#top{
  height:155px;
}
.toptop{
  height: 25px;
  background:#F2F2F2;
}
  .toptopdiv {
  margin: 0px 116px;
}
  .toptopleft{
    float: left;
  }
  .toptopright{
    float: right;
  }
  .toptopleft li{
    position: relative;
    padding: 0 6px;
    float: left;
    height: 25px;
    line-height: 25px;
    display: inline-block;
  }
  .toptopright li{
    position: relative;
    float: right;
    padding: 0 6px;
    height: 25px;
    line-height: 25px;
    display: block;
  }

.topmid{
    height: 100px;
    width: 100%;
  }
  .logo{
    margin: 20px 100px;
    width: 10%;
    height: 60px;
    float: left;
  }
  .searchbox{
    float: left;
    margin: 25px 50px;
  }
  .tel{
    float: right;
    margin-top: 20px;
    margin-right: 200px;
    margin-bottom: 20px;
    height: 60px;
  }
  .num,.anothernum,.aboardnum{
    font-size: 12px;
  }
  .anothernum{
    margin-left: 101px;
  }
  .num em{
    margin-left: 10px;
  }
  .sspan{
    width: 75px;
    line-height: 34px;
    background:#f8f8f8;
    padding-left: 15px;
    color: #999;
    border-left: 3px solid #f80;
    border-top: 3px solid #f80;
    border-bottom: 3px solid #f80;
    float: left;
  }
  .sinput{
    width: 332px;
    height: 34px;
    line-height: 34px;
    border-top: 3px solid #f80;
    border-bottom: 3px solid #f80;
    color: rgb(153, 153, 153);
    font-size: 15px;
    float: left;
  }
  .sbutton{
    background-color: #f80;
    font-size: 15px;
    color: white;
    border: none;
    width: 65px;
    float: left;
    height: 39px;
    margin-left: -2px;
  }

.topbuttom{
    width: 100%;
  background-color:#f80;
  }
  .topbuttom div{
    margin: 0 130px;
  }
  .nav{
    height: 30px;
    padding-top:3px;
  }
  .nav li{
    float: left;
    height: 24px;
    line-height: 24px;
    border-radius: 4px;
    margin-right: 8px;
    width: 180px;
    text-align: center;
    color: white;
    cursor: pointer;
  }
</style>
