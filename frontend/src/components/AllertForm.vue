<template>
  <div>
    <div class="allertForm" id="allert_form">
      <form onsubmit="return false;">
        <label for>Podaj adres email</label>
        <input type="email" placeholder="adres email" v-model="email" />
        <label for>Podaj adres produktu</label>
        <input type="url" placeholder="adres URL produktu" v-model="productURL" />
        <label for>Podaj typ znizki</label>
        <select type="email" v-model="type">
          <option value>Wybierz typ</option>
          <option value="Procentowa">Procentowa</option>
          <option value="Kwotowa">Kwotowa</option>
        </select>
        <label for>Podaj wartość</label>
        <input type="number" placeholder="wartość zniżki" v-model="value" />
        <div class="formButtons">
        <button class="clearButton" @click="clearInputs">Wyczyść</button>
        <button class="normalButton" @click="addAllert">Dodaj</button>
      </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "AllertForm",
  data() {
    return {
      showRouterView: false,
      email: "",
      productURL: "",
      type: "",
      value: "",
      allerts: this.$store.state.allerts
    };
  },
  methods: {
    addAllert() {
      const rightSide = document.getElementById("rightHomepage");
      const leftSide = document.getElementById("leftHomepage");
      const allertList = document.getElementById("allertsList");
      rightSide.style.top = -100 + "vw";
      leftSide.style.top = -100 + "vw";
      allertList.style.left = 0 + "vw";
      this.allerts.push({
        email: this.email,
        productURL: this.productURL,
        type: this.type,
        value: this.value,
        productIMG: "phone.png",
        id: this.randomIdGenerator()
      });
      this.clearInputs();
      console.log(this.allerts);
      window.scrollTo(0, 0);
    },
    clearInputs() {
      this.email = "";
      this.productURL = "";
      this.type = "";
      this.value = "";
    },
    randomIdGenerator() {
      let arr = [];
      arr.push(Math.floor(Math.random() * 9999));
      for (let i in this.allerts) {
        if (arr[0] == this.allerts[i].id) {
          arr[0] = this.allerts[i].id + 1;
        }
      }
      return arr[0]
    }
  },
  created() {}
};
</script>
<style>
.allertForm {
  width: 40vw;
  height: 70vh;
  background-color: white;
  box-shadow: 0px 0px 15px 0px #afafaf;
  display: grid;
  grid-template-columns: 80%;
  align-content: center;
  position: relative;
  justify-content: center;
}
form,
.formButtons {
  justify-self: center;
}
label,
input,
select {
  width: 100%;
}
input,
select {
  height: 40px;
  margin-bottom: 7%;
  padding-left: 1%;
}
::placeholder {
  color: rgb(170, 170, 170);
}
button {
  width: 50%;
}
button {
  border: none;
  height: 40px;
}
.clearButton {
  color: #ff530d;
  background-color: transparent;
  transition: 0.3s all;
}
.clearButton:hover {
  background-color: #00000027;
}
.normalButton {
  background-color: #96ca2d;
  color: white;
  transition: 0.3s all;
}
.normalButton:hover {
  background-color: #8eb936;
}

@media (min-width: 992px) and (max-width: 1199px) {
 .allertForm {
   height: 70vh;
 }
}
@media (max-width:768px) {
  .allertForm {
    width: 80vw;
  }
}
</style>
