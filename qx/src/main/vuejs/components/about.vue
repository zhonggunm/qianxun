<template>
  <div class="about">
    <h1>{{title}}</h1>
    <blockquote class="blockquote">
      <p>{{message}}</p>
      <footer><cite>{{author}}</cite></footer>
    </blockquote>
    <div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'about',
    data () {
      return {
        title: '千寻社区',
        message: '',
        author: ''
      };
    },
    components: {},
    ready: function () {
    }
  };
</script>
