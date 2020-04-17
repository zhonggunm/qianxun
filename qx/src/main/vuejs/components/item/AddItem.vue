<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <form class="form-horizontal" role="form">

      <div class="form-group">
        <label class="col-sm-2 control-label">标题</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" placeholder="6-20个字符" v-model="name">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">类别</label>
        <div class="col-sm-6">

           <div class="input-group-btn dropdown" aria-labelledby="dropdownMenu1">
             <select class="form-control" v-model="categories.selected">
               <option v-for="category in categories.list" value="{{category.id}}">
                 {{category.title}}
               </option>
             </select>
           </div>

        </div>
      </div>

      <div class="form-group">
        <label for="exampleInputFile" class="col-sm-2 control-label">图片</label>
        <div class="col-sm-6">
          <image-uploader id="exampleInputFile" :imagefile.sync="fimage"></image-uploader>
          <div class="progress" v-if="progress>0">
            <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="0"
                 aria-valuemin="0" aria-valuemax="100" style="width: {{progress}}%">
              <span class="sr-only">{{progress}}%</span>
            </div>
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">详细描述</label>
        <div class="col-sm-6">
          <textarea class="form-control" rows="3" v-model="description"></textarea>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
          <h3><span class="label label-info">{{msg}}</span></h3>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
          <button type="button" class="btn btn-primary btn-block"
                  v-bind:class="{'disabled':working}" @click="addItem">创建</button>
        </div>
      </div>
    </form>

</template>

<style>
  body{
    background-color:#ff0000;
  }
</style>

<script>
  import store from '../../store';
  import ImageUploader from '../layout/ImageUploader.vue';
  import {getRootPath, postRequest} from '../../utils/common.js';

  export default{
    name: 'add',
    data () {
      return {
        userid: store.state.user.id,
        name: '',
        description: '',
        msg: '',
        image: '',
        progress: -1,
        working: false,
        categories: {
          selected: 0,
          list: []
        },
        fimage: ''
      };
    },
    computed: {},
    components: {
      'image-uploader': ImageUploader
    },

    methods: {
      getRootPath,
      postRequest,
      queryCategories: function () {
        var obj = {};
        this.postRequest('/item/category', obj, this.onQueryCategoriesSuccess, this.onFailure);
      },
      onQueryCategoriesSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.categories.list = data.data;
        } else {
          // Error handler
        }
        this.working = false;
      },
      onFailure: function (resp) {
      },

      addItem: function () {
        if (store.state.user === undefined) {
          this.$router.go('/login');
        } else {
          this.upload();
        }
      },

      postItem: function () {
        var obj = {
          itemid: this.$route.params.itemid,
          userid: this.userid,
          name: this.name,
          image: this.image,
          category: this.categories.selected,
          area: 1
        };
        this.postRequest('/item/new', obj, this.onPostItemSuccess, this.onPostItemFailure);
      },
      onPostItemSuccess: function (resp) {
        var data = resp.data;
        if (data.code === 'NoError') {
          this.msg = '新建成功！';
        } else {
          this.msg = '错误:(' + data.code + ')';
        }
        this.working = false;
      },
      onPostItemFailure: function (resp) {
        this.msg = '错误:(' + resp.status + '), 请稍候重试！';
        this.working = false;
      },

      step: function (evt) {
        if (evt.lengthComputable) {
          this.progress = (evt.loaded / evt.total) * 100;
        }
      },

      upload: function () {
        this.working = true;
        var self = this;
        var xhr = new XMLHttpRequest();
        var formdata = new FormData();
        formdata.append('file', this.fimage);
        self.progress = 0;
        self.msg = '上传中...';
        xhr.onreadystatechange = function (request) {
          if (xhr.readyState === 4) {
            self.progress = -1;
            if (xhr.status === 200) {
              var resp = JSON.parse(request.currentTarget.response);
              if (resp.code === 'NoError') {
                self.msg = '内容上传成功, 正在创建关联数据...';
                self.image = resp.data;
                self.postItem();
              } else {
                self.msg = '错误:(' + resp.code + ':' + resp.data + '),请稍候重试！';
                self.working = false;
              }
            } else {
              self.msg = '错误:(' + xhr.readyState + ',' + xhr.status + '),请稍候重试！';
              self.working = false;
            }
          }
        };
        xhr.upload.addEventListener('progress', this.step, false);
        xhr.open(/* method */ 'POST',
          /* target url */ this.getRootPath() + '/file/upload'
          /* , async, default touser true */);
        xhr.setRequestHeader('enctype', 'multipart/form-data');
        xhr.send(formdata);
      }
    },
    ready: function () {
      this.queryCategories();
    }
  };
</script>
