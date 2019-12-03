<template>
  <div class="allertsList" id="allertsList">
    <transition name="opacityTransition" mode="out-in">
      <app-confirm-box
        v-if="showConfirmBox"
        :confirmMsg="confirmBoxQuestion"
        @accept="confirm"
        @decline="decline"
      ></app-confirm-box>
    </transition>
    <transition name="opacityTransition" mode="out-in">
      <app-adding-form v-if="showAddingForm" @confirm="closeAddingForm"></app-adding-form>
    </transition>
    <header v-if="showList">
      <h2>Lista allertów</h2>
      <input type="text" placeholder="szukaj" v-model="search" />
      <div class="add-button" @click="addForm">
        <div class="bar"></div>
      </div>
      <div class="close-button" @click="closeAllertsList">
        <div class="bar"></div>
      </div>
    </header>
    <div class="halfs">
      <div class="left">
        <div class="left-header">
        <h5>Wszystkie allerty</h5>
        <span>Kod</span>
        <span>Sklep</span>
        <span>Produkt</span>
        <span>Edycja</span>
        <span>Usuń</span>
        </div>
        <div
          class="edit-page"
          :style="{height: heightOfEditPage + '%', opacity: opacityOfEditPage}"
        >
          <app-edit-page
            :newId="newId"
            @confirmEdit="confirmEditPage"
            :displayOfEditForm="displayOfEditForm"
          ></app-edit-page>
        </div>
        <transition-group name="opacityTransition" mode="out-in">
          <div
            class="allert"
            v-for="(allert, index) in filteredAllerts"
            :class="{'active': activeItemId === index}"
            :key="index"
            @click="showAllertInfo(allert.id, index)"
          >
            <div>
              <p>
                <strong>Adres email:</strong>
                {{allert.email}}
              </p>
              <p>
                <strong>Nazwa produktu:</strong>
                {{allert.productURL}}
              </p>
              <p>
                <strong>Allert:</strong>
                {{allert.type}} {{allert.value}}
              </p>
            </div>
            <div>
              <p class="code-of-product">
                {{allert.id}}
              </p>
            </div>
            <div class="allert-logo">
              <img src="./../../public/img/icons/mediaexpert.png" width="130px" />
            </div>
            <div class="allert-img">
              <img src="./../../public/img/icons/phone.png" width="70px" height="70px" />
            </div>
            <div class="edit-button" @click="openEdit(allert.id, index)">&#10000;</div>
            <div class="close-button small" @click="removeAllert(allert.id, index)">
              <div class="bar"></div>
            </div>
          </div>
        </transition-group>
      </div>
      <div class="right">
        <h5>Podgląd allertu</h5>
        <div class="last-allert" v-if="allerts.length > 0">
          <img src="./../../public/img/icons/phone.png" width="250px" height="250px" />
          <div>
            <p>
              <strong>Adres email:</strong>
              {{allerts[this.previewAllertId].email}}
            </p>
            <p>
              <strong>Nazwa produktu:</strong>
              {{allerts[this.previewAllertId].productURL}}
            </p>
            <p>
              <strong>Allert:</strong>
              {{allerts[this.previewAllertId].type}} {{allerts[this.previewAllertId].value}}
            </p>
            <p>
              <strong>Kod allertu: </strong>
              <span>{{allerts[this.previewAllertId].id}}</span>
            </p>
            <div class="allert-logo">
              <p>
                <strong>Sklep:</strong>
              </p>
              <img src="./../../public/img/icons/mediaexpert.png" width="200px" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EditPage from "./../components/EditPage.vue";
import ConfirmBox from "./../components/ConfirmBox.vue";
import AddingAllertForm from "./../components/AddingAllertForm.vue";

export default {
  name: "Allerts",
  data() {
    return {
      showList: true,
      allerts: this.$store.state.allerts,
      heightOfEditPage: 0,
      opacityOfEditPage: 0,
      newId: 0,
      displayOfEditForm: "none",
      right: 0,
      confirmBoxQuestion: "Na pewno chcesz usunąć ten allert ?",
      showConfirmBox: false,
      showAddingForm: false,
      allertInfo: [],
      previewAllertId: 0,
      search: "",
      activeItemId: ""
    };
  },
  methods: {
    closeAllertsList() {
      const rightSide = document.getElementById("rightHomepage");
      const leftSide = document.getElementById("leftHomepage");
      const allertList = document.getElementById("allertsList");
      rightSide.style.top = 0 + "vw";
      leftSide.style.top = 0 + "vw";
      allertList.style.left = -100 + "vw";
    },
    openEdit(id, index) {
      this.heightOfEditPage = 70;
      this.displayOfEditForm = "grid";
      this.opacityOfEditPage = 1;
      this.newId = index;
    },
    confirmEditPage(displayOfEditFormUpdated) {
      this.opacityOfEditPage = 0;
      this.heightOfEditPage = 0;
      this.displayOfEditForm = displayOfEditFormUpdated;
    },
    showAllertInfo(id, index) {
      const ind = index;
      this.previewAllertId = ind;
      this.activeItemId = index
    },
    removeAllert(id, index) {
      console.log(`Usuwasz allert id ${id} z ${index} indeksu`);
      this.showConfirmBox = true;
      this.allertInfo.push({ allertid: index });
      console.log("allertinfo" + this.allertInfo[0].allertid)
    },
    decline() {
      this.allertInfo = [];
      this.showConfirmBox = false;
      console.log("Anulowałeś usuwanie allertu");
    },
    confirm() {
      const index = this.allertInfo[0].allertid;
      this.allerts.splice(index, 1);
      console.log(`Usunąłeś allert nr ${index}`);
      this.allertInfo = [];
      this.showConfirmBox = false;
      this.previewAllertId = 0;
    },
    addForm() {
      this.showAddingForm = true;
    },
    closeAddingForm(showAddingForm) {
      this.showAddingForm = showAddingForm;
    }
  },
  created() {},
  computed: {
    filteredAllerts() {
      var self = this;
      if (this.allerts == null || this.allerts == undefined) {
        console.log("Allerts list is undefined");
      } else {
        return this.allerts.filter(function(cust) {
          return (
            cust.productURL.toLowerCase().indexOf(self.search.toLowerCase()) >=
            0
          );
        });
      }
    }
  },
  components: {
    appEditPage: EditPage,
    appConfirmBox: ConfirmBox,
    appAddingForm: AddingAllertForm
  }
};
</script>
<style>
.code-of-product {
  position: relative;
  z-index: 1;
  background-color: #96ca2d;
  color: white;
  font-weight: 600;
  padding: 2%;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
  transition: left 0.3s 0.1s;
  text-align: center;
}
.edit-button {
  color: #96ca2d;
  font-family: auto;
  font-size: 1.4rem;
  cursor: pointer;
  transition: 0.3s all;
  justify-self: center;
}
.small {
  transform: scale(0.7);
  transition: 0.3s all;
  justify-self: center;
}
.small:hover {
  transform: scale(0.8);
}
.edit-button:hover {
  transform: scale(1.2);
}
.edit-page {
  position: relative;
  height: 0%;
  width: 100%;
  background-color: #dfdfdf6e;
  border: 1px dashed #afafaf;
  top: 0;
  left: 0;
  opacity: 0;
  transition: height 0.3s, opacity 0.3s, display 0.3s;
  display: grid;
}
.bar {
  height: 30px;
  width: 5px;
  background-color: #96ca2d;
}
.add-button > div::after {
  content: "";
  transform: rotate(90deg);
  height: 30px;
  width: 5px;
  background-color: #96ca2d;
  position: absolute;
}
.close-button > div::after {
  content: "";
  transform: rotate(-90deg);
  height: 30px;
  width: 5px;
  background-color: #ff530d;
  position: absolute;
  cursor: pointer;
}
.close-button > .bar {
  height: 30px;
  width: 5px;
  background-color: #ff530d;
  transform: rotate(45deg);
}
.allertsList {
  position: absolute;
  left: -100vw;
  width: 100vw;
  height: 100%;
  z-index: 4;
  bottom: 0px;
  background-color: white;
  padding: 2%;
  transition: left 0.5s;
  overflow: auto;
  background-image: linear-gradient(
      to bottom,
      rgba(255, 255, 255, 0.85),
      rgba(255, 255, 255, 0.85)
    ),
    url("./../../public/img/icons/photo-of-discount-sign-2529787.jpg");
  background-size: cover;
  overflow-x: hidden;
}
header {
  display: grid;
  grid-template-columns: 80% 10% 5% 5%;
  align-items: center;
  margin-bottom: 2%;
}
header > input {
  margin-bottom: 0;
}
header > div {
  justify-self: center;
  font-size: 1.7rem;
  transition: 0.3s all;
  cursor: pointer;
}
header > div:hover {
  transform: scale(1.2) rotate(90deg);
}
header > h2,
div {
  margin-bottom: 0;
}
.allert {
  padding: 10px;
  width: 100%;
  border-bottom: 1px dashed #afafaf;
  background-color: #dfdfdf6e;
  display: grid;
  grid-template-columns: 45% 15% 15% 11% 7% 7%;
  align-items: center;
  position: relative;
  transition: 0.3s all;
}
.allert:hover {
  background-color: #b4b4b46e;
  cursor: pointer;
}
.active {
  background-color: #b4b4b46e;
}
.left-header {
  display: grid;
  grid-template-columns: 45% 15% 15% 11% 7% 7%;
  align-items: center;
  padding-left: 10px;
  padding-right: 10px;
}
.left-header > span {
  justify-self: center;
  font-weight: bold;
}
.allert > div > p {
  margin-bottom: 0;
}
.allert-logo,
.allert-img {
  justify-self: center;
}
.allert-logo > img,
.allert-img > img {
  transform: scale(1);
}
.halfs {
  display: grid;
  grid-template-columns: 60% 40%;
  grid-gap: 20px;
}
.left {
  height: 80vh;
  overflow-x: hidden;
}
.right {
  display: grid;
  height: 80vh;
  grid-template-columns: 100%;
  grid-template-rows: auto 100%;
}
.last-allert {
  display: grid;
  grid-template-columns: 30% 70%;
  justify-items: center;
  font-size: 1.15rem;
  background-color: #dfdfdf6e;
  padding: 10px;
}
.last-allert > img {
  transform: scale(1);
}
.last-allert > div > p {
  width: 100%;
}
.last-allert > div {
  justify-self: start;
}
@media (max-width:768px) {
  header > input {
    display: none;
  }
  header {
    grid-template-columns: 60% 20% 20%;
  }
  .right, .allert-logo, .allert-img {
    display: none
  }
  .halfs {
    grid-template-columns: 100%;
  }
  .allert > div > p > strong {
    display: none;
  }
  .left-header > span:nth-of-type(2),span:nth-of-type(3) {
    display: none;
  }
  .left-header, .allert {
    grid-template-columns: 50% 20% 15% 15%;
  }
}
</style>
