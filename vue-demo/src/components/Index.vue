<template>
  <div class="index">
    <h2>{{tipMsg}}</h2>
    <div class="block">
      <input class="basic_css" v-model="Info.RRname" placeholder=" Register's name">
      <input class="basic_css" v-model="Info.Rtag" placeholder=" tag: 0/1/2">
      <input class="basic_css" v-model="Info.Rvalue" placeholder=" Left-value">
      <button class="basic_css" v-on:click="Click('register')"> Register </button>
    </div>
    <div class="block">
      <input class="basic_css" v-model="Info.CCname" placeholder=" Charger's name">
      <input class="basic_css" v-model="Info.Cvalue" placeholder=" Charge-value">
      <button class="basic_css" v-on:click="Click('charge')"> Charge </button>
    </div>
    <div class="block">
      <input class="basic_css" v-model="Info.TOname" placeholder=" Ower's name">
      <input class="basic_css" v-model="Info.TBname" placeholder=" Borrower's name">
      <input class="basic_css" v-model="Info.Tvalue" placeholder=" Borrow-value">
      <input class="basic_css" v-model="Info.Ttime_limit" placeholder=" time_limit">
      <button class="basic_css" v-on:click="Click('transaction')"> Transaction </button>
    </div>
    <div class="block">
      <input class="basic_css" v-model="Info.TrSname" placeholder=" Sharer's name">
      <input class="basic_css" v-model="Info.TrOname" placeholder=" Ower's name">
      <input class="basic_css" v-model="Info.TrBname" placeholder=" Borrower's name">
      <input class="basic_css" v-model="Info.Trvalue" placeholder=" Share-value">
      <button class="basic_css" v-on:click="Click('transfer')"> Transfer </button>
    </div>
    <div class="block">
      <input class="basic_css" v-model="Info.FOname" placeholder=" Org's name">
      <input class="basic_css" v-model="Info.FBname" placeholder=" Bank's name">
      <input class="basic_css" v-model="Info.Fvalue" placeholder=" Financing-value">
      <button class="basic_css" v-on:click="Click('financing')"> Financing </button>
    </div>
    <div class="block">
      <input class="basic_css" v-model="Info.LOname" placeholder=" Ower.name">
      <input class="basic_css" v-model="Info.LBname" placeholder=" Borrower.name">
      <button class="basic_css" v-on:click="Click('liquidation')"> Liquidation </button>
    </div>

    <div class="block" style="margin-top: 50px">
      <div class="block">
        <input class="basic_css" v-model="Info.SearchName" placeholder=" name">
        <button class="basic_css" v-on:click="searchClick('search')"> Search </button>
      </div>
      <table class="table_css" v-show="isShowResult">
        <tr>
            <th>Name</th>
            <th>Tag</th>
            <th>Address</th>
            <th>Balance</th>
            <th>leftvalue</th>
            <th>isFinance</th>
            <th>financeBank</th>
        </tr>
        <tr v-for="(org, index) in OrgList" v-bind:key="index">
            <td>{{org.name}}</td>
            <td>{{org.tag}}</td>
            <td>{{org.address}}</td>
            <td>{{org.balance}}</td>
            <td>{{org.leftvalue}}</td>
            <td>{{org.isFinance}}</td>
            <td>{{org.financeBank}}</td>
        </tr>
      </table>
    </div>

   <div class="block" style="margin-top: 50px">
      <div class="block">
        <button class="basic_css" v-on:click="getBillList('getBillList')"> Update </button>
      </div>
      <table class="table_css">
        <tr>
          <th>Index</th>
          <th>Ower</th>
          <th>Borrower</th>
          <th>BankAddress</th>
          <th>Value</th>
          <th>RpayData</th>
        </tr>
        <tr v-for="(bill, index) in BillList" v-bind:key="index">
            <td>{{index}}</td>
            <td>{{bill.owerName}}</td>
            <td>{{bill.borrowerName}}</td>
            <td>{{bill.BankAddr}}</td>
            <td>{{bill.value}}</td>
            <td>{{bill.repayData}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Index',
  data () {
    return {
      Info: {
        RRname: '',
        Rtag: '',
        Rvalue: '',
        CCname: '',
        Cvalue: '',
        TOname: '',
        TBname: '',
        Tvalue: '',
        Ttime_limit: '',
        TfSname: '',
        TfOname: '',
        TfBname: '',
        Tfvalue: '',
        FOname: '',
        FBname: '',
        Fvalue: '',
        LOname: '',
        LBname: '',
        SearchName: ''
      },
      OrgList: [],
      BillList: [],
      tipMsg: 'look at me',
      isShowResult: false
    }
  },
  methods: {
    Click: function (str) {
      var self = this
      var routeStr = '/api/' + str
      alert(routeStr)
      axios
        .post(routeStr, {
          params: {
            data: self.Info
          }
        })
        .then(function (res) {
          self.tipMsg = res.data.log
          alert(self.tipMsg)
        })
        .catch(function (error) {
          console.log('Fail\n', error)
        })
    },
    searchClick: function (str) {
      var self = this
      var routeStr = '/api/' + str
      alert(routeStr)
      axios
        .post(routeStr, {
          params: {
            data: self.Info
          }
        })
        .then(function (res) {
          // console.log('res:', res)
          self.tipMsg = res.data.log
          self.isShowResult = res.data.isSuccess
          alert(self.tipMsg)
          if (res.data.isSuccess) {
            var isChange = false
            for (var i = 0; i < self.OrgList.length; i++) {
              if (self.OrgList[i].name == res.data.body.name) {
                self.OrgList[i] = res.data.body
                isChange = true
                break
              }
            }
            if (!isChange) {
              self.OrgList.push(res.data.body)
            }
          }
        })
        .catch(function (error) {
          console.log('Fail\n', error)
        })
    },
    getBillList: function (str) {
      var self = this
      var routeStr = '/api/' + str
      alert(routeStr)
      axios
        .post(routeStr)
        .then(function (res) {
          console.log('res:', res)
          self.tipMsg = res.data.log
          // self.isShowResult = res.data.isSuccess
          alert(self.tipMsg)
          if (res.data.isSuccess) {
            self.BillList = []
            for (var i = 0; i < res.data.body.length; i++) {
              self.BillList.push(res.data.body[i])
            }
          }
        })
        .catch(function (error) {
          console.log('Fail\n', error)
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.table_css {
  font-family: monospace;
  margin: 0 auto;
  border: 1px solid black;
}
.block {
  margin: 6px;
}
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
      text-align: center;
      transform: translate(-50%, -50%);
    }
  }
}
</style>
