(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7509302c"],{6109:function(t,i,s){"use strict";s("9627")},"642f":function(t,i,s){"use strict";s.d(i,"g",(function(){return n})),s.d(i,"a",(function(){return r})),s.d(i,"f",(function(){return c})),s.d(i,"e",(function(){return l})),s.d(i,"d",(function(){return o})),s.d(i,"h",(function(){return u})),s.d(i,"i",(function(){return d})),s.d(i,"b",(function(){return m})),s.d(i,"c",(function(){return v}));var e=s("5232"),a=(s("0a09"),s("1bab"));function n(t,i){return Object(a["a"])({url:"/mv/first",params:{limit:t,area:i}})}var r=function t(i,s,a,n,r,c){Object(e["a"])(this,t),this.id=i,this.cover=s,this.name=a,this.artist=n,this.count=r,this.duration=c};function c(t){return Object(a["a"])({url:"/mv/detail",params:{mvid:t}})}function l(t){return Object(a["a"])({url:"/mv/url",params:{id:t}})}function o(t,i){var s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1;return Object(a["a"])({url:"/comment/mv",params:{id:t,limit:i,offset:s}})}function u(t){return Object(a["a"])({url:"/simi/mv",params:{mvid:t}})}function d(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:10,i=arguments.length>1?arguments[1]:void 0,s=arguments.length>2?arguments[2]:void 0;return Object(a["a"])({url:"/top/mv",params:{limit:t,area:i,offset:s}})}function m(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"全部",i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"全部",s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"最新",e=arguments.length>3?arguments[3]:void 0,n=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1;return Object(a["a"])({url:"/mv/all",params:{area:t,type:i,order:s,limit:e,offset:n}})}function v(){return Object(a["a"])({url:"/personalized/mv"})}},9627:function(t,i,s){},a94f:function(t,i,s){"use strict";s.r(i);var e=function(){var t=this,i=t.$createElement,s=t._self._c||i;return null!=t.id&&null!=t.detail?s("div",{staticClass:"dance-music-mv-detail"},[s("div",{staticClass:"layout-left"},[null!=t.detail?s("div",{staticClass:"title"},[s("b-tag",{attrs:{plain:"",color:"var(--main-color)"}},[t._v("MV")]),s("div",{staticClass:"name"},[t._v(t._s(t.detail.name))]),s("div",{staticClass:"artist"},[t._v(t._s(t.detail.artistName))])],1):t._e(),null!=t.url?s("div",{staticClass:"video"},[s("video",{staticClass:"video-play",attrs:{src:t.url,controls:"",autoplay:"",width:"100%"}})]):t._e(),s("div",{ref:"recom",staticClass:"recommend"},[s("p",{staticClass:"p"},[t._v("评论")]),s("recommends",{staticClass:"rec",attrs:{recommends:t.recommends}})],1)]),s("div",{staticClass:"right"},[t.detail.desc?s("div",{staticClass:"desc"},[s("p",{staticClass:"p"},[t._v("MV介绍")]),s("div",{staticClass:"base"},[s("div",{staticClass:"date"},[t._v("发布时间："+t._s(t.detail.publishTime))]),s("div",{staticClass:"playCount"},[t._v("播放次数："+t._s(t.detail.playCount)+"次")]),s("div",{staticClass:"clear"})]),null!=t.detail.desc?s("div",{ref:"mvDesc",staticClass:"mv-desc",on:{mouseenter:t.enter}},[s("scroll",{ref:"descScroll",staticClass:"desc-scroll",attrs:{"disable-wheel":!0}},[s("span",[t._v("简介：")]),t._v(" "+t._s(t.detail.desc)+" ")])],1):t._e()]):t._e(),s("div",{staticClass:"alia"},[s("p",{staticClass:"p"},[t._v("相关推荐")]),s("simi-mv-item",{attrs:{"mv-list":t.simiMv}})],1)]),s("div",{staticClass:"clear"}),s("div",{staticClass:"mv-detail-bot"},[s("el-pagination",{attrs:{background:"","current-page":t.offset,"page-count":10},on:{"update:currentPage":function(i){t.offset=i},"update:current-page":function(i){t.offset=i},"current-change":t.onPageChange}})],1)]):t._e()},a=[],n=(s("0a09"),s("5d17")),r=s("b99c"),c=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",{staticClass:"simi"},t._l(t.mvList,(function(i,e){return s("div",{key:e,staticClass:"simi-item",on:{click:function(s){return t.playMV(i.id)}}},[s("div",{staticClass:"left"},[s("img",{attrs:{src:i.cover,alt:""}}),s("div",{staticClass:"count"},[s("i",{staticClass:"iconfont icon-shipin"}),s("div",{staticClass:"play-count"},[t._v(t._s(i.count))])])]),s("div",{staticClass:"right"},[s("div",{staticClass:"name"},[s("b-tag",{staticClass:"tag",attrs:{color:"var(--main-color)",plain:""}},[t._v("MV")]),t._v(" "+t._s(i.name)+" ")],1),s("div",{staticClass:"artist"},[t._v(t._s(i.artist))])])])})),0)},l=[],o={name:"SimiMvItem",props:{mvList:{type:Array,default:function(){return[]}}},methods:{playMV:function(t){this.$router.push("/mv-detail/"+t)}}},u=o,d=s("5d22"),m=Object(d["a"])(u,c,l,!1,null,null,null),v=m.exports,f=s("642f"),h={name:"MvDetail",data:function(){return{id:null,detail:null,url:null,recommends:null,offset:1,limit:20,simiMv:[]}},components:{Scroll:n["a"],Recommends:r["a"],SimiMvItem:v},watch:{$route:function(){this.id=this.$route.params.id,null!=this.id&&(this.reset(),this.initRequest())}},created:function(){this.id=this.$route.params.id,null!=this.id&&this.initRequest()},methods:{pullingUp:function(){this.getComment()},onPageChange:function(){this.getComment()},getComment:function(){var t=this;Object(f["d"])(this.id,this.limit,this.offset).then((function(i){t.recommends=i.data.comments,t.$nextTick((function(){t.$refs.recom.offsetTop}))}))},reset:function(){this.simiMv=[],this.offset=1},initRequest:function(){var t=this;Object(f["f"])(this.id).then((function(i){t.detail=i.data.data})),Object(f["e"])(this.id).then((function(i){t.url=i.data.data.url})),Object(f["d"])(this.id,this.limit).then((function(i){t.recommends=i.data.comments})),Object(f["h"])(this.id).then((function(i){var s=i.data.mvs;for(var e in s){var a=new f["a"](s[e].id,s[e].cover,s[e].name,s[e].artistName,s[e].playCount);t.simiMv.push(a)}}))},enter:function(){this.$refs.descScroll.refresh()}}},p=h,C=(s("6109"),Object(d["a"])(p,e,a,!1,null,"2db776f6",null));i["default"]=C.exports}}]);
//# sourceMappingURL=chunk-7509302c.5929842a.js.map