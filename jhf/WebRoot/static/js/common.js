initSize();
$(".mLogin").mouseover(function() {
	$(".m-top-logout").show(200);
});
$(".m-top-logout").mouseleave(function() {
	$(".m-top-logout").hide();
});
$(".m-top").mouseleave(function() {
	$(".m-top-logout").hide();
});
$(window).resize(function() {
	initSize();
});

function initSize() {
	$(".m-left").height(window.innerHeight);
	$(".m-right").height(window.innerHeight);
	$(".m-right").width(window.innerWidth - 90);
	$(".m-item").height(window.innerHeight - 100);
	$(".m-content").width($(".m-right").width() - $(".m-item").width());
	$(".m-content").height(window.innerHeight - 100);
	$(".m-container").width(window.innerWidth - 90 - 46 - $(".m-item").width());
}