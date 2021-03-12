<template>
  <div @keyup.enter="login" id="userlogin">
    <div class="header">
      <div class="logo">
        <a @click="gotohome"> <img src="../../dist/img/timg.jpg" width="150" height="40">
        </a>
      </div>
    </div>
    <div class="middle">
      <div class="right">
        <div class="login_nav">
          <div class="login_div" style="float: left;"
               :class="{active:isactive1}" @click="tab1" ref="tab1">账户登陆
          </div>
          <div class="login_div" style="float: right;"
               :class="{active:isactive2}" @click="tab2">免费注册
          </div>
        </div>
        <div class="login_tip"></div>
        <div>
          <span class="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span>
          <input type="text" id="loginemail" placeholder="邮箱"
                 oninput="if(value.length>=12)value=value.slice(0,12)" ref="loginemail"/> <input
          type="text" value="@qq.com" readonly="readonly" id='emailtype'/><br/>

          <div class="checkemail_div" v-if="isactive2">
            <span class="checkemail">验&nbsp;&nbsp;证&nbsp;&nbsp;码</span> <input
            type="text" placeholder="验证码" id="checkemail" ref="checknumber"/> <input
            type="button" value="获取验证码" id="checkbutton" :disabled="disabled" ref="check" :style="checkbutton"
            @click="settime"/> <br/>
          </div>

          <span class="pwd">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
          <input :type="pwdtype" id="loginpassword" placeholder="密码" ref="loginpassword"/>
          <span class="eye"><img @click="eyechange" :src="require('../../dist/img/' + eyesrc)" class="eyeimg"/></span>
          <br/>

          <div class="checkemail_div" v-if="isactive2">
            <span class="pwd">确认密码</span> <input type="password"
                                                 placeholder="再次确认密码" id="againregpwd" ref="againpassword"/> <br/>
          </div>

          <input type="checkbox" id="autologin" v-if="isactive1"/><label v-if="isactive1">两周内自动登录</label><br
          v-if="isactive1"/>
          <div class="login_tip" v-if="isactive1"></div>
          <input type="button" value="登录" class="submit" id="submitlogin"
                 v-if="isactive1" key="submitlogin"  @click="login" />
          <input type="button"
                 value="注册" class="submit" id="submitreg" v-show="isactive2"
                 key="submitreg" @click="reg" /> <br/>
          <a href="#" class="forgetpwd"
             v-if="isactive1">忘记密码</a>
          <button class="nouser" v-if="isactive1" @click="tab2">没有账号？立即注册</button>
        </div>
      </div>
    </div>
    <div class="bottom">
      <p id="hour_24">24小时客户服务电话(免长途费)：9999-999-999
        Dream呼叫中心位于珠海来电将统一显示为 999-99999999</p>
      <p id="links">
        <a href="javascript:return false;">关于我们</a> <a
        href="javascript:return false;">Investor Relations</a> <a
        href="javascript:return false;">联系我们</a> <a
        href="javascript:return false;">投诉建议</a> <a
        href="javascript:return false;">广告服务</a> <a
        href="javascript:return false;">旅游券</a> <a
        href="javascript:return false;">Dream招聘</a> <a
        href="javascript:return false;">隐私保护</a> <a
        href="javascript:return false;">免责声明</a> <a
        href="javascript:return false;">旅游度假资质</a> <a
        href="javascript:return false;">供应商合作</a> <a
        href="javascript:return false;">用户协议</a> <a
        href="javascript:return false;">网站地图</a> <br> <a
        href="javascript:return false;">团队博客</a> <a
        href="javascript:return false;">网站联盟</a> <a
        href="javascript:return false;">UEIP</a> <a
        href="javascript:return false;">帮助中心</a>
      </p>
      <p id="copyright">
        Copyright © 2019-2024 <a href="javascript:return false;">Dream酒旅</a>
        <a href="javascript:return false;">营业执照</a> | <a
        href="javascript:return false;">ICP证：粤C2-20190006</a>&nbsp;&nbsp;
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "userlogin",
    data() {
      return {
        isactive1: true,
        isactive2: false,
        pwdtype: 'password',
        eyeischange: false,
        eyesrc:'eyeclose.jpg',
        loginemail:null,
        loginpassword:null,
        countdown:30,
        disabled:false,
        checkbutton:'background:#f80',
        checknum:null,
        againpassword:null
      }
    },
    methods:
      {
        tab1(){this.isactive2=false;this.isactive1=true;},
        tab2(){this.isactive2=true;this.isactive1=false;},
        eyechange() {
          if(this.eyeischange)
        {
          this.pwdtype='password';
          this.eyeischange=false;
          //console.log(this.pwdtype);
          this.eyesrc='eyeclose.jpg'
        }
        else
        {
          this.pwdtype='text';
          this.eyeischange=true;
          //console.log(this.pwdtype);
          this.eyesrc='eyeopen.jpg'
        }
        },
        login() {
          this.loginemail=this.$refs.loginemail.value;
          this.loginpassword=this.$refs.loginpassword.value;
          console.log(this.loginpassword+"xxx"+this.loginemail);
          var rex = /^[0-9]+$/;
          if (this.loginemail==null||this.loginemail=="") {
            alert("邮箱不能为空");
          } else if (!rex.test(this.loginemail)) {
            alert("邮箱格式不正确,只能含有数字");
          } else if (this.loginemail.length<=7||this.loginemail.length>=12) {
            alert("邮箱只能填写8-12位");
          } else if (this.loginpassword==null||this.loginpassword=="") {
            alert("密码不能为空");
          } else {
            this.$axios.post('/checkpassword.do',{
              loginemail:this.loginemail,
              loginpassword:this.loginpassword
            }).then(successResponse=> {
              console.log(successResponse.data);
              if (successResponse.data== "success") {
                sessionStorage.setItem("loginemail",this.loginemail);
                this.$router.push({path: '/'})
              }
              else {
                alert("密码错误");
              }})
          }
        },
        reg(){
          this.loginemail='';
          this.loginpassword='';
          this.loginemail=this.$refs.loginemail.value;
          this.loginpassword=this.$refs.loginpassword.value;
          this.againpassword=this.$refs.againpassword.value;
          let rex = /^[0-9]+$/;
          let temp1=this.$refs.checknumber.value.toString();
          let temp2=this.checknum.toString();
          if (this.loginemail==null||this.loginemail=="") {
            alert("邮箱不能为空");
          } else if (!rex.test(this.loginemail)) {
            alert("邮箱格式不正确,只能含有数字");
          } else if (this.loginemail.length<=7||this.loginemail.length>=12) {
            alert("邮箱只能填写7-12位");
          } else if (this.loginpassword==null||this.loginpassword=="") {
            alert("密码不能为空");
          } else if (this.loginpassword.length<=7||this.loginpassword.length>=12) {
            alert("密码只能填写7-12位");
          } else if (this.loginpassword!=this.againpassword) {
            alert("两次密码需相同");
          } else if (temp1!=temp2) {
            alert("验证码不正确");
          } else {
            this.$axios.post('/successreg.do',{
              qqemail:this.loginemail,
              password:this.loginpassword
            })
            alert("注册成功，请登录")
            this.$refs.tab1.click();
          }
        },
        settime() {
          var _this = this;
          this.loginemail = this.$refs.loginemail.value;
          //console.log(this.loginemail);
          if (this.loginemail == "") {
            alert("邮箱不能为空");
          } else {
            if (this.countdown == 30) {
              this.checkbutton = 'background:#f80';
              this.$axios.post('/sendemail.do', {
                qqemail: this.loginemail
              }).then(successResponse => {
                if (successResponse.data == "fail") {
                  alert("该邮箱已经存在,请直接登陆");
                  this.countdown = 0;
                  this.$refs.tab1.click();
                }
                else
                {
                  this.checknum=successResponse.data;
                }
              })
            }
            if (this.countdown == 0) {
              this.checkbutton = 'background:#f80';
              this.disabled = false;
              this.$refs.check.value = "获取验证码";
              this.countdown = 30;
              return false;
            } else {
              this.checkbutton = 'background:gray';
              //console.log(this.countdown)
              this.disabled = true;
              this.$refs.check.value = "重新发送(" + this.countdown + ")";
              this.countdown--;
            }
            setTimeout(function () { //设置一个定时器，每秒刷新一次
              _this.settime();
            }, 1000);
          }
        },
        gotohome(){
          this.$router.push({path: '/'});
        }
      }
  }
</script>

<style scoped>
  * {
    margin: 0px;
    padding: 0px;
  }

  a {
    text-decoration: none;
  }

  ul li {
    list-style-type: none；
  }

  .header {
    height: 80px;
    margin: 0 auto;
  }

  .logo {
    float: left;
    margin: 20px 100px;
  }

  .middle {
    width: 100%;
    height: 600px;
    background: url(../../dist/img/ba1.jpg) no-repeat;
    background-position: top center;
  }

  .bottom {
    width: 100%;
    color: #777;
  }

  .bottom p {
    line-height: 40px;
    text-align: center;
  }

  .bottom p a {
    color: #777;
    text-decoration: none;
  }

  .right {
    position: absolute;
    top: 140px;
    right: 40px;
    width: 400px;
    background-color: white;
  }

  input[type="text"],input[type="password"],input[type="email"],select {
    border: 1px solid #DCDEE0;
    vertical-align: middle;
    height: 50px;
    padding: 0px 16px;
    font-size: 14px;
    color: #555555;
    outline: none;
    margin-bottom: 15px;
    color: #888;
  }

  .login_div {
    width: 50%;
    text-align: center;
    height: 35px;
    padding: 10px 0px;
  }

  .active {
    color: blue;
  }

  .email,.pwd,#autologin,.submit,.checkemail {
    margin-left: 20px;
    margin-right: 10px;
    font-size: 18px;
  }

  #loginemail {
    width: 38%;
  }

  #emailtype {
    width: 17%;
  }

  #loginpassword {
    width: 53%;
  }

  #againregpwd {
    width: 64%;
  }

  #checkemail {
    width: 42%;
  }

  .login_tip {
    height: 20px;
  }

  .login_nav {
    height: 55px;
  }

  .submit {
    display: block;
    background: #f80;
    text-align: center;
    color: #fff;
    height: 40px;
    line-height: 40px;
    border-radius: 3px;
    width: 90%;
    border: 0 none;
    font-size: 18px;
  }

  .forgetpwd {
    float: left;
    text-decoration: none;
    margin-left: 20px;
    margin-bottom: 15px;
    color: #337ab7;
  }

  .nouser {
    float: right;
    text-decoration: none;
    margin-right: 20px;
    margin-bottom: 15px;
    color: #337ab7;
    border: none;
    background-color: transparent;
  }

  #checkbutton {
    background: #f80;
    color: #fff;
    height: 39px;
    border-radius: 3px;
    width: 20%;
    border: 0 none;
  }

  .eye img {
    width: 41px;
    height: 28px;
    transform: scale(0.5);
  }


</style>
