"use strict";(self.webpackChunkdocu=self.webpackChunkdocu||[]).push([[5834],{3905:(e,t,n)=>{n.d(t,{Zo:()=>d,kt:()=>u});var r=n(67294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var l=r.createContext({}),s=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},d=function(e){var t=s(e.components);return r.createElement(l.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},m=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,l=e.parentName,d=c(e,["components","mdxType","originalType","parentName"]),m=s(n),u=a,f=m["".concat(l,".").concat(u)]||m[u]||p[u]||o;return n?r.createElement(f,i(i({ref:t},d),{},{components:n})):r.createElement(f,i({ref:t},d))}));function u(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=m;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c.mdxType="string"==typeof e?e:a,i[1]=c;for(var s=2;s<o;s++)i[s]=n[s];return r.createElement.apply(null,i)}return r.createElement.apply(null,n)}m.displayName="MDXCreateElement"},91280:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>i,default:()=>p,frontMatter:()=>o,metadata:()=>c,toc:()=>s});var r=n(87462),a=(n(67294),n(3905));const o={},i="Getting Started with Low-Code CDK",c={unversionedId:"connector-development/config-based/index",id:"connector-development/config-based/index",title:"Getting Started with Low-Code CDK",description:"This framework is in alpha. It is still in active development and may include backward-incompatible changes. Please share feedback and requests directly with us at feedback@airbyte.io",source:"@site/../docs/connector-development/config-based/index.md",sourceDirName:"connector-development/config-based",slug:"/connector-development/config-based/",permalink:"/connector-development/config-based/",draft:!1,editUrl:"https://github.com/airbytehq/airbyte/blob/master/docs/../docs/connector-development/config-based/index.md",tags:[],version:"current",frontMatter:{},sidebar:"mySidebar",previous:{title:"Connector Development",permalink:"/connector-development/"},next:{title:"Getting Started",permalink:"/connector-development/config-based/tutorial/getting-started"}},l={},s=[{value:"From scratch",id:"from-scratch",level:2},{value:"Concepts",id:"concepts",level:2},{value:"Tutorial",id:"tutorial",level:2}],d={toc:s};function p(e){let{components:t,...n}=e;return(0,a.kt)("wrapper",(0,r.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"getting-started-with-low-code-cdk"},"Getting Started with Low-Code CDK"),(0,a.kt)("p",null,"\u26a0\ufe0f This framework is in ",(0,a.kt)("a",{parentName:"p",href:"https://docs.airbyte.com/project-overview/product-release-stages/#alpha"},"alpha"),". It is still in active development and may include backward-incompatible changes. Please share feedback and requests directly with us at ",(0,a.kt)("a",{parentName:"p",href:"mailto:feedback@airbyte.io"},"feedback@airbyte.io")," \u26a0\ufe0f"),(0,a.kt)("h2",{id:"from-scratch"},"From scratch"),(0,a.kt)("p",null,"This section gives an overview of the low-code framework."),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/overview"},"Overview")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/yaml-structure"},"YAML structure")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"https://airbyte-cdk.readthedocs.io/en/latest/api/airbyte_cdk.sources.declarative.html"},"Reference docs"))),(0,a.kt)("h2",{id:"concepts"},"Concepts"),(0,a.kt)("p",null,"This section contains additional information on the different components that can be used to define a low-code connector."),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/authentication"},"Authentication")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/error-handling"},"Error handling")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/pagination"},"Pagination")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/record-selector"},"Record selection")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/request-options"},"Request options")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/stream-slicers"},"Stream slicers")),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/substreams"},"Substreams"))),(0,a.kt)("h2",{id:"tutorial"},"Tutorial"),(0,a.kt)("p",null,"This section a tutorial that will guide you through the end-to-end process of implementing a low-code connector."),(0,a.kt)("ol",{start:0},(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/getting-started"},"Getting started")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/create-source"},"Creating a source")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/install-dependencies"},"Installing dependencies")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/connecting-to-the-API-source"},"Connecting to the API")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/reading-data"},"Reading data")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/incremental-reads"},"Incremental reads")),(0,a.kt)("li",{parentName:"ol"},(0,a.kt)("a",{parentName:"li",href:"/connector-development/config-based/tutorial/testing"},"Testing"))))}p.isMDXComponent=!0}}]);