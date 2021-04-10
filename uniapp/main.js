import Vue from 'vue'
import App from './App'
import joystick from './pages/index/joystick.vue'
Vue.config.productionTip = false
Vue.component('joy-stick',joystick)
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
