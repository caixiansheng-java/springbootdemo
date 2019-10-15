<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0037)http://www.kjson.com/jsformat/?fm=map -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="js在线格式化,javascript在线格式化,html在线格式化,js代码格式化">
<meta name="description" content="可以对javascript/js，HTML进行格式化排版，整齐的进行显示。">
<meta name="author" content="">

<title>HTML/JS压缩格式化 - 在线JSON校验格式化工具(K JSON),
	json解析,json格式化,json在线校验</title>

<link rel="shortcut icon" href="http://www.kjson.com/favicon.png"
	type="image/x-icon">

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../css/metisMenu.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<!-- <link href="/static/v2.0/dist/css/timeline.css" rel="stylesheet"> -->

<!-- Custom CSS -->
<link href="../css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/bdsstyle.css" rel="stylesheet" type="text/css">
</head>

<body>
	<iframe frameborder="0" style="display: none;"
		src="../html/saved_resource.html"></iframe>
	<div id="bdshare"
		style="right: 0px; top: 289px; position: fixed; height: 330px; overflow: hidden;">
		<img
			src="../image/r0.gif"
			alt="" style="float: left; margin-top: 58px;">
		<iframe id="bdsIfr"
			style="position: absolute; display: none; z-index: 9999;"
			frameborder="0"
			src="../html/saved_resource(1).html"></iframe>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="com-top"
				style="height: 30px; width: 100%; background-color: #3D4147; padding: 0 10px 0 10px;">
				<div style="color: #abc18e; padding-top: 5px;">
					Hello world，今天是10月14日(星期一)。 <span class="toplogin"
						style="float: right;"> <a href="javascript:;"
						data-toggle="modal" data-target="#myModal" style="color: #abc18e;">站长微信</a>
					</span>
				</div>
			</div>

			<div class="navbar-header">
				<a class="navbar-brand" href="http://www.kjson.com/">kjson</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-left topnav">
				<li class=""><a href="http://www.kjson.com/a/partner"
					target="_self">合作</a></li>
				<li class=""><a href="http://www.kjson.com/a/aboutus"
					target="_self">关于</a></li>
			</ul>
		</nav>

		<div id="page-wrapper">
			<div style="height: 10px;"></div>
			<div></div>
			<!--Modal-->
			<div class="modal fade" id="myModal" tabindex="999" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">微信二维码</h4>
						</div>
						<div class="modal-body" style="text-align: center;">
							<p>扫描以下二维码，填写验证信息"kjson"，即可.</p>
							<img style="width: 300px;"
								src="../image/me.png">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">JavaScript/HTML格式化</div>
						<div class="panel-body">

							<div style="padding: 7px 0px 0px 6px; text-align: center">
								<select id="tabsize">
									<option value="1">制表符缩进</option>
									<option value="2">2个空格缩进</option>
									<option value="4" selected="selected">4个空格缩进</option>
									<option value="8">8个空格缩进</option>
								</select> <input class="but" value="格式化"
									onclick="return do_js_beautify()" id="beautify" type="button">
								<!--<br>-->
								<input class="but" value="普通压缩" onclick="pack_js(0)"
									type="button"> <input class="but" value="* 加密压缩 *"
									onclick="pack_js(1)" type="button"> <input class="but"
									value="复制" onclick="copy();" type="button"> <input
									class="but" value="清空结果" onclick="Empty();" type="button">
							</div>

							<div style="padding-top: 10px;" align="center">
								<textarea id="content" name="content"
									placeholder="请输入需要处理的javascript/HTML"
									style="font-family: Courier New; border: 1px solid rgb(0, 0, 0); width: 100%; min-height: 300px;"></textarea>
							</div>


						</div>
					</div>
				</div>
			</div>



			<div style="height: 10px;"></div>


			<div class="row">
				<div class="col-lg-12">
					<div class="text-center">
						Copyright © 2013-2019 <a href="http://www.beian.miit.gov.cn/"
							style="color: #CCC;" target="_blank">粤ICP备14072846号</a>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script async=""
		src="../js/analytics.js"></script>
	<script
		src="../js/hm.js"></script>
	<script
		src="../js/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="../js/bootstrap.min.js"></script>

	<script
		src="./HTML_JS压缩格式化 - 在线JSON校../js/t/javascript"></script>
	<script
		src="../js/jsformat.js"
		type="text/javascript"></script>
	<script
		src="../js/jsformat2.js"
		type="text/javascript"></script>
	<script
		src="../js/htmlformat.js"
		type="text/javascript"></script>
	<!-- <script type="text/javascript">
		(function() {
			$(document).ready(
					function() {
						var k = "k";
						k += "j";
						k += "s";
						k += "o";
						k += "n";
						var host = 'www.' + k + '.com';
						if (window.location.host != host) {
							window.location.href = "http://" + host
									+ window.location.pathname
									+ window.location.search;
						}
					});

			(function() {
				return false;
				var menu = [], local_menu = [];
				function add_new(link) {
					var o = $(".topnav li a[href='" + link + "']");
					var text = o.html();
					var f = "<font class='newf' style='color:red;'>•</font>";
					o.html(text + f);

					var p = o.parents('ul').siblings('a.dropdown-toggle');
					if (p) {
						if (p.find(".newf").length == 0) {
							var text = p.html();
							p.html(text + f);
						}
					}
				}
				;
				$(".topnav li a").each(function() {
					menu.push($(this).attr('href'));
				}).on('click', function() {
					local_menu.push($(this).attr('href'));
					localStorage.local_menu = local_menu.toString();
				});

				if (localStorage.local_menu == undefined) {
					localStorage.local_menu = menu.toString();
				} else {
					local_menu = localStorage.local_menu.split(',');
					// find diff
					for ( var i in menu) {
						var isnew = true;
						for ( var j in local_menu) {
							if (menu[i] == local_menu[j]) {
								isnew = false;
								continue;
							}
						}
						if (isnew) {
							// 加红点
							add_new(menu[i]);
						}
					}
				}
			})()

		})()
	</script> -->





	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?b41a45cb6a6d5ade3218bbe18664a643";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>

	<!-- Baidu Button BEGIN -->
	<script type="text/javascript" id="bdshare_js"
		data="type=slide&amp;img=0&amp;pos=right&amp;uid=637701"
		src="../js/bds_s_v2.js"></script>

	<script type="text/javascript">
		var bds_config = {
			"bdTop" : 289
		};
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion="
				+ Math.ceil(new Date() / 3600000);
	</script>
	<!-- Baidu Button END -->

	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'https://www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-101536180-1', 'auto');
		ga('send', 'pageview');
	</script>

	<script type="text/javascript">
		function do_js_beautify() {
			document.getElementById('beautify').disabled = true;
			js_source = document.getElementById('content').value.replace(
					/^\s+/, '');
			tab_size = document.getElementById('tabsize').value;
			tabchar = ' ';
			if (tabsize == 1) {
				tabchar = '\t';
			}
			if (js_source && js_source.charAt(0) === '<') {
				document.getElementById('content').value = style_html(
						js_source, tab_size, tabchar, 80);
			} else {
				document.getElementById('content').value = js_beautify(
						js_source, tab_size, tabchar);
			}
			document.getElementById('beautify').disabled = false;
			return false;
		}
		function pack_js(base64) {
			var input = document.getElementById('content').value;
			var packer = new Packer;
			if (base64) {
				var output = packer.pack(input, 1, 0);
			} else {
				var output = packer.pack(input, 0, 0);
			}
			document.getElementById('content').value = output;
		}
		function copy() {
			var Result = document.getElementById('content').value;
			if (document.getElementById('content').value != '') {
				window.clipboardData.setData("Text", Result);
				document.getElementById('content').select();
				window.alert('已复制成功！');
			}
		}
		function Empty() {
			document.getElementById('content').value = '';
			document.getElementById('content').select();
		}
		function GetFocus() {
			document.getElementById('content').focus();
		}
	</script>


	<script
		src="../js/logger.js"></script>
</body>
</html>