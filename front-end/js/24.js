(window.webpackJsonp=window.webpackJsonp||[]).push([[24],{723:function(e,t,r){e.exports={"next-sr-only":"TransferRepict--next-sr-only--1-b8ST4",transferRepict:"TransferRepict--transferRepict--1cGlSox",formContainer:"TransferRepict--formContainer--3oregoO",formItem:"TransferRepict--formItem--1Z7M8Rk",formItemCol:"TransferRepict--formItemCol--22VfJhc",inputIcon:"TransferRepict--inputIcon--2PgnECG",submitBtn:"TransferRepict--submitBtn--38OHAzD",checkbox:"TransferRepict--checkbox--eVz5P_S",account:"TransferRepict--account--TwfCNej",tips:"TransferRepict--tips--2Yy0Pbo",tipsText:"TransferRepict--tipsText--cige6br",line:"TransferRepict--line--WBq9qNi"}},803:function(e,t,r){"use strict";r.r(t);var a=r(301),n=r(120),c=r(675),o=r(676),s=r(670),i=r(671),l=r(1),m=r.n(l),u=r(69),p=r.n(u),f=r(121),d=r(122),b=r(723),g=r.n(b);function y(e,t){return T(e)||h(e,t)||R()}function R(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}function h(e,t){if(Symbol.iterator in Object(e)||"[object Arguments]"===Object.prototype.toString.call(e)){var r=[],a=!0,n=!1,c=void 0;try{for(var o=e[Symbol.iterator](),s;!(a=(s=o.next()).done)&&(r.push(s.value),!t||r.length!==t);a=!0);}catch(e){n=!0,c=e}finally{try{a||null==o.return||o.return()}finally{if(n)throw c}}return r}}function T(e){if(Array.isArray(e))return e}var v=p.a,E=i.a.Row,I=o.a.Item;t.default=function(e){var t=Object(f.b)(d.e),r=t.loading,a=t.request,c,s=y(Object(l.useState)({from:"",to:"",count:""}),2),i=s[0],u=s[1];function p(e){u(e)}function b(e,t){t?console.log("errors",t):R(e)}function R(t){var r,n;return regeneratorRuntime.async(function c(o){for(;;)switch(o.prev=o.next){case 0:return o.prev=0,t.originFrom=localStorage.getItem("username"),o.next=4,regeneratorRuntime.awrap(a({data:t}));case 4:r=o.sent,n=r.data,e.history.push("/result/success"),o.next=12;break;case 9:o.prev=9,o.t0=o.catch(0),console.error(o.t0);case 12:case"end":return o.stop()}},null,null,[[0,9]])}return m.a.createElement("div",{className:"TransferRepict-page"},m.a.createElement("div",{className:g.a.transferRepict},m.a.createElement("div",{className:g.a.formContainer},m.a.createElement(o.a,{value:i,onChange:p},m.a.createElement(I,{required:!0,requiredMessage:"\u5fc5\u586b",className:g.a.formItem},m.a.createElement(n.a,{innerBefore:m.a.createElement(v,{type:"person",size:"small",className:g.a.inputIcon}),name:"from",maxLength:20,placeholder:"\u6b20\u6b3e\u65b9"})),m.a.createElement(I,{required:!0,requiredMessage:"\u5fc5\u586b",className:g.a.formItem},m.a.createElement(n.a,{innerBefore:m.a.createElement(v,{type:"person",size:"small",className:g.a.inputIcon}),name:"to",maxLength:20,placeholder:"\u65b0\u6536\u6b3e\u65b9"})),m.a.createElement(I,{required:!0,requiredMessage:"\u5fc5\u586b",className:g.a.formItem},m.a.createElement(n.a,{innerBefore:m.a.createElement(v,{type:"coupons",size:"small",className:g.a.inputIcon}),name:"count",placeholder:"\u91d1\u989d"})),m.a.createElement(E,{className:g.a.formItem},m.a.createElement(o.a.Submit,{type:"primary",validate:!0,disabled:r,onClick:b,className:g.a.submitBtn},r?"\u521b\u5efa\u4e2d...":"\u521b \u5efa"))))))}}}]);