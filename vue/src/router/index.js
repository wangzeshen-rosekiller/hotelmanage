import Vue from 'vue'
import Router from 'vue-router'
import userlogin from '../components/userlogin'
import Home from '../components/Home'
import Homedetail from '../components/Homedetail';
import usercenter from '../components/usercenter'
import comment from '../components/comment'
import pay from '../components/pay'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/userlogin',
      name: 'userlogin',
      component: userlogin
    },
    {
      path:'/detail',
      name:'detail',
      component:Homedetail
    },
    {
      path:'/usercenter',
      name:'usercenter',
      component:usercenter
    },
    {
      path:'/comment',
      name:'comment',
      component:comment
    },
    {
      path:'/pay',
      name:'pay',
      component:pay
    }
  ]
})
