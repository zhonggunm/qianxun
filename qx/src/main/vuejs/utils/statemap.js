/**
 * Created by eyingsh on 1/30/2017.
 */

var itemStatusMap = [
  '保留',
  '在手',
  '丢失',
  '已通知失主',
  '已付款到千寻',
  '运送中',
  '已付款到找到者',
  '完成',
  '过期',
  '死亡'
];

var orderStatusMap = [
  '未知',
  '新建',
  '已支付',
  '运送中',
  '已收到',
  '完成',
  '异常',
  '取消'
];

var claimStatusMap = [
  '未知',
  '新建',
  '已支付',
  '拒绝',
  '完成',
  '异常',
  '取消'
];

export function getItemStatusMap (index) {
  return itemStatusMap[index];
};

export function getOrderStatusMap (index) {
  return orderStatusMap[index];
};

export function getClaimStatusMap (index) {
  return claimStatusMap[index];
};
