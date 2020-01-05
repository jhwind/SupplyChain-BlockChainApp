<template>
  <div class="msg">
    {{msg}}
  </div>
</template>


<script>
export default {
  name:"TipLayer",
  data(){
    return {
        msg: null
    }
  }
}
</script>

<style scoped>
.msg{
  position:fixed;
  left:50%;
  top:50%;
  transform:translate(-50%,-50%);
  background-color: rgba(0,0,0,0.5);
  color: white;
  padding:0.5rem 1rem;
  border-radius: 10px;
  animation: TipLayer 2s;
}
@keyframes TipLayer {
  from{
    transform:translate(-50%,-50%) scale(0);
    opacity: 0;
  }
  25%{
    transform:translate(-50%,-50%);
    opacity: 1;
  }
  75%{
      transform:translate(-50%,-50%);
      opacity: 1;
    }
  to{
    transform:translate(-50%,-50%) scale(0);
    opacity: 0;
  }
}
</style>
