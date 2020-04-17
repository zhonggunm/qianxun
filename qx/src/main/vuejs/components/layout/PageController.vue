<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <div>
      <button type="button" class="btn btn-default btn-lg" v-bind:class="{'disabled':page <= 0}"
              @click="previousPage">
        <span class="glyphicon glyphicon-chevron-left"></span> 上一页
      </button>
      <button type="button" class="btn btn-default btn-lg" v-bind:class="{'disabled':finalpage}" @click="nextPage">
        <span class="glyphicon glyphicon-chevron-right"></span> 下一页
      </button>
      <button type="button" class="btn btn-default btn-lg" @click="refresh">
        <span class="glyphicon glyphicon-refresh"></span> 刷新
      </button>
    </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  export default {
    props: ['refresh', 'page', 'finalpage'],
    methods: {
      nextPage: function () {
        if (this.finalpage === false) {
          ++this.page;
          this.refresh();
        }
      },
      previousPage: function () {
        --this.page;
        if (this.page < 0) {
          this.page = 0;
        } else {
          this.finalpage === false;
          this.refresh();
        }
      }
    }
  };
</script>
