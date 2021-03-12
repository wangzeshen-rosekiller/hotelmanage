<template>
  <div>
    <Hometop></Hometop>
      <div class="middle">
        <div class="bigimg">
          <img :src="bigimgsrc" alt="">
          <div class="theme">
            <div>#商务酒店</div>
          </div>
        </div>
        <div class="smallimg" v-for="(item,index) in datalistpic" v-if="index<6">
          <img :src="item.path"/>
        </div>
      </div>
      <div class="simpleintro">
        <div class="left">
          <h3>{{chinesename}}</h3>
          <div>{{startname}}</div>
          <div>{{tel}}</div>
          <div>{{address}}</div>
        </div>
          <div class="right">
            {{instruction}}
          </div>
      </div>
      <div class="hotlist">
          <div class="top">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="入住时间" class="eldate">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.date1"></el-date-picker>
                </el-col>
              </el-form-item><el-form-item label="离开时间" class="eldate">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.date2"></el-date-picker>
                </el-col>
              </el-form-item>
              <el-form-item class="eldate">
                <el-button type="primary" @click="onSubmit">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="list">
            <el-table :data="tablelist" style="width: 100%" stripe v-loading="loading">
              <el-table-column  label="房型" width="270">
                <template slot-scope="scope">
                  <div>
                    <a href="javascript:void(0);">
                      <template v-for="(item,index) in scope.row.pictures">
                        <div class="roomimg">
                          <img  :src="item.path" v-if="index<1" >
                        </div>
                      </template>
                    </a>
                  </div>
                  <div class="nameDetail">
                    <p class="name">{{scope.row.roomNameCn}}</p>
                    <p class="detail" id="hl_detail">查看详情&gt;</p>
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="ratePlanInfo[0].bedType" label="床型" width="180">
              </el-table-column>
              <el-table-column prop="" label="早餐" width="180">无早
              </el-table-column>
              <el-table-column prop="ratePlanInfo[0].wifi" label="宽带" width="180">
              </el-table-column>
              <el-table-column prop="ratePlanInfo[0].cancel.name" label="取消政策" width="180">
              </el-table-column>
              <el-table-column  label="日均价" :default-sort = "{prop: 'ratePlanInfo[0].totalPrice', order: 'descending'}">
                <template slot-scope="scope">
                  <em>￥</em>
                  <template v-for="(item,index) in scope.row.ratePlanInfo" v-if="index<1">
                    <em>{{item.guidedPrice}}</em>
                    <a class="book-btn" @click="gotopay(item.guidedPrice,item.bedInfo,scope.row.roomNameCn)">预订</a>
                  </template>

                </template>
              </el-table-column>
            </el-table>
          </div>
      </div>
      <div class="comlist">
        <el-table :data="commentlist" style="width: 100%">
          <el-table-column prop="startvalue" label="日期" width="150">
          </el-table-column>
          <el-table-column label="信息">
            <el-table-column  prop="useremail" label="用户" width="200">
            </el-table-column>
            <el-table-column label="评分">
              <el-table-column prop="pointV3" label="卫生" width="100">
              </el-table-column>
              <el-table-column prop="pointV2" label="服务" width="100">
              </el-table-column>
              <el-table-column prop="pointV3" label="舒适度" width="100">
              </el-table-column>
              <el-table-column prop="content" label="评论" width="270">
              </el-table-column>
              <el-table-column prop="reply" label="店家回复">
              </el-table-column>
            </el-table-column>
          </el-table-column>
        </el-table>
      </div>
    <Homebottom></Homebottom>
  </div>
</template>

<script>
  import Hometop from '@/components/Hometop'
  import Homebottom from '@/components/Homebottom'

    export default {
        name: "Homedetail",
      components:{
          Hometop,
        Homebottom
      },
      data(){
          return{
            hotelid: this.$route.query.hotelid,
            datalistpic:[],
            datalist:[],
            tablelist:[],
            commentlist:[],
            bigimgsrc:'',
            chinesename:'',
            startname:'',
            tel:0,
            instruction:'',
            address:'',
            form: {
              date1: '',
              date2: '',
            },
            loading:true
          }
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        gotopay(price, bedtype, roomNameCn) {
          this.qqemail = sessionStorage.getItem("loginemail");
          console.log(this.qqemail);
          if (this.qqemail == "null" || this.qqemail == null) {
            this.$router.push({path: '/userlogin'});
          } else {
            console.log(this.chinesename + price + bedtype + roomNameCn);
            this.$router.push({
              path: '/pay', query: {
                hotelid: this.hotelid,
                chinesename: this.chinesename,
                address: this.address,
                startvalue: this.startvalue,
                tel: this.tel,
                price: price,
                bedtype: bedtype,
                roomNameCn: roomNameCn
              }
            });
          }
        }
      },
      created() {
          this.$axios.get('http://route.showapi.com/1653-3',{
          params:{
            "showapi_appid": '', //这里需要改成自己的appid
            "showapi_sign": '', //这里需要改成自己的应用的密钥secret
            "hotelId" : this.hotelid
          }
        }).then(result =>
          {
          //console.log(result.data.showapi_res_body.data.pictures);
          this.datalistpic=result.data.showapi_res_body.data.pictures;
          this.datalist=result.data.showapi_res_body.data
          this.bigimgsrc=this.datalistpic[0].path;
          this.chinesename=this.datalist.chineseName;
          this.startname=this.datalist.starName;
          this.tel=this.datalist.tel;
          this.instruction=this.datalist.instruction;
          if(this.instruction.length>230){this.instruction=this.instruction.substring(0,230)+"...";}
          this.address=this.datalist.address;
        }).catch(err => {
          console.log('err', err)
        }),

          this.$axios.get('http://route.showapi.com/1653-4',{
            params:{
              "showapi_appid": '', //这里需要改成自己的appid
              "showapi_sign": '', //这里需要改成自己的应用的密钥secret
              "hotelId" : this.hotelid
            }
          }).then(result =>
          {
            //console.log(result.data.showapi_res_body.roomInfo);
            //this.datalistpic=result.data.showapi_res_body.data.pictures;
            this.tablelist=result.data.showapi_res_body.roomInfo;
            this.loading=false;
          }).catch(err => {
            console.log('err', err)
          })

          this.$axios.post('/getallcomment.do',{
            hotelid: this.hotelid
          }).then(result =>
          {
            this.commentlist=result.data;
            console.log(result.data.totalprice);
          }).catch(err => {
            console.log('err', err)
          })
      }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css");
.middle{
  width: 90%;
  margin: 30px auto;
  display: inline-block;
  padding-left: 115px;
}
  .bigimg{
    width: 590px;
    height: 332px;
    float: left;
    overflow: hidden;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    border: 1px black solid;
  }
  .bigimg img{
    width: 100%;
    height: 100%;
  }
  .smallimg{
    width: 190px;
    height: 161px;
    float: left;
    overflow: hidden;
    margin: 0 0 10px 10px;
    border-radius: 6px;
    cursor: pointer;
    background: #fafafa;
    border: 1px black solid;
  }
  .smallimg img{
    width: 100%;
    height: 100%;
  }
  .theme{
    position: absolute;
    bottom: 15px;
    left: 15px;
  }
  .theme div{
    height: 26px;
    display: inline-block;
    background: rgba(0,0,0,.8);
    border-radius: 13px;
    font-size: 12px;
    color: #fff;
    line-height: 26px;
    text-align: center;
    margin-top: 5px;
    margin-right: 5px;
    padding: 0 10px;
  }
.simpleintro{
  width: 81%;
  margin-left: 140px;
  }
  .simpleintro h3{
    font-size: 30px;
    color: #000;
    display: inline-block;
    max-width: 420px;
  }
  .left{
    float: left;
    margin-right: 20px;
  }
  .eldate{
    display: inline-table;
    margin-left: 140px;
  }
  .hotlist,.comlist{
    width: 84%;
    border-radius: 12px;
    padding: 18px 0 0;
    overflow: hidden;
    margin-left: 110px;
    margin-top: 29px;
  }
  .hotelPic{
    width: 100px;
    height: 56px;
    margin: 0 auto;
    display: block;
  }
  .roomimg{
    width: 100px;
    height: 56px;
    display: block;
  }
  .nameDetail {
    width: 100px;

    text-align: center;
  }
  .name {
    color: #333;
    cursor: pointer;
    font-size: 14px;
  }
  .detail {
    color: #f80;
    cursor: pointer;
    margin-top: 5px;
    font-size: 12px;
  }
  .roomimg img{
    width: 100%;
    height: 100%;
  }
  .book-btn {
    color: #fff;
    background-color: #f80;
    display: inline-block;
    vertical-align: middle;
    width: 100px;
    height: 33px;
    text-align: center;
    cursor: pointer;
    position: relative;
    font: bold 16px/2 'Microsoft Yahei';
    margin: 0 4px;
  }
</style>
