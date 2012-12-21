// Google Site Search
(function() {
	var cx = '009670124967221547740:nxzwpnwhlg8';
	var gcse = document.createElement('script');
	gcse.type = 'text/javascript';
	gcse.async = true;
	gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//www.google.com/cse/cse.js?cx=' + cx;
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(gcse, s);
})();

// Prepare container for GA events
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-1693297-1']);
_gaq.push(['_setAllowLinker', true]);
// cdt = Cross domain tracker
_gaq.push(['cdt._setAccount', 'UA-1693297-29']);
_gaq.push(['cdt._setAllowLinker', true]);


// Virtual URL is used by pages that are a part of a Google Analytics goal funnel
ga_virtual_url = '';

// Google Analytics cross-domain tracking helper to ensure outbound links pass the


function openWindow(link) {
	_gaq.push(function() {
		var tracker = _gat._getTrackerByName('UA-1693297-29'); //add name param if needed
		if (link.target == "_blank") {
			window.open(tracker._getLinkerUrl(link.href));
		} else {
			window.location = tracker._getLinkerUrl(link.href);
		}
	});
	return false;
}

// JQuery hooks
$(document).ready(function() {

	// Google Analytics
	var sys_err = $('#sys-error');
	if (sys_err.length > 0) {
		// If an error occurs, don't count it as a page view. Capture it as an error event.
		var sys_err_code = $('#sys-error #sys-error-code:first').text();
		var sys_err_msg = $('#sys-error #sys-error-msg:first').text();
		var page_info = 'page=' + document.location.pathname + document.location.search + '&from=' + document.referrer;
		_gaq.push(['_trackEvent', 'Errors', sys_err_code + ' : ' + sys_err_msg, page_info]);
		_gaq.push(['cdt._trackEvent', 'Errors', sys_err_code + ' : ' + sys_err_msg, page_info]);
		//alert('sys_err_code: ' + sys_err_code + '\n' + 'sys_err_msg: ' + sys_err_msg + '\n' + 'page_info: ' + page_info);
	} else if (ga_virtual_url != '') {
		_gaq.push(['_trackPageview', ga_virtual_url]);
		_gaq.push(['cdt._trackPageview', ga_virtual_url]);
	} else {
		// If no error occurs, and this isn't a virtual url, count as a normal page view
		_gaq.push(['_trackPageview']);
		_gaq.push(['cdt._trackPageview']);
	}

	// Google eCommerce Tracking
	if (ga_virtual_url == '/checkout/complete') {
		_gaq.push(['_trackTrans']);
		_gaq.push(['cdt._trackTrans']);
	}

	// Google Async code
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();

	// See if Eloqua needs to update a form
	if (typeof(GetElqCustomerGUID) != "undefined") {
		var eg = GetElqCustomerGUID();
		$('#elqCustomerGUID').val(eg);
	}

	// EULA Retrofit
	if ($("p.question:contains('EULA')").length > 0) {
		var q = $("p.question:contains('EULA')");
		q.html(q.html().replace(/EULA/ig, '<a target="_blank" href="/Usage/Master-EULA?iframe=true&width=800&height=600">EULA</a>'));
	}

	// Setup Main Nav
	$('ul.nav-main-sf').superfish({
		delay: 500,
		animation: {
			opacity: 'show',
			height: 'show'
		},
		speed: 'fast',
		autoArrows: false,
		dropShadows: false
	});


	// Google Analytics helpers
	// helper function - allow regex as jQuery selector
	$.expr[':'].regex = function(e, i, m) {
		var mP = m[3].split(','),
			l = /^(data|css):/,
			a = {
				method: mP[0].match(l) ? mP[0].split(':')[0] : 'attr',
				property: mP.shift().replace(l, '')
			},
			r = new RegExp(mP.join('').replace(/^\s+|\s+$/g, ''), 'ig');
		return r.test($(e)[a.method](a.property));
	};

	// _trackDownloads
/* Commented Out, because this method is colliding with prettyPhoto
	     $('a:regex(href,"\\.(zip|mp\\d+|mpe*g|pdf|docx*|pptx*|xlsx*|jpe*g|png|gif|tiff*)")$').live('click', function(e) {
	        _gaq.push(['_trackEvent', 'download', 'click', this.href.replace(/^.*\/\//, '')]);
	        _gaq.push(['cdt._trackEvent', 'download', 'click', this.href.replace(/^.*\/\//, '')]);
	    });
	    */

	// _trackMailTo
	$('a[href^="mailto"]').live('click', function(e) {
		_gaq.push(['_trackSocial', 'email', 'send', this.href.replace(/^mailto:/i, '')]);
		_gaq.push(['cdt._trackSocial', 'email', 'send', this.href.replace(/^mailto:/i, '')]);
	});

	// _trackOutbound 
	$('a[href^="http"]:not([href*="//' + location.host + '"])').live('click', function(e) {
		_gaq.push(['_trackEvent', 'outbound', 'click', this.href.match(/\/\/([^\/]+)/)[1]]);
		_gaq.push(['cdt._trackEvent', 'outbound', 'click', this.href.match(/\/\/([^\/]+)/)[1]]);
	});

	// Google Analytics cross-domain tracking helpers
	// Creating custom :external selector
	$.expr[':'].external = function(obj) {
		return !obj.href.match(/^mailto\:/) && !obj.href.match(/^javascript\:/) && !obj.href.match(/youtube/) && (obj.hostname != location.hostname);
	};

	// Add 'external' CSS class to all external links (exclude news page)
	$('a:external').addClass('external');
	$('a:external').not('.subtree_level_3_node_id_263 a:external').click(function() {
		return openWindow(this);
	});

	// Make sure forms that post across domains push _linkByPost
	$("form").submit(function() {
		if (this.action.match(location.hostname) != null && this.action.match(/^http|https\:/)) {
			_gaq.push(['cdt._linkByPost', this]);
		}
	});

	// Setup connect icons
	$('#header-connect').qtip({
		content: $('#header .connect-options'),
		position: {
			adjust: {
				x: 50,
				y: 100
			}
		},
		hide: {
			fixed: true,
			delay: 250
		},
		position: {
			corner: {
				target: 'bottomLeft',
				tooltip: 'topRight'
			},
			adjust: {
				screen: true
			}
		},
		style: {
			width: {
				max: 500
			},
			border: {
				radius: 8
			},
			name: 'light',
			tip: true
		}
	});

	// Add qtip to imagemaps
	$('area').each(function() {
		$(this).qtip({
			content: $(this).attr('alt'),
			// Use the ALT attribute of the area map
			position: {
				corner: {
					target: 'topRight',
					tooltip: 'bottomLeft'
				},
				adjust: {
					screen: true,
					x: -48,
					y: -10
				}
			},
			hide: {
				fixed: true,
				delay: 250
			},
			style: {
				width: {
					max: 500
				},
				border: {
					radius: 8
				},
				name: 'light',
				tip: true
			}
		});
	});

	// Setup pagecurl
	$("#pagecurl").hover(function() { //On hover...
		$("#pagecurl img , #pagecurl .msg").stop().animate({ //Animate and expand the image and the msg (Width + height)
			width: '300px',
			height: '300px'
		}, 500);
	}, function() {
		$("#pagecurl img").stop() //On hover out, go back to original size 50x50
		.animate({
			width: '50px',
			height: '50px'
		}, 220);
		$("#pagecurl .msg").stop() //On hover out, go back to original size 50x50
		.animate({
			width: '50px',
			height: '50px'
		}, 200); //Note this one retracts a bit faster (to prevent glitching in IE)
	});

	// Setup Feature Matrixes
	$(".feature").each(function() {
		$(this).qtip({
			hide: {
				fixed: true,
				delay: 250
			},
			content: $('#desc-' + this.id),
			position: {
				corner: {
					target: 'rightMiddle',
					tooltip: 'leftMiddle'
				}
			},
			style: {
				width: {
					max: 500
				},
				border: {
					radius: 6,
					color: '#9CC1DE'
				},
				name: 'light',
				tip: true
			}
		});
	});


	// Setup super footer connect links 
	$('#footer-links a[title]').qtip({
		position: {
			corner: {
				target: 'topLeft',
				tooltip: 'bottomRight'
			},
			adjust: {
				screen: true
			}
		},
		style: {
			textAlign: 'center',
			width: {
				max: 270
			},
			border: {
				radius: 8
			},
			name: 'light',
			tip: true
		}

	});

	// Setup Uniform
	if ($('.uni-form').length > 0) {
		$(".uni-form select, .uni-form input[type='checkbox'], .uni-form input[type='radio'], .uni-form input[type='file'], .uni-form input[type='text'], .uni-form input[type='button'], .uni-form input[type='submit'], .uni-form input[type='reset'], .uni-form textarea").uniform();
		$(".justify").each(function() {
			maxi = 0
			$(this).find("label").each(function() {
				width = $(this).width();
				if (width > maxi) {
					maxi = width;
				}
			});
			maxi = maxi * 1.1;
			$(this).find("label").width(maxi);
		});
	}


	// Setup expandable definition lists
	$('dl.expandable dt').live('click', function() {
		var dd = $(this).next();
		var w = dd.parent().width() * 0.95;
		dd.css({
			width: w
		});
		if (!dd.is(':animated')) {
			dd.slideToggle(750);
			$(this).toggleClass('opened');
		}
	});

	$('a.expand-all').click(function() {
		if ($(this).hasClass('collapse')) {
			$('dl.expandable dt.opened').click();
		} else $('dl.expandable dt:not(.opened)').click();
		$(this).toggleClass('expand collapse');
		return false;
	});


	// Toggel vissibility of items
	cur_ref = $('.displayed');
	$('a.show[rel]').click(function() {
		var target = $('#' + $(this).attr('rel'));
		if (!$(target).hasClass('displayed')) {
			$('#' + cur_ref.attr('id') + '-btn').toggleClass('current');
			$('#' + target.attr('id') + '-btn').toggleClass('current');
			$(cur_ref).fadeOut(350, function() {
				$(cur_ref).toggleClass('displayed');
				$(target).fadeIn(350, function() {
					$(target).toggleClass('displayed');
					cur_ref = $(target);
				});
			});
		}
		return false;
	});


	/* Checkout method */
	if ($("input[name$='payment_type']").length > 0) {

		if ($("#payment_type_cc").attr('checked')) {
			$("#method-cc").show();
		} else {
			$("#method-cc").hide();
		}

		if ($("#payment_type_pp").attr('checked')) {
			$("#method-pp").show();
		} else {
			$("#method-pp").hide();
		}

		$("input[name$='payment_type']").click(function() {
			var payment_type = $(this).val();
			if (payment_type == 'cc') {
				$("#method-pp").slideUp('fast', function() {
					$("#method-cc").slideDown('fast');
				});
			} else if (payment_type == 'pp') {
				$("#method-cc").slideUp('fast', function() {
					$("#method-pp").slideDown('fast');
				});

			}
		});

	}



	// Update the sidebar in the books
	if ($('.book #sidebar').length > 0) {
		// Add Trial and Demo links
		// Add the demo links
		$('.ss-book-mcookbook #sidebar, .ss-book-mvnref #sidebar, .ss-book-mvnrefbook #sidebar, .ss-book-mvnex #sidebar, .ss-book-nxbook #sidebar, .ss-book-m2eclipse #sidebar, .ss-book-tychobook #sidebar').append('<img src="/var/ezflow_site/storage/images/media/images/book-promo-nexus-header/11770-1-eng-US/Book-Promo-Nexus-Header.png" alt="Need More from Nexus? Download a 14 Day Trial" /><a href="/Products/Nexus-Professional/Purchase/Free-Trial" title="Download Nexus Pro Trial"><img src="/var/ezflow_site/storage/images/media/images/book-promo-download-nexus-pro/12637-1-eng-US/Book-Promo-Download-Nexus-Pro.png" alt="Download Nexus" /></a><br /><br />');

		// Add the feedburner recent blog posts
		$('.book #sidebar').append('<h3>Recent Sonatype Posts</h3><div id="recent-posts"></div>');
		// Add ticker element first then do this
		//$('.book #sidebar some-element')
		$('.book #sidebar #recent-posts').rssfeed('http://www.sonatype.com/people/feed/', {
			limit: 5,
			header: false,
			titletag: 'h4',
			date: false,
			content: false,
			snippet: true,
			showerror: false,
			errormsg: '',
			key: null,
			ssl: false,
			linktarget: '_self'
		}, function(e) {
			$(e).find('div.rssBody').vTicker({
				speed: 700,
				pause: 6000,
				showItems: 5,
				animation: '',
				mousePause: true,
				isPaused: false
			});
		});


		// Add the connect links
		$('.book #sidebar').append('<h3>Connect With Sonatype</h3><ul class="connect-options"><li><a class="bgi30 i30-blog" href="/people" title="Tips, Tricks, and news on all things Maven, Hudson, Nexus, and Central!">Follow the Sonatype Blog</a></li><li><a class="bgi30 i30-newsletter" href="/Request/Newsletter" title="So you prefer email? No problem! Sign-up and we\'ll keep you current.">Get the Sonatype Newsletter</a></li><li><a class="bgi30 i30-twitter" target="_blank" href="http://twitter.com/SonatypeCM" title="Follow Sonatype on Twitter!">Follow Sonatype on Twitter</a></li><li><a class="bgi30 i30-youtube" target="_blank" href="http://www.youtube.com/user/sonatype" title="Watch Sonatype\'s YouTube Channel!">Tune in on YouTube</a></li><li><a class="bgi30 i30-linkedin" target="_blank" href="http://www.linkedin.com/company/sonatype" title="Follow us on LinkedIn!">Connect on LinkedIn</a></li></ul>');

	}




	if ($('.youtube-player').length > 0) {
		var ytp_config = {
			width: 640,
			// player width (integer or string)
			height: 360,
			// player height (integer or string)
			showPlaylist: 0,
			// show playlist on plugin init (boolean)
			showTime: 0,
			// show current time and duration in toolbar (boolean)
			showTitleOverlay: 0,
			// show video title overlay text (boolean)
			repeat: 0,
			// repeat videos (boolean)
			chromeless: 0,
			// chromeless player (boolean)
			highDef: 1,
			// high definition quality or normal quality (boolean)
			showToolbar: 0,
			// show or hide the custom toolbar (boolean)
			playlist: {
				title: 'Random videos',
				videos: $("a.video-trigger").map(function() {
					return {
						id: $(this).attr('href').replace(/^[^v]+v.(.{11}).*/, '$1'),
						title: $(this).html()
					};
				})
			}
		};

		player = $('.youtube-player').player(ytp_config);
		p = player.player('plugin');

		function playYTVideo(vid, vtitle) {
			p.loadVideo({
				id: vid,
				title: vtitle
			});
			p.playVideo();
		}

		$("a.video-trigger").click(function() {
			playYTVideo($(this).attr('href').replace(/^[^v]+v.(.{11}).*/, '$1'), $(this).html());
			return false;
		});
	}

	// Setup Image Zooming
	$("a[rel^='prettyPhoto']").prettyPhoto();

});




// Swap in Flash banner
/*

// This should only happen on the homepage

var flashvars = {};
var params = {wmode:"transparent"};
var attributes = {};
swfobject.embedSWF("../flash/bnr-home-flash.swf", "bnr-home-flash", "996", "240", "9.0.0", "../javascripts/swfobject/expressInstall.swf", flashvars, params, attributes);
*/

// AddThis Social Widgets
if (typeof addthis_config !== "undefined") {
	addthis_config.data_track_clickback = false
} else {
	var addthis_config = {
		data_track_clickback: false
	};
}

var addthis_share = {
	url_transforms: {
		shorten: {
			twitter: 'bitly'
		},
	},
	shorteners: {
		bitly: {
			username: 'aloneySonatype',
			apiKey: 'R_6efdc02fe538a05bfcc9f3a40b06d8c7'
		}
	}
}
