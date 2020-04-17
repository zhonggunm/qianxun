<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="modal fade" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <lost-report-view id="lostreportmodel" :item="item"
                          v-if="item.status==1"></lost-report-view>
        <find-report-view id="findreportmodel" :item="item"
                          v-if="item.status==2"></find-report-view>
        <accept-confirm-view id="acceptconfirmmodel" :item="item"
                          v-if="item.status==5"></accept-confirm-view>
      </div>
    </div>
  </div>
</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import FindReportView from './FindReportView.vue';
  import LostReportView from './LostReportView.vue';
  import AcceptConfirmView from './AcceptConfirmView.vue';

  export default{
    name: 'itemDetailModel',
    props: ['item'],
    data () {},
    components: {
      'find-report-view': FindReportView,
      'lost-report-view': LostReportView,
      'accept-confirm-view': AcceptConfirmView
    },
    methods: {},

    ready: function () {}
  };
</script>
