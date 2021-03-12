<template>
  <div>
    <Hometop></Hometop>
    <div class="block">
      <el-carousel height="340px">
        <el-carousel-item v-for="item in 2" :key="item">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="search">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="目的地">
          <el-input v-model="form.name" placeholder="选择城市"></el-input>
        </el-form-item>
        <el-form-item label="入住时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 217%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="离开时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" style="width: 217%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="关键字">
          <el-input type="text" v-model="form.desc" placeholder="请输入关键字（选填）"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" class="Homesearch">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="box">
      <div class="boxtop">
        <h2>酒店</h2>
        <a>更多超值酒店</a>
      </div>
      <div class="boxmain">
        <div class="boxmaintop">
          入住城市：
          <button v-for="(item,index) in citylist" @click="changecity(index)"
                  :class="{ buttonBackground:changeLeftBackground == index}">{{item}}</button>
        </div>
        <div class="boxmainbuttom">
          <div class="boxmainleft">
              <ul>
                <li v-for="(item,index) in datalist" v-if="index<8">
                  <a @click="gotodetail(index)" style="cursor: pointer">
                    <div>
                      <img :src="item.picture" class="img">
                    </div>
                    <div class="total">
                      <div class="title2">{{item.chineseName}}</div>
                      <div class="level">{{item.starName}}</div>
                      <div class="price">
                        <em>￥</em>
                        <span class="money">{{item.price}}</span>
                        <span>起</span>
                      </div>
                    </div>
                  </a>
                </li>
              </ul>
          </div>
            <div class="hotel-filter">
              <div class="mod">
                <div class="title">价位</div>
                <ul class="list clearfix">
                  <li><a href="javascript:void(0)">不限</a></li>
                  <li><a href="javascript:void(0)">100元以下</a></li>
                  <li><a href="javascript:void(0)">100-300元</a></li>
                  <li><a href="javascript:void(0)">300-600元</a></li>
                  <li><a href="javascript:void(0)">600-1500元</a></li>
                  <li><a href="javascript:void(0)">1500元以上</a></li>
                </ul>
              </div>
              <div class="mod">
                <div class="title">商圈</div>
                <ul class="list clearfix">
                  <li><a href="javascript:void(0)">北京</a></li>
                  <li><a href="javascript:void(0)">上海</a></li>
                  <li><a href="javascript:void(0)">广州</a></li>
                  <li><a href="javascript:void(0)">深圳</a></li>
                  <li><a href="javascript:void(0)">杭州</a></li>
                  <li><a href="javascript:void(0)">成都</a></li>
                </ul>
              </div>
              <div class="mod">
                <div class="title">品牌</div>
                <ul class="list clearfix">
                  <li><a href="javascript:void(0)">北京</a></li>
                  <li><a href="javascript:void(0)">上海</a></li>
                  <li><a href="javascript:void(0)">广州</a></li>
                  <li><a href="javascript:void(0)">深圳</a></li>
                  <li><a href="javascript:void(0)">杭州</a></li>
                  <li><a href="javascript:void(0)">成都</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
    </div>
    <div class="box">
      <div class="boxtop">
        <h2>酒店</h2>
        <a>更多超值酒店</a>
      </div>
      <div class="boxmain">
        <div class="boxmaintop">
          入住城市：
          <button v-for="(item,index) in citylist" @click="changecity(index)"
                  :class="{ buttonBackground:changeLeftBackground == index}">{{item}}</button>
        </div>
        <div class="boxmainbuttom">
          <div class="boxmainleft">
            <ul>
              <li v-for="(item,index) in datalist" v-if="index<8">
                <a href="">
                  <div>
                    <img :src="item.picture" class="img">
                  </div>
                  <div class="total">
                    <div class="title2">{{item.chineseName}}</div>
                    <div class="level">{{item.starName}}</div>
                    <div class="price">
                      <em>￥</em>
                      <span class="money">{{item.price}}</span>
                      <span>起</span>
                    </div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <div class="hotel-filter">
            <div class="mod">
              <div class="title">价位</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">不限</a></li>
                <li><a href="javascript:void(0)">100元以下</a></li>
                <li><a href="javascript:void(0)">100-300元</a></li>
                <li><a href="javascript:void(0)">300-600元</a></li>
                <li><a href="javascript:void(0)">600-1500元</a></li>
                <li><a href="javascript:void(0)">1500元以上</a></li>
              </ul>
            </div>
            <div class="mod">
              <div class="title">商圈</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">北京</a></li>
                <li><a href="javascript:void(0)">上海</a></li>
                <li><a href="javascript:void(0)">广州</a></li>
                <li><a href="javascript:void(0)">深圳</a></li>
                <li><a href="javascript:void(0)">杭州</a></li>
                <li><a href="javascript:void(0)">成都</a></li>
              </ul>
            </div>
            <div class="mod">
              <div class="title">品牌</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">北京</a></li>
                <li><a href="javascript:void(0)">上海</a></li>
                <li><a href="javascript:void(0)">广州</a></li>
                <li><a href="javascript:void(0)">深圳</a></li>
                <li><a href="javascript:void(0)">杭州</a></li>
                <li><a href="javascript:void(0)">成都</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="box">
      <div class="boxtop">
        <h2>酒店</h2>
        <a>更多超值酒店</a>
      </div>
      <div class="boxmain">
        <div class="boxmaintop">
          入住城市：
          <button v-for="(item,index) in citylist" @click="changecity(index)"
                  :class="{ buttonBackground:changeLeftBackground == index}">{{item}}</button>
        </div>
        <div class="boxmainbuttom">
          <div class="boxmainleft">
            <ul>
              <li v-for="(item,index) in datalist" v-if="index<8">
                <a href="">
                  <div>
                    <img :src="item.picture" class="img">
                  </div>
                  <div class="total">
                    <div class="title2">{{item.chineseName}}</div>
                    <div class="level">{{item.starName}}</div>
                    <div class="price">
                      <em>￥</em>
                      <span class="money">{{item.price}}</span>
                      <span>起</span>
                    </div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <div class="hotel-filter">
            <div class="mod">
              <div class="title">价位</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">不限</a></li>
                <li><a href="javascript:void(0)">100元以下</a></li>
                <li><a href="javascript:void(0)">100-300元</a></li>
                <li><a href="javascript:void(0)">300-600元</a></li>
                <li><a href="javascript:void(0)">600-1500元</a></li>
                <li><a href="javascript:void(0)">1500元以上</a></li>
              </ul>
            </div>
            <div class="mod">
              <div class="title">商圈</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">北京</a></li>
                <li><a href="javascript:void(0)">上海</a></li>
                <li><a href="javascript:void(0)">广州</a></li>
                <li><a href="javascript:void(0)">深圳</a></li>
                <li><a href="javascript:void(0)">杭州</a></li>
                <li><a href="javascript:void(0)">成都</a></li>
              </ul>
            </div>
            <div class="mod">
              <div class="title">品牌</div>
              <ul class="list clearfix">
                <li><a href="javascript:void(0)">北京</a></li>
                <li><a href="javascript:void(0)">上海</a></li>
                <li><a href="javascript:void(0)">广州</a></li>
                <li><a href="javascript:void(0)">深圳</a></li>
                <li><a href="javascript:void(0)">杭州</a></li>
                <li><a href="javascript:void(0)">成都</a></li>
              </ul>
            </div>
          </div>
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
    name: "Home",
    components: {
      Hometop,
      Homebottom
    },
    data() {
      return {
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
        datalist:[],
        city:'上海',
        citylist:['上海','杭州','深圳','广州'],
        changeLeftBackground:0
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      getdatalist(){
        this.$axios.get('http://route.showapi.com/1653-1',{
          params:{
            "showapi_appid" : '', //这里需要改成自己的appid
            "showapi_sign" : '', //这里需要改成自己的应用的密钥secret
            "keyWords" : "",
            "page" : "0",
            "cityName" : this.city,
            "inDate" : "",
            "outDate" : "",
            "sortCode" : "",
            "returnFilter" : "",
            "star" : "",
            "feature" : "",
            "minPrice" : "",
            "maxPrice" : "",
            "facility" : "",
            "hotellabels" : "",
            "limit":"30"}
        }).then(result => {
          //console.log(result.data.showapi_res_body.data.hotelList);
          this.datalist=result.data.showapi_res_body.data.hotelList;
        }).catch(err => {
          console.log('err', err)
        })
      },
      changecity(index){
        this.city=this.citylist[index];
        this.changeLeftBackground = index;
        this.getdatalist();
      },
      gotodetail(item){
        console.log(item);
        console.log(this.datalist.item);
        console.log(this.datalist[item]);
        this.$router.push({path: '/detail',query:{hotelid:this.datalist[item].hotelId}});
      }
    },
    created() {
      this.getdatalist();
    }
  }
</script>

<style scoped>
  @import url("//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css");
  ul,li,a{
    list-style: none;
    margin: 0;
    padding: 0;
    text-decoration:none;
  }

  .boxmaintop button{
    margin: 0 5px;
    border: none;
    width: 45px;
    height: 25px;
  }

  .el-carousel__item:nth-child(2n) {
    background: url("../../dist/img/xiecheng1.jpg") right;
  }

  .el-carousel__item:nth-child(2n+1) {
    background: url("../../dist/img/xiecheng2.jpg") right;
  }
  .Homesearch{
    background-color: #f80;
  }
  .block{
    position: relative;
  }
  .search{
    position: absolute;
    top: 155px;
    background-color: #fffdd7;
    z-index: 999;
    height: 279px;
    width: 30%;
    padding: 30px 13px;
    border: 1px black solid;
    margin-left: 31px;
    border-top: none;
  }
  .box{
    width: 85%;
    margin: 32px auto;
    background: #fff;
    color: #3d3d3d;
    box-shadow: 0 2px 1px #e1e1e1;
  }
  .boxtop{
    height: 48px;
    background: #fbfbfb;
    padding: 0 12px;
  }
  .boxtop h2{
    float: left;
    margin-top: 12px;
    font-size: 18px;
    padding-left: 24px;
  }
  .boxtop a{
    float: right;
    padding-right: 15px;
    line-height: 48px;
  }
  .boxmaintop{
    padding-left: 16px;
    margin: 17px 0;
    height: 20px;
  }
  .boxmaintop a{
    text-decoration: none;
    display: inline-block;
    height: 20px;
    line-height: 20px;
    padding: 0 6px;
    color: #e90;
  }
  .boxmainbuttom{
    padding: 0 12px 6px;
  }
  .boxmain{
    height: 547px;
  }
  .boxmainleft{
    float: left;
    width: 80%;
  }
  .boxmainleft ul li{
    float: left;
    width: 228px;
    margin: 0 6px 6px 0;
    position: relative;
  }
  .boxmainleft ul li a{
    color: #3d3d3d;
    display: block;
  }
  .img{
    height: 150px;
    overflow: hidden;
    width: 100%;
  }
  .total{
    border: 1px solid #f2f3f4;
    padding-top: 12px;
    height: 80px;
  }
  .title2{
    font-size: 14px;
    margin: 0 12px 5px;
    height: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .level{
    color: #a5a5a5;
    margin: 0 0 6px 12px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .price{
    padding: 0 12px 12px;
    overflow: hidden;
    float: left;
    height: 18px;
    font-size: 16px;
    font-family: pingfang sc,Arial;
    line-height: 1;
    color: #ff5741;
  }
  .price em{
    font-size: 12px;
    font-style: normal;
  }
  .hotel-filter {
    width: 228px;
    font-size: 14px;
    line-height: 2;
    background: #fafafa;
    float: right;
  }
  .hotel-filter .mod {
    height: 163px;
  }
  .hotel-filter .title {
    border-top: 6px solid #ffc900;
    color: #e90;
    font-size: 16px;
    padding-top: 6px;
    text-indent: 12px;
  }
  .hotel-filter .list {
    padding-left: 12px;
  }
  .hotel-filter .list li {
    float: left;
    width: 108px;
    height: 28px;
    white-space: nowrap;
  }
  .hotel-filter a {
    color: #666;
    list-style: none;
  }
  .buttonBackground{
    background-color: #f80;
    color: white;
  }
</style>
