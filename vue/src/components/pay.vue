<template>
  <div>
    <div class="orderBg">
      <div id="wrap" class="wrap">
        <div class="content_title">
          <div class="hotel_name">
            <h2>{{chinesename}}</h2>
          </div>
          <div class="dream_guarantee">
            <a href="javascript:" class="bolder" id="noRoomPaid">梦想家保障：</a>
            <span>承诺到店无房赔付首晚房费，请放心预订</span>
          </div>
          <div id="container" class="container clearfix">
            <div id="order_info" class="block">
              <div class="title">
                <span class="tip"></span>
                <span>预订信息</span>
              </div>
              <div class="block_line"></div>
              <div class="block_item date">
                <div class="block_item_content">
                  <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="入住时间">
                      <el-col :span="11">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" value-format="yyyy/MM/dd" format="yyyy/MM/dd"></el-date-picker>
                      </el-col>
                    </el-form-item>
                    <el-form-item label="离开时间">
                      <el-col :span="11">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" value-format="yyyy/MM/dd" format="yyyy/MM/dd"></el-date-picker>
                      </el-col>
                    </el-form-item>
                  </el-form>
                  <span>共{{totaldata}}晚</span>
                </div>
              </div>
              <div class="block_item rooms">
                <div class="block_item_title"><span>预订间数：</span></div>
                <div class="block_item_content">
                  <div class="room_select">
                    <input type="button" id="num-jian" class="num-jian" value="-" @click="decrease">
                    {{roomnum}}
                    <input type="button" id="num-jia" class="num-jia" value="+" @click="increase">
                  </div>
                  <div class="remaining_rooms hide" style="display: inline-block;">剩余<span> {{10-roomnum}}</span>间</div>
                  <div class="rooms_total_price" id="rooms_total_price">共￥{{roomnum*price*totaldata}}元</div>
                </div>
              </div>
            </div>
            <div id="personal_info" class="block">
              <div class="title">
                <span class="tip"></span>
                <span>个人信息</span>
              </div>
              <div class="block_line"></div>
              <!--住客姓名-->

              <div class="block_item tourists_name">
                <div class="block_item_title"><span><em>*</em>住客姓名：</span></div>
                <div class="block_item_content">
                  <input class="contact_name" id="contact_name" placeholder="填写一位入住人姓名" maxlength="6" v-model="name">
                </div>
              </div>


              <!--联系方式-->
              <div class="block_item tourists_contact">
                <div class="block_item_title"><span><em>*</em>身份证：</span></div>
                <div class="block_item_content">
                  <input id="tel_phone" placeholder="填写入住人的身份证" maxlength="18" v-model="id">
                </div>
              </div>
              <div class="block_item tourists_email">
                <div class="block_item_title"><span><em>*</em>电子邮箱：</span></div>
                <div class="block_item_content">
                  <input id="email" v-model="qqemail" readonly="readonly">
                </div>
              </div>
            </div>
            <div class="block order_rule hide" style="display: block;">
              <div class="title">
                <span>预订规则</span>
              </div>
              <div class="block_line"></div>
              <div class="desc">
                <span><label class="cancel_marked_no">不可取消</label>订单提交需支付全部房费，如订单不确认将全额退款至您的付款账户。订单确认后不可取消修改，如未入住或取消修改及提前离店，我们将收取您全部金额支付酒店。</span>
              </div>
            </div>
            <div class="block submit_order" style="width: 100%;">
              <div class="reselect">
                <a href="javascript:history.back(-1)">&lt;重新选择房型</a>
              </div>

              <input id="g_next" class="submit_button" value="提交订单" type="button" @click="saveorder">
              <div class="booking_notes">
                <div id="acceptBookingNotes" class="tip accept selected"></div>
                我接受
                <a>《酒店预订须知》</a>
              </div>
            </div>
          </div>
          <div id="right_column">
            <div class="hotel_info" style="margin-top: 11px;">
              <div class="hotel_name">{{chinesename}}</div>
              <div class="hotel_address">地址：{{address}}</div>
            </div>
            <div class="line1"></div>
            <div class="room_info" id="room_info">
              <div class="room_name">{{roomNameCn}}</div>
              <div class="bed_type"><span>床型：&nbsp;</span>{{bedtype}}</div>
              <div class="floor"><span>楼层：&nbsp;</span>2层</div>
              <div class="area"><span>面积：&nbsp;</span>30㎡</div>

              <div class="base_num"><span>最多入住人数：&nbsp;</span>2</div>

              <div class="network"><span>宽带：&nbsp;</span>免费无线</div>
              <div class="window"><span>窗户：&nbsp;</span>无</div>
              <div class="pay_tip"><span>支付：&nbsp;</span>收取全部费用</div>
              <div><span>加床：&nbsp;</span>请提前咨询酒店前台</div>
            </div>

            <!--支付信息-->
            <div class="price_info">
              <div class="info" style="display: block;">
                <!--支付信息-->
                <div id="pay_info">
                  <div class="pay_type">网上支付</div>

                  <div class="total">
                    <span class="e">总计</span>
                    <span class="price" id="price">￥{{roomnum*price*totaldata}}</span>
                  </div>
                  <div class="line"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "pay",
      data(){
          return{
            hotelid: this.$route.query.hotelid,
            chinesename:this.$route.query.chinesename,
            address:this.$route.query.address,
            startvalue:this.$route.query.startvalue,
            bedtype:this.$route.query.bedtype,
            price: this.$route.query.price,
            tel:this.$route.query.tel,
            roomNameCn:this.$route.query.roomNameCn,
            days:1,
            id:'',
            name:'',
            form: {
              name: '',
              region: '',
              date1: '',
              date2: '',
              delivery: false,
              type: [],
              resource: '',
              desc: ''
            },
            roomnum:1
          }
      },
      methods:{
        decrease(){this.roomnum--;if(this.roomnum<=0){this.roomnum=1;console.log(this.startvalue)}},
        increase(){this.roomnum++;if (this.roomnum>10){this.roomnum=10;}},
        saveorder(){
          let rex_name=/^[\u4e00-\u9fa5]{2,4}$/;
          let rex_id=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
          if(this.form.date2==''||this.form.date1==''){alert("请输入酒店入离日期");}
          else if(this.name==null||this.name=="")
          {alert("请输入一位住客的姓名");}
          else if(!rex_name.test(this.name))
          {alert("请输入正确的姓名");}
          else if(this.id==null||this.id=="")
          {alert("请输入身份证");}
          else if(!rex_id.test(this.id))
          {alert("请输入正确的身份证");}
          else{
            this.$axios.post('/payfinish.do',{
              hotelid: this.hotelid,
              user:sessionStorage.getItem("loginemail"),
              startvalue:this.form.date1,
              endvalue:this.form.date2,
              num:this.roomnum,
              name:this.name,
              id:this.id,
              totalprice:this.price*this.roomnum*this.days,
              chinesename:this.chinesename,
              roomtype:this.roomNameCn
            }).then(result =>
            {
              alert("预定成功");
              this.$router.push({path: '/usercenter'});
            }).catch(err => {
              console.log('err', err)
            })
          }
        }
      },
      created() {

      },
      computed:{
          qqemail(){return sessionStorage.getItem("loginemail")+"@qq.com";},
          totaldata()
          {
            this.days=(new Date(Date.parse(this.form.date2.replace(/-/g, '/')))-new Date(Date.parse(this.form.date1.replace(/-/g, '/'))))/(1*24*60*60*1000);
            if (this.days<0||this.days>30||isNaN(this.days)){return 1;}
            return this.days;
          }
      }
    }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css");

  .wrap { width: 991px; margin: 0px auto 0px auto; }
  .orderBg { background: #f0f4fb; width: 100%; font-family: 'Microsoft YaHei'; padding-top: 23px; }
  .content_title { margin: 0px 0px 20px 0px; width: 991px; }
  .content_title .hotel_name { color: #f80; font-size: 18px; font-weight: bolder; max-width: 630px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; } .content_title div { display: inline-block; }
  .content_title .dream_guarantee { float: right; } .content_title div { display: inline-block; }
  .content_title .dream_guarantee .bolder { font-weight: bolder; display: inline-block; font-size: 14px; color: #999; vertical-align: middle; cursor: pointer; }
  .content_title .dream_guarantee span { display: inline-block; font-size: 14px; color: #999; vertical-align: middle; }
  .container { display: inline-block; width: 710px; margin-bottom: 50px; }
  #container .block { margin: 0px 0px 10px 0px; background-color: #fff; padding-bottom: 10px; }
  #container .block .title { height: 38px; padding-left: 15px; }
  #container .block .title span { display: inline-block; vertical-align: middle; font-size: 16px; line-height: 38px; color: #333; font-weight: bolder; }
  #container .block_line { background: #f0f0f0; width: 100%; height: 2px; }
  .block_item { margin: 15px 0px 15px 0px; }
  .block_item_title { display: inline-block; width: 114px; vertical-align: middle; }
  .block_item_title span { display: inline-block; float: right; font-size: 14px; color: #333; }
  .block_item_content { display: inline-block; vertical-align: middle; font-size: 14px; color: #666; width: 552px; }
  .date .date_select { display: inline-block; position: relative; }
  input.input_date { border: 1px solid #ddd; width: 148px; height: 30px; cursor: default; padding-left: 10px; line-height: 30px; vertical-align: middle; }
  .date .week { position: absolute; top: 6px; right: 10px; color: #ccc; }
  .date .duration { display: inline-block; margin-left: 10px; }
  .block_item { margin: 15px 0px 15px 0px; }
  .block_item_title { display: inline-block; width: 114px; vertical-align: middle; }
  .block_item_title span { display: inline-block; float: right; font-size: 14px; color: #333; }
  .block_item_content { display: inline-block; vertical-align: middle; font-size: 14px; color: #666; width: 552px; }
  .rooms .room_select { display: inline-block; position: relative; }
  #room_num { display: inline-block; border: 1px solid #ddd; width: 38px; height: 28px;text-align: center;margin-top: -31px;}
  a { text-decoration: none; color: #404040;}
  .rooms .remaining_rooms { margin-left: 10px; }
  .rooms .remaining_rooms span { color: #f60; }
  .rooms .rooms_total_price { display: inline-block; font-size: 16px; color: #f60; font-weight: bolder; margin-left: 16px; }
  .block_item { margin: 15px 0px 15px 0px; }
  .block_item_title { display: inline-block; width: 114px; vertical-align: middle; }
  .block_item_title span { display: inline-block; float: right; font-size: 14px; color: #333; }
  .block_item_title em { color: #ff7c7c; }
  #tourists_name .block_item_content { position: relative; } .block_item_content { display: inline-block; vertical-align: middle; font-size: 14px; color: #666; width: 552px; }
  .tourists_name input { border: 1px solid #ddd; width: 238px; height: 32px; padding: 7px 10px 7px 10px; } .grey { color: #ccc; }
  #intl_code { display: inline-block; border: 1px solid #ddd; height: 30px; width: 18px; margin-right: 10px; padding: 0 10px; line-height: 30px; }
  #tel_phone { display: inline-block; border: 1px solid #ddd; width: 238px; height: 30px; padding: 0px 10px; line-height: 30px; }
  #email { display: inline-block; border: 1px solid #ddd; width: 238px; height: 32px; padding: 7px 10px 7px 10px; }
  .order_rule .desc { padding: 20px 64px 20px 44px; }
  .order_rule .desc span { display: inline-block; font-size: 14px; color: #666; }
  .order_rule .desc span .cancel_marked_no { font-size: 16px; color: #fff; background: #fb6649; border-radius: 3px; padding: 0 6px; margin-right: 7px; }
  .submit_order { height: 82px; background: #fefee6; background-color: rgb(254, 254, 230); border: 1px solid #eddbb3; }
  .submit_order .reselect { float: left; margin: 32px auto 32px 38px; }
  .submit_order .reselect a { font-size: 14px; color: #f80; }
  .submit_button { float: right; width: 120px; height: 40px; margin: 23px 20px 20px auto; font-size: 18px; color: #fff; background-color: #f80; text-align: center; line-height: 40px; border-radius: 2px; font-weight: bolder; cursor: pointer; border: 0 none; }
  .submit_order .booking_notes { float: right; margin: 32px 13px 32px auto; font-size: 14px; color: #999; position: relative; }
  .submit_order .booking_notes .selected { background-position: -64px 0px; } .submit_order .booking_notes .accept { display: inline-block; width: 14px; height: 14px; vertical-align: middle; cursor: pointer; background-position: -46px -48px; }
  .submit_order .booking_notes a { color: #f80; cursor: pointer; }

  #right_column { width: 270px; float: right; background-color: #fff; }
  #right_column .hotel_name { margin: 10px auto 0px 10px; color: #f80; font-size: 14px; font-weight: bolder; }
  #right_column .hotel_address { margin-left: 10px; color: #9e9f9f; font-size: 14px; }
  #right_column .line1 { background: #ddd; width: 269px; height: 1px; margin: 10px auto 10px auto; }
  #right_column .room_info { padding: 5px 0px 5px 10px; }
  #right_column .room_info .room_name { width:100%;color: #333; font-weight: bolder; font-size: 15px; margin-bottom: 10px; } #right_column .room_info div { margin: 2.5px auto 2.5px 0px; margin-bottom: 2.5px; font-size: 14px; color: #666; }
  #right_column .room_info div { width:100%;margin: 2.5px auto 2.5px 0px; font-size: 14px; color: #666; }
  #right_column .room_info div span { color: #645858; font-weight: bolder; }
  #right_column .price_info { background: #fefee6; box-shadow: 0px 1px 0px 0px rgba(0,0,0,0.08); border-top: solid 1px #eddbb3; }
  #pay_info { margin: 16px 11px; }
  #pay_info .pay_type { width: 100%;font-size: 18px; color: #333; font-weight: bolder; }
  #pay_info .line { background: #eddbb3; width: 250px; height: 1px; }
  #pay_info .total { margin-top: 20px; width: 100%;}
  #pay_info .total .e { font-size: 18px; color: #333; font-weight: bolder; margin-left: 21px; }
  #pay_info .total .price { float: right; margin-right: 23px; font-size: 18px; color: #f60; font-weight: bolder; }

  .num-jian,.num-jia {
    display: inline-block;
    width: 28px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    font-size: 18px;
    color: #999;
    cursor: pointer;
    border: 1px solid #e6e6e6;
    background-color: #fff;
  }
</style>
