(window.webpackJsonp=window.webpackJsonp||[]).push([[27],{727:function(e,a,t){e.exports={"next-sr-only":"Validate--next-sr-only--26SoQ5E",validate:"Validate--validate--XoAujrp",formContainer:"Validate--formContainer--3z8jAN4",formItem:"Validate--formItem--3f90Pgb",formItemCol:"Validate--formItemCol--1JAwcGi",inputIcon:"Validate--inputIcon--1agJmaH",submitBtn:"Validate--submitBtn--pPnI9MN",checkbox:"Validate--checkbox--25Qa-Cs",account:"Validate--account--158AA2L",tips:"Validate--tips--3-SK396",tipsText:"Validate--tipsText--3iycUar",line:"Validate--line--Z7f5xVH"}},807:function(e,a,t){"use strict";t.r(a);var n=t(301),r=t(120),o=t(675),l=t(676),i=t(670),c=t(671),s=t(1),m=t.n(s),u=t(69),d=t.n(u),p=t(121),f=t(122),b=t(727),v=t.n(b);function y(e,a){return E(e)||h(e,a)||g()}function g(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}function h(e,a){if(Symbol.iterator in Object(e)||"[object Arguments]"===Object.prototype.toString.call(e)){var t=[],n=!0,r=!1,o=void 0;try{for(var l=e[Symbol.iterator](),i;!(n=(i=l.next()).done)&&(t.push(i.value),!a||t.length!==a);n=!0);}catch(e){r=!0,o=e}finally{try{n||null==l.return||l.return()}finally{if(r)throw o}}return t}}function E(e){if(Array.isArray(e))return e}var I=d.a,x=c.a.Row,V=l.a.Item;a.default=function(e){var a=Object(p.b)(f.l),t=a.loading,n=a.request,o,i=y(Object(s.useState)({from:"",to:"",count:""}),2),c=i[0],u=i[1];function d(e){u(e)}function b(e,a){a?console.log("errors",a):g(e)}function g(a){var t,r;return regeneratorRuntime.async(function o(l){for(;;)switch(l.prev=l.next){case 0:return l.prev=0,a.name=localStorage.getItem("username"),l.next=4,regeneratorRuntime.awrap(n({data:a}));case 4:t=l.sent,r=t.data,e.history.push("/result/success"),l.next=12;break;case 9:l.prev=9,l.t0=l.catch(0),console.error(l.t0);case 12:case"end":return l.stop()}},null,null,[[0,9]])}return m.a.createElement("div",{className:"Validate-page"},m.a.createElement("div",{className:v.a.validate},m.a.createElement("div",{className:v.a.formContainer},m.a.createElement("p",null,"\u4ec5\u91d1\u878d\u673a\u6784(\u94f6\u884c\u7b49)\u53ef\u6267\u884c\u8be5\u64cd\u4f5c"),m.a.createElement(l.a,{value:c,onChange:d},m.a.createElement(V,{required:!0,requiredMessage:"\u5fc5\u586b",className:v.a.formItem},m.a.createElement(r.a,{innerBefore:m.a.createElement(I,{type:"person",size:"small",className:v.a.inputIcon}),name:"from",maxLength:20,placeholder:"\u6536\u636e\u6b20\u6b3e\u65b9"})),m.a.createElement(V,{required:!0,requiredMessage:"\u5fc5\u586b",className:v.a.formItem},m.a.createElement(r.a,{innerBefore:m.a.createElement(I,{type:"person",size:"small",className:v.a.inputIcon}),name:"to",maxLength:20,placeholder:"\u6536\u636e\u6536\u6b3e\u65b9"})),m.a.createElement(V,{required:!0,requiredMessage:"\u5fc5\u586b",className:v.a.formItem},m.a.createElement(r.a,{innerBefore:m.a.createElement(I,{type:"coupons",size:"small",className:v.a.inputIcon}),name:"count",placeholder:"\u91d1\u989d"})),m.a.createElement(x,{className:v.a.formItem},m.a.createElement(l.a.Submit,{type:"primary",validate:!0,disabled:t,onClick:b,className:v.a.submitBtn},t?"\u521b\u5efa\u4e2d...":"\u521b \u5efa"))))))}}}]);