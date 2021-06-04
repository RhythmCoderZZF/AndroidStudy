(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2a94fb1a"],{"02e4":function(t,e,n){"use strict";n("9648")},"057e":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{class:t.program+"playlist"},t._l(t.musicList,(function(e,i){return n("div",{key:i,staticClass:"playlist-item",on:{mouseenter:function(e){return t.handleEnter(i)},mouseleave:function(e){return t.handleLeave(i)},click:function(e){return t.enterMusicListDetail(i)}}},[n("div",{staticClass:"playlist-item-container"},[n("div",{staticClass:"playlist-item-container-group"},[t.emptyDesc?t._e():[n("transition",{attrs:{name:"playlist-slide"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.currentIndex==i,expression:"currentIndex == index"}],staticClass:"playlist-desc"},[t._v(" "+t._s(e.copywriter)+" ")])])],n("transition",{attrs:{name:"playlist-slide"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.emptyDesc?t.currentIndex==i:t.currentIndex!=i,expression:"\n              emptyDesc ? currentIndex == index : currentIndex != index\n            "}],staticClass:"playlist-count"},[n("div",[n("i",{staticClass:"iconfont icon-erji"}),t._v(" "+t._s(e.playCount)+" ")])])]),n("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.picUrl||e.coverImgUrl,expression:"item.picUrl || item.coverImgUrl"}],on:{load:t.handleRefresh}}),n("transition",{attrs:{name:"dance-music-opacity"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.currentIndex==i,expression:"currentIndex == index"}],staticClass:"playlist-play"},[n("i",{staticClass:"iconfont icon-icon_play"})])])],2),n("div",{staticClass:"playlist-name",class:[t.program+"playlist-name-"+t.theme]},[t._v(" "+t._s(e.name)+" ")])])])})),0)},s=[],a=n("3ce8"),r=n("ca40"),c={name:"MusicList",mixins:[a["a"],r["a"]],props:{musicList:{type:Array,default:[]},emptyDesc:{type:Boolean,default:!1}},data:function(){return{currentIndex:null}},methods:{handleEnter:function(t){this.currentIndex=t},handleLeave:function(t){this.currentIndex=null},enterMusicListDetail:function(t){this.$router.push("/musiclistdetail/"+this.musicList[t].id+"/"+(new Date).getTime())},handleRefresh:function(){this.imgCount==this.musicList.length&&this.$emit("refresh"),this.imgCount++}},watch:{musicList:function(){this.imgCount=1}}},l=c,o=(n("d84c"),n("5d22")),u=Object(o["a"])(l,i,s,!1,null,"6f949cea",null);e["a"]=u.exports},"0a5a":function(t,e,n){},9648:function(t,e,n){},"9bde":function(t,e,n){},a7eb:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{class:t.indiviClass},[n("div",{staticClass:"dance-music-group"},[n("swiper",{staticClass:"swiper",attrs:{banner:t.banner}}),n("h4",{class:[t.program+"indivi-h4",t.program+"indivi-h4-"+t.theme]},[t._v(" 推荐歌单 ")]),n("music-list",{attrs:{"music-list":t.personalized},on:{refresh:t.handleRefresh}}),n("private-content",{attrs:{pri:t.privateContent}}),n("new-songs",{attrs:{"music-list":t.songList},on:{newSongImgLoad:t.handleRefresh}})],1)])},s=[],a=(n("6ce8"),n("3ce8")),r=n("fba2"),c=function(){var t=this,e=t.$createElement,n=t._self._c||e;return null!=t.banner?n("el-carousel",{staticClass:"swiper",attrs:{interval:4e3,type:"card",height:"200px",trigger:"click"}},t._l(t.banner,(function(t,e){return n("el-carousel-item",{key:e},[n("img",{directives:[{name:"lazy",rawName:"v-lazy",value:t.imageUrl,expression:"item.imageUrl"}],attrs:{alt:""}})])})),1):t._e()},l=[],o={name:"Swiper",props:{banner:{type:Array,default:[]}}},u=o,d=(n("02e4"),n("5d22")),m=Object(d["a"])(u,c,l,!1,null,null,null),p=m.exports,f=n("057e"),v=function(){var t=this,e=t.$createElement,n=t._self._c||e;return null!=t.pri?n("div",{staticClass:"private"},[n("h4",{staticClass:"top"},[t._v(t._s(t.pri.name))]),n("div",{staticClass:"content"},t._l(t.pri.result,(function(e,i){return n("div",{key:i,staticClass:"item",on:{click:function(n){return t.playMV(e.id)}}},[t._m(0,!0),n("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.picUrl,expression:"item.picUrl"}],attrs:{alt:""}}),n("div",{staticClass:"bottom",class:[t.program+"bottom-"+t.theme]},[t._v(" "+t._s(e.name)+" ")])])})),0)]):t._e()},h=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"loco"},[n("i",{staticClass:"iconfont icon-shipin"})])}],y={name:"PrivateContent",mixins:[a["a"]],props:{pri:{type:Object,default:function(){return{}}}},methods:{playMV:function(t){this.$router.push("/mv-detail/"+t)}}},g=y,b=(n("df6c"),Object(d["a"])(g,v,h,!1,null,"348bc973",null)),w=b.exports,C=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"newSongs",class:[t.program+"newsongs-"+t.theme]},[n("h4",{staticClass:"top"},[t._v("最新音乐")]),n("div",{staticClass:"content"},t._l(t.musicList,(function(e,i){return n("div",{key:i,staticClass:"item",on:{dblclick:function(e){return t.playMusic(i)}}},[n("div",{staticClass:"number"},[n("span",{directives:[{name:"show",rawName:"v-show",value:!("no-id"==t.playId&&i==t.playIndex&&e.name==t.playName),expression:"!(playId=='no-id'&&index == playIndex && item.name == playName)"}]},[t._v(t._s(i+1))]),n("i",{directives:[{name:"show",rawName:"v-show",value:"no-id"==t.playId&&i==t.playIndex&&e.name==t.playName,expression:"playId=='no-id'&&index == playIndex && item.name == playName"}],staticClass:"iconfont icon-V",class:"icon-"+t.theme})]),n("div",{staticClass:"title"},[n("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.picUrl,expression:"item.picUrl"}],attrs:{alt:""},on:{load:t.newSongImgLoad}}),t._m(0,!0)]),n("div",{staticClass:"mess"},[n("div",[t._v(t._s(e.name))]),n("br"),n("div",{staticClass:"bottom"},[t._v(t._s(e.song.album.company))])])])})),0)])},_=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"icon"},[n("i",{staticClass:"iconfont icon-bofang"})])}],x=(n("0a09"),n("ca40")),I=n("8b7f"),L=n("9fe9"),j={name:"newSongs",props:{musicList:{type:Array,default:function(){return[]}}},mixins:[x["a"],a["a"],I["a"],L["a"]],computed:{isPlay:function(){return"no-id"==this.playId&&index==playIndex&&item.name==playName}},methods:{newSongImgLoad:function(){this.imgCount==this.musicList.length&&this.$emit("newSongImgLoad"),this.imgCount++}}},O=j,N=(n("c7d7"),Object(d["a"])(O,C,_,!1,null,"25cce37f",null)),z=N.exports,k={name:"Individuation",mixins:[a["a"]],components:{Swiper:p,PrivateContent:w,NewSongs:z,MusicList:f["a"]},computed:{indiviClass:function(){return["".concat(this.program+"indivi"),"".concat(this.program+"-indivi-"+this.theme)]}},data:function(){return{banner:null,limit:10,personalized:null,privateContent:null,songList:null,musiclist:[]}},created:function(){var t=this;null!=this.$store.state.cookie&&""!=this.$store.state.cookie&&(this.limit=11),Object(r["b"])().then((function(e){t.banner=e.data.banners.slice(0,6)})),Object(r["d"])(this.limit).then((function(e){t.personalized=e.data.result})),Object(r["e"])().then((function(e){t.privateContent=e.data})),Object(r["c"])().then((function(e){t.songList=e.data.result}))},methods:{handleRefresh:function(){},playMusic:function(t){var e=this;this.musiclist=[];var n=function(n){_getSongsDetail(e.songList[n].id).then((function(i){var s=new songDetail(i.data.songs);e.musiclist.push(s),n==e.songList.length-1&&e.PlayMusic(t)}))};for(var i in this.songList)n(i)}}},$=k,S=(n("dd51"),Object(d["a"])($,i,s,!1,null,"562ec146",null));e["default"]=S.exports},af09:function(t,e,n){},c7d7:function(t,e,n){"use strict";n("af09")},d84c:function(t,e,n){"use strict";n("9bde")},dd51:function(t,e,n){"use strict";n("0a5a")},df6c:function(t,e,n){"use strict";n("df6e")},df6e:function(t,e,n){},fba2:function(t,e,n){"use strict";n.d(e,"b",(function(){return s})),n.d(e,"d",(function(){return a})),n.d(e,"e",(function(){return r})),n.d(e,"c",(function(){return c})),n.d(e,"f",(function(){return l})),n.d(e,"a",(function(){return o})),n.d(e,"g",(function(){return u}));var i=n("1bab");function s(){return Object(i["a"])({url:"/banner"})}function a(t){return Object(i["a"])({url:"/personalized",params:{limit:t}})}function r(){return Object(i["a"])({url:"/personalized/privatecontent"})}function c(){return Object(i["a"])({url:"/personalized/newsong"})}function l(){return Object(i["a"])({url:"/toplist"})}function o(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:-1,s=arguments.length>3?arguments[3]:void 0,a=arguments.length>4&&void 0!==arguments[4]?arguments[4]:1;return Object(i["a"])({url:"/artist/list",params:{area:t,type:e,limit:s,initial:n,offset:a}})}function u(t){return Object(i["a"])({url:"/top/song",params:{type:t}})}}}]);
//# sourceMappingURL=chunk-2a94fb1a.65de559b.js.map