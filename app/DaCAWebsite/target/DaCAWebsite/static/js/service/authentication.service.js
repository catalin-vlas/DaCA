(function(){angular.module("myApp").factory("AuthenticationService",a);a.$inject=["$http","$cookieStore","$rootScope","UserService"];function a(j,h,e,i){var d={};d.Login=g;d.SetCredentials=f;d.ClearCredentials=c;return d;function g(n,l,m){var k;i.getUserByUsername(n).then(function(o){if(o!==null&&o.password===l){k={success:true}}else{k={success:false,message:"Username or password is incorrect"}}m(k)})}function f(m,k){var l=b.encode(m+":"+k);e.globals={currentUser:{username:m,authdata:l}};j.defaults.headers.common.Authorization="Basic "+l;h.put("globals",e.globals)}function c(){e.globals={};h.remove("globals");j.defaults.headers.common.Authorization="Basic"}}var b={keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var c="";var m,k,h="";var l,j,g,f="";var d=0;do{m=e.charCodeAt(d++);k=e.charCodeAt(d++);h=e.charCodeAt(d++);l=m>>2;j=((m&3)<<4)|(k>>4);g=((k&15)<<2)|(h>>6);f=h&63;if(isNaN(k)){g=f=64}else{if(isNaN(h)){f=64}}c=c+this.keyStr.charAt(l)+this.keyStr.charAt(j)+this.keyStr.charAt(g)+this.keyStr.charAt(f);m=k=h="";l=j=g=f=""}while(d<e.length);return c},decode:function(f){var d="";var n,l,j="";var m,k,h,g="";var e=0;var c=/[^A-Za-z0-9\+\/\=]/g;if(c.exec(f)){window.alert("There were invalid base64 characters in the input text.\nValid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\nExpect errors in decoding.")}f=f.replace(/[^A-Za-z0-9\+\/\=]/g,"");do{m=this.keyStr.indexOf(f.charAt(e++));k=this.keyStr.indexOf(f.charAt(e++));h=this.keyStr.indexOf(f.charAt(e++));g=this.keyStr.indexOf(f.charAt(e++));n=(m<<2)|(k>>4);l=((k&15)<<4)|(h>>2);j=((h&3)<<6)|g;d=d+String.fromCharCode(n);if(h!=64){d=d+String.fromCharCode(l)}if(g!=64){d=d+String.fromCharCode(j)}n=l=j="";m=k=h=g=""}while(e<f.length);return d}}})();