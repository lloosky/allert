<template>
  <div class="confirm-container">
    <div class="confirm-box">
      <h3>{{confirmMsg}}</h3>
      <div>
        <button class="clearButton" @click="$emit('decline')">Nie</button>
        <button class="normalButton" @click="$emit('accept')">Tak</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  props: { confirmMsg: String },
  methods: {}
};
</script>

<style scoped>
.confirm-container {
  display: grid;
  grid-template-columns: auto;
  grid-template-rows: auto;
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: inherit;
  top: 0%;
  left: 0%;
  background-color: rgba(0, 0, 0, 0.685);
}
.confirm-box {
  display: grid;
  padding: 20px;
  width: 50%;
  height: 30%;
  background-color: #ffffff;
  justify-self: center;
  grid-template-rows: 50% 50%;
  color: #212529;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  align-self: center;
}
h3 {
  text-align: center;
}
button {
  width: 50%;
  justify-self: right;
}
.decline-button {
  justify-self: left;
}
@media (max-width:768px) {
  .confirm-box {
    width: 80%;
  }
}
</style>
