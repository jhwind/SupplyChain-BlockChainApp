# SupplyChain-BlockchainApp

16340216

### 目录结构

```powershell
.
├── 16340216_王健豪.pdf
├── fisco			链端
├── nodejs-sdk		后端
├── README.md		
└── vue-demo		前端

3 directories, 3 files
```

### 视频地址

[演示视频](https://www.bilibili.com/video/av82230276/)

```
# 因为视频太大，放在bilibili上了
```



### 重要文件

```powershell
# 后端启动
SupplyChain-BlockChainApp/nodejs-sdk/packages/cli/app.js
# 合约
SupplyChain-BlockChainApp/nodejs-sdk/packages/cli/contracts/SupplyChain.sol
# 前端
SupplyChain-BlockChainApp/vue-demo/src/router/index.js
```

### 启动项目

```powershell
# 一些配置
# 在SupplyChain-BlockChainApp/nodejs-sdk/packages/api/common//configuration.js 
# 44行语句，修改为自己合适的json地址
Configuration.config = '/home/wjh/nodejs-sdk/packages/cli/conf/config.json';

# 启动链端
./ SupplyChain-BlockChainApp/fisco/nodes/127.0.0.1/start_all.sh
# 启动后端
node SupplyChain-BlockChainApp/nodejs-sdk/packages/cli/app.js
# 启动前端，在SupplyChain-BlockChainApp/vue-demo下（安装cnpm，感受蜗牛和光速的距离）
cnpm install
cnpm run repoclean
cnpm run bootstrap
cnpm run dev
# 在浏览器打开
http://localhost:8080/
```