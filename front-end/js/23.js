(window.webpackJsonp=window.webpackJsonp||[]).push([[23],{794:function(e,t,a){e.exports={"next-sr-only":"BaseSetting--next-sr-only--2qxGof_",label:"BaseSetting--label--1QOWW-G",formContent:"BaseSetting--formContent--2rs9eku",formItem:"BaseSetting--formItem--bCsGBP4",formTitle:"BaseSetting--formTitle--2l6qKqj"}},821:function(e,t,a){"use strict";a.r(t);var n=a(1),r=a.n(n),l=a(301),o=a(120),i=a(208),c=a(125),s=a(675),u=a(676),m=a(303),f=a(212),p=a(670),g=a(671),d=a(57),b=a(205),y=a.n(b),v=a(794),h=a.n(v),E=a(122),w=a(121);function S(){return(S=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e}).apply(this,arguments)}function C(e,t){return k(e)||O(e,t)||j()}function j(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}function O(e,t){if(Symbol.iterator in Object(e)||"[object Arguments]"===Object.prototype.toString.call(e)){var a=[],n=!0,r=!1,l=void 0;try{for(var o=e[Symbol.iterator](),i;!(n=(i=o.next()).done)&&(a.push(i.value),!t||a.length!==t);n=!0);}catch(e){r=!0,l=e}finally{try{n||null==o.return||o.return()}finally{if(r)throw l}}return a}}function k(e){if(Array.isArray(e))return e}var q=g.a.Row,x=g.a.Col,B=f.a.Group,T=u.a.Item,I={labelCol:{xxs:6,s:3,l:3},wrapperCol:{s:12,l:10}};function G(e){console.log("beforeUpload callback : ",e)}function A(e){console.log("onChane callback : ",e)}function M(e,t){console.log("onSuccess callback : ",e,t)}function N(e){console.log("onError callback : ",e)}var J=Object(d.d)(function(e){var t,a=Object(w.b)(E.f).request,l,i=C(Object(n.useState)({name:"",email:"",phone:""}),2),s=i[0],m=i[1];function f(e){m(e)}function p(e,t){var n,r;return regeneratorRuntime.async(function t(l){for(;;)switch(l.prev=l.next){case 0:e.name=localStorage.getItem("username"),console.log(e),n=a({data:e}),r=n.data,c.a.success("succeed!!");case 4:case"end":return l.stop()}})}var g=e.intl.formatMessage;return r.a.createElement("div",{className:"settings-form"},r.a.createElement(y.a,null,r.a.createElement(u.a,{value:s,onChange:f},r.a.createElement("div",{className:h.a.formContent},r.a.createElement("h2",{className:h.a.formTitle},r.a.createElement(d.a,{id:"app.setting.pagetitle"})),r.a.createElement(T,S({label:g({id:"app.setting.email"})},I,{required:!0,requiredMessage:g({id:"app.setting.email.message"})}),r.a.createElement(o.a,{htmlType:"email",name:"email"})),r.a.createElement(T,S({label:g({id:"app.setting.phone"})},I,{required:!0,requiredMessage:g({id:"app.setting.phone.message"})}),r.a.createElement(o.a,{name:"phone"})),r.a.createElement(q,{style:{marginTop:20}},r.a.createElement(x,{offset:"3"},r.a.createElement(u.a.Submit,{type:"primary",style:{width:100},validate:!0,onClick:p},r.a.createElement(d.a,{id:"app.setting.submit"}))))))))});function P(){return r.a.createElement("div",null,r.a.createElement(J,null))}a.d(t,"default",function(){return P})}}]);