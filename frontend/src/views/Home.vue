<template>
  <div class="homepage-components">
    <app-allerts></app-allerts>
    <div class="leftHomepage" id="leftHomepage">
      <div class="logo"></div>
      <div class="subtitle">
        <p>Stwórz swój pierwszy allert</p>
        <p class="arrow"></p>
      </div>
    </div>
    <div class="rightHomepage" id="rightHomepage">
      <app-allertForm></app-allertForm>
    </div>
  </div>
</template>
<script>
import AllertForm from "./../components/AllertForm.vue";
import Allerts from "../views/Allerts.vue";

export default {
  name: "home",
  components: {
    appAllertForm: AllertForm,
    appAllerts: Allerts
  }
};
</script>
<style>
.arrow {
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 10px 0 10px 20px;
  border-color: transparent transparent transparent #ffffff;
  animation-name: arrow-move;
  animation-duration: 0.8s;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}
@keyframes arrow-move {
  from {
    transform: translateX(0px);
  }
  to {
    transform: translateX(-20px);
  }
}
.subtitle {
  display: grid;
  grid-template-columns: 70% 30%;
  align-items: center;
  grid-gap: 10px;
}
.homepage-components {
  display: grid;
  width: 100vw;
  height: 100vh;
  grid-template-columns: 50% 50%;
  position: absolute;
  top: 0;
  left: 0;
}
.leftHomepage {
  justify-content: center;
  align-content: center;
  display: grid;
  grid-template-columns: 50%;
  grid-template-rows: auto;
  top: 0;
  position: relative;
  transition: top 0.3s;
}
.rightHomepage {
  display: grid;
  grid-template-columns: 100%;
  align-content: center;
  justify-items: center;
  top: 0;
  position: relative;
  transition: top 0.7s;
}
.subtitle > p {
  color: white;
  justify-self: center;
  font-size: 1.5rem;
  margin: 0;
  align-self: center;
  justify-self: center;
}
.logo {
  background-image: url("./../../public/img/logo/logo_final_600px.png");
  background-size: contain;
  background-repeat: no-repeat;
  background-position: 10% center;
  height: 150px;
}
@media (max-width: 767px) {
  .homepage-components {
    grid-template-columns: 100%;
  }
  .leftHomepage,
  .rightHomepage {
    height: 100vh;
  }
  .leftHomepage {
    grid-template-columns: 80%;
  }
  .subtitle {
    grid-template-columns: 100%;
  }
  .arrow {
    border-width: 20px 10px 0 10px;
    border-color: #ffffff transparent transparent transparent;
  }
  @keyframes arrow-move {
  from {
    transform: translateY(30px);
  }
  to {
    transform: translateY(0px);
  }
}
}
</style>
