/**
 * Created by yshaoxp on 2017/2/4.
 */
import store from '../store';

export function getAPIPath () {
  return '/qxtag-0.0.1';
};

export function getRootPath () {
  /* return 'http://localhost:8080'; */
  return 'http://123.206.136.253' + getAPIPath();
};

export function getAppImage () {
  return 'qxtag-0.0.1.apk';
};

export function postRequest (path, object, onSuccess, onFailure) {
  var user = store.state.user;
  if (user !== undefined) {
    object.guid = user.id;
    object.gpassword = user.password;
  }
  this.$http.post(getRootPath() + path, object).then(onSuccess, onFailure);
};
