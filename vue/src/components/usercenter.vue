<template>
<div>
  <Hometop></Hometop>
  <div class="contain">
    <div class="left">
      <ul id="first-nav">
        <li id="order">
          <span>我的订单</span> <span class="nav-arrow"></span>
        </li>
        <li id="cash">
          <span>我的资产</span> <span class="nav-arrow arrow-down"></span>
        </li>
        <li id="usercenter">
          <span>个人中心</span> <span class="nav-arrow arrow-down"></span>
        </li>
        <li id="message">
          <span>常用信息</span> <span class="nav-arrow arrow-down"></span>
        </li>
        <li id="tools">
          <span>常用工具</span> <span class="nav-arrow arrow-down"></span>
        </li>
      </ul>
    </div>
    <div class="right">
      <el-table :data="buylist" style="width: 80%">
        <el-table-column label="酒店" width="200">
          <template slot-scope="scope">
            <img class="item-icon" src="https://m.tuniucdn.com/fb2/t1/G5/M00/3F/4B/Cii-tFxBMT2IafNQAAAGz46cPLgAATNzQP-M8EAAAbn275.png">
            <span>{{scope.row.chinesename}}</span>
            <span>{{scope.row.roomtype}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="num" label="数量" width="120">
        </el-table-column>
        <el-table-column label="时间" width="120">
          <template slot-scope="scope" >
            <span>{{scope.row.startvalue}}</span>
            至
            <span>{{scope.row.endvalue}}</span>
          </template>
        </el-table-column>
        <el-table-column label="价格" width="120">
          <template slot-scope="scope">
            <em>￥</em>
            <span class="price">{{scope.row.totalprice}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="orderid" label="订单号" width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <span class="statue">
              <h4 style="margin-bottom: 13px;width:57px">{{scope.row.statue}}</h4>
              <em></em>
            </span>
            <span class="statue2">
              <el-button type="info" plain class="btn" size="mini" v-if="scope.row.statue=='预定成功'" @click="update(scope.row.orderid)">取消</el-button>
              <el-button type="warning" plain class="btn" size="mini" v-if="scope.row.statue=='已完成'" @click="gotocomment(scope.row.orderid,scope.row.hotelid)">评价</el-button>
              <el-button type="info" plain class="btn" size="mini" @click="del(scope.row.orderid)">删除</el-button>
            </span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
  <Homebottom></Homebottom>
</div>
</template>

<script>
  import Hometop from '@/components/Hometop'
  import Homebottom from '@/components/Homebottom'

    export default {
      name: "usercenter",
      components: {
        Hometop,
        Homebottom
      },
      data() {
        return {
          qqemail: '',
          buylist: []
        }
      },
      methods: {
        del(orderid) {
          console.log(orderid);
          this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.$axios.post('/delorder.do', {
              orderid: orderid
            });
            this.$router.go(0);
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        update(orderid) {
          console.log(orderid);
          this.$confirm('此操作将取消该订单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '取消成功!所付款将按原路退还'
            });
            this.$axios.post('/updateorder.do', {
              orderid: orderid,
              statue: 1
            });
            this.$router.go(0);
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
          });
        },
        gotocomment(orderid, hotelid) {
          //console.log(hotelid);
          this.$router.push({path: '/comment', query: {orderid: orderid, hotelid: hotelid}});
        }
      },
      created() {
        this.qqemail = sessionStorage.getItem("loginemail");
        console.log(this.qqemail);
        if (this.qqemail == "null" || this.qqemail == null) {
          this.$router.push({path: '/userlogin'});
        } else {
          this.$axios.post('/getallbuy.do', {
            qqemail: this.qqemail
          }).then(result => {
            this.buylist = result.data;
            console.log(result.data);
          }).catch(err => {
            console.log('err', err)
          })
        }
      }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css");
  .el-button + .el-button {
    margin-left: 0px;
  }
  ul{
    list-style: none;
  }
.left{
  width: 13%  ;
  float: left;
  background-color: #FF8800;
  padding-bottom: 1px;
  margin-right: 4%;
}
  .right{
    /*float: left;*/
  }

.contain{
  width: 90%;
  margin: 30px 100px;
  min-height: 400px;
}
#first-nav { padding: 20px 29px 0; }
ul#first-nav > li#order { background-image: url(//img3.tuniucdn.com/site/wap/img/member/usercenter/order.png); }
ul#first-nav > li { color: #fff; font-size: 16px; margin-bottom: 30px; background-repeat: no-repeat; background-position: 0 4px; padding-left: 25px; cursor: pointer; position: relative; }
 ul#first-nav > li#cash { background-image: url(//img3.tuniucdn.com/site/wap/img/member/usercenter/cash.png); }
 ul#first-nav > li#usercenter { background-image: url(//img3.tuniucdn.com/site/wap/img/member/usercenter/usercenter.png); }
 ul#first-nav > li#message { background-image: url(//img3.tuniucdn.com/site/wap/img/member/usercenter/message.png); }
 ul#first-nav > li#tools { background-image: url(//m.tuniucdn.com/fb2/t1/G1/M00/AF/66/Cii9EVjbJ2SIXuLqAAABylqRH6EAAI-xQP__h4AAAHi452.png); }
  .item-icon {
    width: 9%;
    margin-right: 19px;
    vertical-align: middle;
  }
  .price{
    ext-align: center;
    color: #ff6600;
    display: inline-block;
  }
  .btn{
    display: block;
  }
  .statue{
    text-align: center;
    color: #ff6600;
    display: inline-block;
  }
  .statue2{
    display: inline-block;
  }
</style>
