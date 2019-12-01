<template>
  <div id="application">
    <div class="allert-background">
      <app-navigation></app-navigation>

      <div class="background-photo"></div>
      <div class="background-footer">
        <p>
          Chcesz zadać pytanie ?
          <strong>Skontaktuj się z nami</strong>
        </p>
      </div>
    </div>
    <transition name="opacityTransition" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>
<script>
import Navigation from "./components/Navigation.vue";

export default {
  name: 'App',
  components: {
    appNavigation: Navigation
  }
}
</script>

<style>
.allert-background {
  width: 100vw;
  height: 100vh;
}
.background-photo {
  height: 80vh;
  background-image: linear-gradient(
      to bottom,
      rgba(255, 165, 20, 0.726),
      rgb(218, 178, 98)
    ),
    url("./../public/img/icons/photo-of-discount-sign-2529787.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  width: 100vw;
}
.background-footer {
  height: 20vh;
  display: grid;
  grid-template-columns: 100%;
  align-content: center;
  padding-left: 2%;
}
.background-footer > p {
  margin: 0;
  color: #afafaf;
}
.background-footer > p > strong {
  color: #fabc3c;
}
.opacityTransition-enter-active {
  animation: opacityTransition-in 0.3s ease-out;
}
.opacityTransition-leave-active {
  animation: opacityTransition-out 0.3s ease-out;
}
@keyframes opacityTransition-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes opacityTransition-out {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
@media (max-width: 767px) {
  .background-footer {
    justify-items: center;
    align-items: center;
  }
}
</style>