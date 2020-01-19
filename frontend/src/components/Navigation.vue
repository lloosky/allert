<template>
  <div class="navigation">
    <div class="navigation-draw" :style="{height: heightOfNavDraw + 'vh', top: topOfDraw + 'px'}">
      <div class="closeNavDraw" @click="closeNavigationDraw">
        <div></div>
        <div></div>
      </div>
      <router-link to="/" name="home">
        <img src="./../../public/img/icons/001-home.png" @click="closeNavigationDraw" />
      </router-link>
      <router-link to="manual" name="manual">
        <img src="./../../public/img/icons/002-information.png" @click="closeNavigationDraw" />
      </router-link>
      <router-link to="contact" name="contact">
        <img src="./../../public/img/icons/003-envelope.png" @click="closeNavigationDraw" />
      </router-link>
    </div>
    <div class="navigation-button" @click="openNavigationDraw">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      heightOfNavDraw: 0,
      topOfDraw: -200
    };
  },
  methods: {
    openNavigationDraw() {
      this.heightOfNavDraw = 70;
      this.topOfDraw = 0;
    },
    closeNavigationDraw() {
      this.heightOfNavDraw = 0;
      this.topOfDraw = -200;
    }
  }
};
</script>
<style>
.navigation-draw {
  width: 5vw;
  position: absolute;
  left: 0px;
  background-color: #ff530d;
  transition: 0.5s ease-in-out;
  z-index: 3;
  display: grid;
  grid-template-columns: 100%;
  grid-template-rows: auto auto auto;
  align-items: center;
  justify-items: center;
}
img {
  transform: scale(0.6);
  cursor: pointer;
  transition: 0.2s ease-in-out;
}
img:hover {
  transform: scale(0.8);
}
.navigation-button {
  position: absolute;
  padding: 2% 0% 0% 2%;
  transition: 0.3s ease-in-out;
  cursor: pointer;
  z-index: 2;
}
.navigation-button > div {
  height: 5px;
  width: 35px;
  background-color: white;
  margin-bottom: 5px;
  border-radius: 15px;
  transition: 0.2s ease-in-out;
}
.navigation-draw > .closeNavDraw > div {
  height: 2px;
  width: 20px;
  background-color: black;
  border-radius: 15px;
}
.closeNavDraw {
  position: absolute;
  top: 15px;
  right: 10px;
  transform: rotate(45deg);
  transition: 0.2s ease-in-out;
  cursor: pointer;
}
.closeNavDraw > div:nth-child(2) {
  transform: rotate(90deg) translateX(-2px);
}
.navigation-draw > ul {
  position: absolute;
  top: 50%;
  list-style: none;
  padding-left: 0;
}
.navigation-button:hover {
  transform: scale(0.9);
}
.navigation-button:hover > div:nth-child(1) {
  transform: translateX(10px);
}
.navigation-button:hover > div:nth-child(2) {
  transform: translateX(-10px);
}
.closeNavDraw:hover {
  transform: scale(0.9) rotate(120deg);
}
@media (max-width: 767px) {
  .navigation-button {
    padding: 5% 0% 0% 5%;
  }
  .navigation-draw {
    width: 20vw;
  }
}
</style>
