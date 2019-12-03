<template>
  <div class="edit-page-form" :style="{display: displayOfEditForm}">
    <form onsubmit="return false;">
      <label for>Podaj adres email</label>
      <input type="email" :placeholder="allerts[this.newId].email" v-model="email" />
      <label for>Podaj adres produktu</label>
      <input type="url" :placeholder="allerts[this.newId].productURL" v-model="productURL" />
      <label for>Podaj typ znizki</label>
      <select type="email" v-model="type">
        <option value>Wybierz typ</option>
        <option value="Procentowa">Procentowa</option>
        <option value="Kwotowa">Kwotowa</option>
      </select>
      <label for>Podaj wartość</label>
      <input type="number" :placeholder="allerts[this.newId].value" v-model="value" />
      <div class="formButtons buttonsEdit">
      <button class="clearButton" @click="closeEditPage">Zamknij</button>
      <button class="clearButton" @click="clearInputs">Wyczyść</button>
      <button class="normalButton" @click="confirmEdit">Zapisz</button>
    </div>
    </form>
    
  </div>
</template>
<script>
export default {
  data() {
    return {
      displayOfEditFormUpdated: "none",
      email: "",
      productURL: "",
      type: "",
      value: "",
      allerts: this.$store.state.allerts
    };
  },
  props: {
    newId: Number,
    displayOfEditForm: String
  },
  methods: {
    clearInputs() {
      this.email = "";
      this.productURL = "";
      this.type = "";
      this.value = "";
    },
    confirmEdit() {
      this.$emit("confirmEdit", this.displayOfEditFormUpdated);
      const all_item = this.allerts[this.newId];

      all_item.email = this.email;
      all_item.productURL = this.productURL;
      all_item.type = this.type;
      all_item.value = this.value;
      this.clearInputs();
    },
    closeEditPage() {
      this.$emit("confirmEdit", this.displayOfEditFormUpdated);
    }
  },
  destroyed() {
      this.closeEditPage();
  }
};
</script>
<style>
.edit-page-form {
    display: grid;
    grid-template-columns: 80%;
    grid-template-rows: 100%;
    align-items: center;
    justify-content: center;
}
.edit-page-form > form > select,
.edit-page-form > form > input {
  margin-bottom: 3%;
}
.buttonsEdit {
  display: grid;
  justify-items: center;
  grid-template-columns: auto auto auto;
}
.buttonsEdit > button {
  width: 100%;
}
</style>