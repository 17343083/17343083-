(window.webpackJsonp=window.webpackJsonp||[]).push([[22],{726:function(e,t,a){e.exports={"next-sr-only":"PayFor--next-sr-only--1ea8to6",payFor:"PayFor--payFor--kht6Xly",formContainer:"PayFor--formContainer--2gEpTJO",formItem:"PayFor--formItem--1WrN0u5",formItemCol:"PayFor--formItemCol--s7_dBV4",inputIcon:"PayFor--inputIcon--1zGQ48J",submitBtn:"PayFor--submitBtn---rYaEY1",checkbox:"PayFor--checkbox--2lA9IfL",account:"PayFor--account--1xwookS",tips:"PayFor--tips--g83ToXe",tipsText:"PayFor--tipsText--34cFQJj",line:"PayFor--line--2XkGVyV"}},806:function(e,t,a){"use strict";a.r(t);var r=a(301),n=a(120),o=a(675),c=a(676),s=a(670),l=a(671),i=a(1),m=a.n(i),u=a(69),p=a.n(u),y=a(121),f=a(122),d=a(726),b=a.n(d);function h(e,t){return g(e)||F(e,t)||v()}function v(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}function F(e,t){if(Symbol.iterator in Object(e)||"[object Arguments]"===Object.prototype.toString.call(e)){var a=[],r=!0,n=!1,o=void 0;try{for(var c=e[Symbol.iterator](),s;!(r=(s=c.next()).done)&&(a.push(s.value),!t||a.length!==t);r=!0);}catch(e){n=!0,o=e}finally{try{r||null==c.return||c.return()}finally{if(n)throw o}}return a}}function g(e){if(Array.isArray(e))return e}var E=p.a,I=l.a.Row,w=c.a.Item;t.default=function(e){var t=Object(y.b)(f.c),a=t.loading,r=t.request,o,s=h(Object(i.useState)({to:"",count:""}),2),l=s[0],u=s[1];function p(e){u(e)}function d(e,t){t?console.log("errors",t):v(e)}function v(t){var a,n;return regeneratorRuntime.async(function o(c){for(;;)switch(c.prev=c.next){case 0:return c.prev=0,t.from=localStorage.getItem("username"),c.next=4,regeneratorRuntime.awrap(r({data:t}));case 4:a=c.sent,n=a.data,e.history.push("/result/success"),c.next=12;break;case 9:c.prev=9,c.t0=c.catch(0),console.error(c.t0);case 12:case"end":return c.stop()}},null,null,[[0,9]])}return m.a.createElement("div",{className:"PayFor-page"},m.a.createElement("div",{className:b.a.payFor},m.a.createElement("div",{className:b.a.formContainer},m.a.createElement(c.a,{value:l,onChange:p},m.a.createElement(w,{required:!0,requiredMessage:"\u5fc5\u586b",className:b.a.formItem},m.a.createElement(n.a,{innerBefore:m.a.createElement(E,{type:"person",size:"small",className:b.a.inputIcon}),name:"to",maxLength:20,placeholder:"\u6536\u6b3e\u65b9"})),m.a.createElement(w,{required:!0,requiredMessage:"\u5fc5\u586b",className:b.a.formItem},m.a.createElement(n.a,{innerBefore:m.a.createElement(E,{type:"coupons",size:"small",className:b.a.inputIcon}),name:"count",placeholder:"\u91d1\u989d"})),m.a.createElement(I,{className:b.a.formItem},m.a.createElement(c.a.Submit,{type:"primary",validate:!0,disabled:a,onClick:d,className:b.a.submitBtn},a?"\u521b\u5efa\u4e2d...":"\u521b \u5efa"))))))}}}]);