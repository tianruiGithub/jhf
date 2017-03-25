var type = "";
var img = "";
var color = "";
var scoreList = "";
var time = 5;
var timer;
var openId = "";
function init(val) {
	$(".w-panel-group").height(window.innerHeight);
	$(".w-panel-content").height($(".w-panel-group").height() - 45);

	if (window.innerWidth < 350) {
		$("#kaishi_s").width(90);
		$("#kaishi_w").width(200);
		$("#kaishi_left").width(145);
		$("#kaishi_right").css("margin-top", "5px");
		$("#kaishi_right").width(125);
		$("#kaishi").width(90);
		$("#kaishi_div").css("margin-top", "10px");
	}

	$("#page0").css("display", "block");
	if ($("#kaishi_box").height() > window.innerHeight) {
		$("#kaishi_bg").height($("#kaishi_box").height());
	}
	openId = val;
}
$("#kaishi").click(function() {
	$("#page0").css("display", "none");
	$("#page1").css("display", "block");

});
$("#first3").click(function() {
 
		$("#page1").css("display", "none");
		$("#page2").css("display", "block");
		initQuestion();
	 
});
$(".type").click(function(){
		if($(this).attr("state") == "0"){
			$(this).attr("state","1");
			$(this).css("background-color","#fe6b5b");
			$(this).css("color","#fff");		
		}
		else{
			$(this).attr("state","0");
			$(this).css("background-color","#fff");
			$(this).css("color","#666");
		}
	});
$("#first2").click(function() {
	type = "";
	$("#page1").css("display", "none");
	$("#page2").css("display", "block");
	initQuestion();

});

$(".face-type").click(function() {
	$(".face-type-img").attr("src", "resources/image/check_unselect.png");
	$("#" + this.id + "Image").attr("src", "resources/image/check_select.png");
	type = $("#" + this.id + " > span").html();
});

$("#second-paizhao").click(function() {
	$("#paizhao").click();
});
$("#second-skip").click(function() {
	$("#page2").css("display", "none");
	$("#page4").css("display", "block");
});

// 第三部
$("#third-back").click(function() {
	$("#page3").css("display", "none");
	$("#page2").css("display", "block");
	img = "";
	color = "";
});
$("#third-rotate").click(function() {
	$("#tongueImage").cropper("rotate", 90);
});
$("#third-cropper").click(function() {
	var croppedCanvas;
	var roundedCanvas;
	croppedCanvas = $("#tongueImage").cropper('getCroppedCanvas');
	roundedCanvas = getRoundedCanvas(croppedCanvas);
	var imageData = roundedCanvas.toDataURL();
	$.ajax({
		type : "post",
		url : "/jhf/data/test/tongue",
		data : {
			"imageData" : imageData
		},
		beforeSend : function() {
			$("#third-cropper").attr("disabled", "disabled");
		},
		success : function(res, textStatus) {
			$("#third-cropper").removeAttr("disabled");
			if (res.status != "success") {
				alert(res.msg);
			} else {
				img = imageData;
				color = res.data;

				$("#page3").css("display", "none");
				$("#page4").css("display", "block");

			}
		}
	});
});
$("#four").click(function() {
	var qList = $(".question");
	var physique;
	var state;
	var score = "";
	for (var i = 0; i < qList.length; i++) {
		physique = $(qList[i]).attr("physique");
		state = $(qList[i]).attr("state");
		if (state == "0") {
			alert("还有题没答完呦");
			return;
		}
		if (score == "") {
			score += physique + "H" + state;
		} else {
			score += "I" + physique + "H" + state;
		}
	}
	scoreList = score;
	$("#page4").css("display", "none");
	$("#page5").css("display", "block");
	beginJishi();

});

function initQuestion() {
	$.ajax({
		type : "post",
		url : "/jhf/data/question/query",
		data : {
			"demandNo" : "07"
		},
		success : function(res, textStatus) {

			$("#questioninfo").html("");
			$.each(res.data,
				function(i, item) {

					$("#questioninfo")
						.append(
							'<li state="0" physique="'
							+ item.physique_no
							+ '" class="question" id="q'
							+ i
							+ '" bh="'
							+ i
							+ '"> '
							+ '<a id="qt'
							+ i
							+ '" href="#q'
							+ i
							+ '"> '
							+ '<div class="question-title-bar"></div> '
							+ ' <div id="qts'
							+ i
							+ '" class="question-title-name">'
							+ item.question_content
							+ '</div> '
							+ '</a>  '
							+ ' <ul class="sub-menu"> '
							+ ' <li class="question-select"> '
							+ ' <div id="question'
							+ i
							+ 'answer1" onclick="selectAnswer('
							+ i
							+ ',1)" style="margin-left:20px;" class="question-answer question'
							+ i
							+ 'answer">'
							+ ' 没有'
							+ '</div>  '
							+ ' <div id="question'
							+ i
							+ 'answer2" onclick="selectAnswer('
							+ i
							+ ',2)" class="question-answer question'
							+ i
							+ 'answer"> '
							+ '   很少'
							+ ' </div>  '
							+ '  <div id="question'
							+ i
							+ 'answer3" onclick="selectAnswer('
							+ i
							+ ',3)" class="question-answer question'
							+ i
							+ 'answer"> '
							+ '    有时'
							+ ' </div>  '
							+ ' <div id="question'
							+ i
							+ 'answer4" onclick="selectAnswer('
							+ i
							+ ',4)" class="question-answer question'
							+ i
							+ 'answer"> '
							+ '   较多'
							+ ' </div>  '
							+ ' <div id="question'
							+ i
							+ 'answer5" onclick="selectAnswer('
							+ i
							+ ',5)" class="question-answer question'
							+ i
							+ 'answer"> '
							+ '  总是'
							+ ' </div> '
							+ ' </li> '
							+ ' </ul>'
							+ ' <div class="question-line"/>'
							+ ' </li>');

				});
			// 折叠面板效果js
			var accordion_head = $('.question-accordion > li > a'),
				accordion_body = $('.question-accordion  li > .sub-menu');
			// Open the first tab on load
			accordion_head.removeClass('active');
			accordion_head.first().addClass('active').next().slideDown(100);
			// Click function
			accordion_head.on('click', function(event) {
				// Disable header links
				event.preventDefault();
				// Show and hide the tabs on click
				if ($(this).attr('class') != 'active') {
					accordion_body.slideUp(100);
					$(this).next().stop(true, true).slideToggle(100);
					accordion_head.removeClass('active');
					$(this).addClass('active');
				}
			});

		}
	});
}
document
	.getElementById('paizhao')
	.addEventListener(
		'change',
		function() {
			var reader = new FileReader();

			reader.onload = function(e) {
				compress(this.result);
			};

			reader.readAsDataURL(this.files[0]);

			var compress = function(res) {
				var img = new Image(),
					maxH = 300;

				img.onload = function() {
					var cvs = document.createElement('canvas'),
						ctx = cvs
							.getContext('2d');

					if (img.height > maxH) {
						img.width *= maxH / img.height;
						img.height = maxH;
					}

					cvs.width = img.width;
					cvs.height = img.height;

					ctx.clearRect(0, 0, cvs.width, cvs.height);
					ctx.drawImage(img, 0, 0, img.width, img.height);

					var dataUrl = cvs.toDataURL('image/jpeg', 1);
					$("#tongueImageDiv")
						.html(
							"<img id='tongueImage'  style='width:300px;height:300px;display:block;background-color:black;'/> ");
					$("#tongueImage").attr('src', dataUrl);
					$("#tongueImage").cropper({
						aspectRatio : 1,
						dragMode : 'move',
						autoCropArea : 1,
						restore : false,
						guides : false,
						highlight : false,
						minCropBoxWidth : 300,
						minCropBoxHeight : 300,
						cropBoxMovable : false,
						cropBoxResizable : false,
						built : function() {
							$("#page2").css("display", "none");
							$("#page3").css("display", "block");
						}
					});
				}
				img.src = res;
			}
		}, false);
function getRoundedCanvas(sourceCanvas) {
	var canvas = document.createElement('canvas');
	var context = canvas.getContext('2d');
	var width = sourceCanvas.width;
	var height = sourceCanvas.height;
	canvas.width = width;
	canvas.height = height;
	context.fillStyle = "#fff";
	context.fillRect(0, 0, width, height);
	context.drawImage(sourceCanvas, 0, 0, width, height, 0, 0, width, height);
	return canvas;
}

function selectAnswer(bh, xh) {
	$(".question" + bh + "answer").css("background-color", "#fff");
	$(".question" + bh + "answer").css("color", "#333");
	$("#question" + bh + "answer" + xh).css("background-color", "#fe6b5b");
	$("#question" + bh + "answer" + xh).css("color", "#fff");
	$("#q" + bh).attr("state", xh);
	$("#qts" + bh).css("color", "#c0c0c0");

	setTimeout(function() {
		$("#qt" + (bh + 1)).trigger("click");
	}, 400);
}

function beginJishi() {
	var t = 0;
	var state = 0;
	this.timer = setInterval(function() {
		time = time - 0.1;

		if (time <= 0) {
			time = 5;
			if (state == 0) {
				state = 1;
				$.ajax({
					type : "post",
					url : "/jhf/data/test/create",
					data : {
						"openId" : openId,
						"demandNo" : "07",
						"imageData" : img,
						"feature" : type,
						"color" : color,
						"scoreList" : scoreList
					},
					success : function(res, textStatus) {
						if (res.status == "success") {
							closeWindow();
						}  else {
							alert("由于网络原因，此次检测无效，请重新检测");
							closeWindow();
						}
					}
				});
			}
			else{
				closeWindow();
			}

		}
		t = time / 5.0;
		$(".jindus").width(window.innerWidth - t * window.innerWidth);

	}, 100);
}