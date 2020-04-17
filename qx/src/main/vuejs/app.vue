<style lang="sass" src="./assets/scss/main.scss"></style>

<template>
  <div class="app">

    <page-header></page-header>

    <div class="application main container margin">
      <router-view class="view" transition="test" transition-mode="out-in" keep-alive></router-view>
    </div>

    <page-footer></page-footer>

  </div>
</template>

<style>
  .margin {
    margin-top: 10px;
    margin-bottom: 10px;
  }
</style>

<script>

  import PageHeader from './components/layout/PageHeader.vue';
  import PageFooter from './components/layout/PageFooter.vue';

  export default {
    name: 'app',
    data () {
      return {
        authenticating: false
      };
    },
    components: {
      PageHeader, PageFooter
    },
    ready: function () {

    }
  };
</script>
