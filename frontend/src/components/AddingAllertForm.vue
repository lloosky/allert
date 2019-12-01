<template>
  <div class="addingAllertForm" id="allert_form">
    <form>
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
    </form>
    <div class="formButtons buttonPadding">
      <button class="clearButton" @click="closeForm">Zamknij</button>
      <button class="clearButton" @click="clearInputs">Wyczyść</button>
      <button class="normalButton" @click="addAllert">Dodaj</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AllertForm",
  data() {
    return {
      email: "",
      productURL: "",
      type: "",
      value: "",
      allerts: this.$store.state.allerts,
      showAddingForm: false
    };
  },
  methods: {
    addAllert() {
      this.allerts.push({
        email: this.email,
        productURL: this.productURL,
        type: this.type,
        value: this.value,
        productIMG: "phone.png",
        id: this.randomIdGenerator()
      });
      console.log(this.allerts);
      window.scrollTo(0, 0);
      this.$emit("confirm", this.showAddingForm);
    },
    closeForm() {
      this.$emit("confirm", this.showAddingForm);
      this.clearInputs();
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
<style scoped>
.addingAllertForm {
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.685);
  display: grid;
  grid-template-columns: 50%;
  -ms-flex-line-pack: center;
  align-content: center;
  position: absolute;
  top: 0;
  right: 0;
  z-index: inherit;
  justify-content: center;
}
.addingAllertForm > form,
.buttonPadding {
  background-color: white;
  padding: 10% 10% 0% 10%;
}
.buttonPadding {
  padding: 0% 10% 10% 10%;
  display: grid;
  grid-template-columns: auto auto auto;
  grid-gap: 10px;
  justify-items: center;
}
.normalButton,
.clearButton {
  width: 100%;
}
</style>
