
(function($){$.fn.rssfeed=function(url,options,fn){var defaults={limit:10,header:true,titletag:'h4',date:true,content:true,snippet:true,showerror:true,errormsg:'',key:null,ssl:false,linktarget:'_self'};var options=$.extend(defaults,options);return this.each(function(i,e){var $e=$(e);var s='';if(options.ssl)s='s';if(!$e.hasClass('rssFeed'))$e.addClass('rssFeed');if(url==null)return false;var api="http"+s+"://ajax.googleapis.com/ajax/services/feed/load?v=1.0&callback=?&q="+encodeURIComponent(url);if(options.limit!=null)api+="&num="+options.limit;if(options.key!=null)api+="&key="+options.key;api+="&output=json_xml"
$.getJSON(api,function(data){if(data.responseStatus==200){_process(e,data.responseData,options);if($.isFunction(fn))fn.call(this,$e);}else{if(options.showerror)
if(options.errormsg!=''){var msg=options.errormsg;}else{var msg=data.responseDetails;};$(e).html('<div class="rssError"><p>'+msg+'</p></div>');};});});};var _process=function(e,data,options){var feeds=data.feed;if(!feeds){return false;}
var html='';var row='odd';var xml=getXMLDocument(data.xmlString);var xmlEntries=xml.getElementsByTagName('item');if(options.header)
html+='<div class="rssHeader">'+'<a href="'+feeds.link+'" title="'+feeds.description+'">'+feeds.title+'</a>'+'</div>';html+='<div class="rssBody">'+'<ul>';for(var i=0;i<feeds.entries.length;i++){var entry=feeds.entries[i];var entryDate=new Date(entry.publishedDate);var pubDate=entryDate.toLocaleDateString()+' '+entryDate.toLocaleTimeString();html+='<li class="rssRow '+row+'">'+'<'+options.titletag+'><a href="'+entry.link+'" title="View this feed at '+feeds.title+'" target="'+options.linktarget+'">'+entry.title+'</a></'+options.titletag+'>'
if(options.date)html+='<div>'+pubDate+'</div>'
if(options.content){if(options.snippet&&entry.contentSnippet!=''){var content=entry.contentSnippet;}else{var content=entry.content;}
html+='<p>'+content+'</p>'}
if(xmlEntries.length>0){var xmlMedia=xmlEntries[i].getElementsByTagName('enclosure');if(xmlMedia.length>0){html+='<div class="rssMedia"><div>Media files</div><ul>'
for(var m=0;m<xmlMedia.length;m++){var xmlUrl=xmlMedia[m].getAttribute("url");var xmlType=xmlMedia[m].getAttribute("type");var xmlSize=xmlMedia[m].getAttribute("length");html+='<li><a href="'+xmlUrl+'" title="Download this media">'+xmlUrl.split('/').pop()+'</a> ('+xmlType+', '+formatFilesize(xmlSize)+')</li>';}
html+='</ul></div>'}
html+='</li>';}
if(row=='odd'){row='even';}else{row='odd';}}
html+='</ul>'+'</div>'
$(e).html(html);};function formatFilesize(bytes){var s=['bytes','kb','MB','GB','TB','PB'];var e=Math.floor(Math.log(bytes)/Math.log(1024));return(bytes/Math.pow(1024,Math.floor(e))).toFixed(2)+" "+s[e];}
function getXMLDocument(string){var browser=navigator.appName;var xml;if(browser=='Microsoft Internet Explorer'){xml=new ActiveXObject('Microsoft.XMLDOM');xml.async='false'
xml.loadXML(string);}else{xml=(new DOMParser()).parseFromString(string,'text/xml');}
return xml;}})(jQuery);