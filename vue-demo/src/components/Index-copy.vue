<template>
  <div class="index">
    <div>
  <!--     <transition name="slide-fade">
        <div class="bar" v-show="isDisplay">
          <div class="container">
            <div class="content">
              {{aletMsg}}
            </div>
          </div>
        </div>
      </transition> -->
      <input class="basic_css" v-model="registerInfo.name" placeholder=" name">
      <input class="basic_css" v-model="registerInfo.tag" placeholder=" tag">
      <input class="basic_css" v-model="registerInfo.value" placeholder=" value">
      <button class="basic_css" @click="registerClick"> register </button>
    </div>
    <div>
      <input class="basic_css" v-model="registerInfo.name" placeholder=" name">
      <input class="basic_css" v-model="registerInfo.tag" placeholder=" tag">
      <input class="basic_css" v-model="registerInfo.value" placeholder=" value">
      <button class="basic_css" @click="registerClick"> register </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Index',
  data () {
    return {
      registerInfo: {
        name: '',
        tag: '',
        value: ''
      },
      aletMsg: 'look at me',
      isDisplay: false
    }
  },
  methods: {
    registerClick: function () {
      var self = this
      axios
        .post('/api/register', {
          params: {
            data: self.registerInfo,
            testEx: 'hello'
          }
        })
        .then(function (res) {
          console.log(res)
          self.tip = res.data.isSuccess
          // self.isDisplay = true
          self.aletMsg = res.data.log
          alert(self.aletMsg)
          // window.setTimeout(() => {
          //   self.isDisplay = false
          // }, 3000)
        })
        .catch(function (error) {
          console.log('Fail\n', error)
        })
    }
  }
  // mounted () {
  //   var self = this
  //   axios
  //     .get('/api/test/test')
  //     .then(function (res) {
  //       // console.log(res)
  //       self.msg = res.data
  //     })
  //     .catch(function (error) {
  //       console.log(error)
  //       console.log('Fail!!!')
  //     })
  // }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.basic_css {
  font-family: monospace;
}
.index {
  font-family: monospace;
}
h1 {
  font-weight: normal;
  .alsrtInfo{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 10;
    background: rgba(0, 0, 0, 0.1);
    .profPrompt_test{
      padding: 30px 10px;
      width: 480px;
      overflow: hidden;
      line-height: 28px;
      border: 1px solid #4eb6d3;
      color: #4eb6d3;
      position: absolute;
      background-color: #fbfbfb;
      top: 356px;
      left: 50%;
      font-size: 14px;
      font-family: Gotham-Book;
      opacity: 1;
      /* z-index: 1; */
      text-align: center;
      transform: translate(-50%, -50%);
    }
  }
}
.bar {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: 9999;
}
.container {
  width: 500px;
  height: 100px;
  background: #FFFFFF;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 25px;
/*  display: table-cell;
  vertical-align: middle;*/
}
.content {
  color: #FF0000;
  font-size: 15px;
  padding: 0 10px;
  box-sizing: border-box;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>
