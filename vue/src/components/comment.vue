<template>
  <div>
    <Hometop></Hometop>
    <div class="container">
      <div id="right_column">
        <div class="hotel_info" style="margin-top: 11px;">
          <div class="hotel_name">订单详情</div>
        </div>
        <div class="line1"></div>
        <div class="room_info" id="room_info">
          <div class="room_name" style="margin-top: 4px;"></div>
          <div class="bed_type"><span>订单id：&nbsp;</span>{{commentlist.orderid}}</div>
          <div class="floor"><span>入住时间：&nbsp;</span>{{commentlist.startvalue}}</div>
          <div class="area"><span>离店时间：&nbsp;</span>{{commentlist.endvalue}}</div>
          <div class="base_num"><span>入住人：&nbsp;</span>{{commentlist.name}}</div>
          <div class="network"><span>房型：&nbsp;</span>{{commentlist.roomtype}}</div>
          <div class="window"><span>支付金额：&nbsp;</span>{{commentlist.totalprice}}</div>
          <div class="pay_tip"><span>支付方式：&nbsp;</span>收取全部费用</div>
          <div><span>房间数量：&nbsp;</span>{{commentlist.num}}</div>
        </div>
      </div>
      <div id="box">
        <div class="content">
          <div class="point">
            <span>
              <big>{{total}}</big>
              <small>.0</small>
            </span>
            <span style="margin: 0 10px">您的评论内容：{{textarea}}</span>
          </div>
          <el-divider></el-divider>
          <span>卫生：<el-rate v-model="pointV1" show-score class="rate" :max="maxvalue">
        </el-rate></span>
          <span>服务：<el-rate v-model="pointV2" show-score class="rate" :max="maxvalue">
        </el-rate></span>
          <span>舒适度：<el-rate v-model="pointV3" show-score class="rate" :max="maxvalue">
        </el-rate></span>
          <div class="commenttext">评论内容：</div>
          <el-input type="textarea" placeholder="请输入内容" v-model="textarea" maxlength="100"
                    show-word-limit>
          </el-input>
          <div class="commentbut"><el-button type="primary" @click="commentsave">提交</el-button></div>
        </div>
      </div>
    </div>
    <Homebottom></Homebottom>
  </div>
</template>

<script>
  import Hometop from '@/components/Hometop'
  import Homebottom from '@/components/Homebottom'
    export default {
      name: "comment",
      components:{
          Homebottom,
          Hometop
      },
      data(){
          return{
            orderid: this.$route.query.orderid,
            hotelid: this.$route.query.hotelid,
            commentlist:[],
            pointV1:0,
            pointV2:0,
            pointV3:0,
            textarea:'',
            maxvalue:10
          }
      },
      computed:{
        total(){return ((this.pointV1+this.pointV2+this.pointV3)/3).toFixed(0)}
      },
      methods:{
        commentsave(){
          //console.log(this.hotelid);
          this.$axios.post('/postcomment.do',{
            orderid: this.orderid,
            hotelid:this.hotelid,
            content:this.textarea,
            pointV1: this.pointV1,
            pointV2: this.pointV2,
            pointV3: this.pointV3
          }).then(
            () => {
              this.$router.push({path: '/usercenter'})
            }
          )
        }
      },
      created() {
        this.$axios.post('/comment.do',{
          orderid: this.orderid
        }).then(result =>
        {
          this.commentlist=result.data;
        }).catch(err => {
          console.log('err', err)
        })
      }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css");
  .container {
    margin-left: 100px;
    margin-top: 30px;
  }
  #right_column {
    float: left;
    background-color: #fff;
    border: 5px #666 solid;
    display: inline-block;
  }
   .hotel_name {
    margin: 20px auto 0px 10px;
    color: #f80;
    font-size: 14px;
    font-weight: bolder;
  }
   .line1 {
    background:#ddd;
    width: 187px;
    height: 1px;
    margin: 10px auto 10px auto;
  }
   .room_info {
    padding: 5px 0px 5px 10px;
  }
  .room_info div {
    width: 100%;
    font-size: 14px;
    color:
      #666;
    margin: 21px auto 3px 0px;
  }
  #box {
    border:#666 5px solid;
    background:white;
    width: 80%;
    margin-left: 20px;
    display: inline-block;
    height: 394px;
  }
  .rate{
    display: inline-block;
  }
  #box .content {
    padding:20px;
  }
  .point {
    padding-bottom: 75px;
    overflow: hidden;
    height: 50px;
  }
  .point SPAN {
    float: left;
    line-height: 64px;
    font-family: Tahoma;
  }
  .point SPAN BIG {
    font-weight: bold;
    font-size: 60px;
    line-height: 64px;
    font-family: Tahoma;
  }
  .point SPAN SMALL {
    font-size: 30px;
    line-height: 64px;
    font-family: Tahoma;
  }
  .commenttext{
    margin: 20px 0;
  }
  .commentbut{
    float: right;
    margin: 5px;
  }
</style>
