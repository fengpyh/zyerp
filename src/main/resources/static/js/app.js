$(function() {
	//console.log("checkLogined!");
	var tk = $.cookie("accessToken");
	//var email = $.cookie("email");
	console.log(tk);
	
	
	
	//=============================================
	//backToTopBtn BUTON
	var backToTopBtn = $("#backToTopBtn");

    // Show or hide the button based on scroll position
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) { // Show button after scrolling 100px
            backToTopBtn.removeClass("d-none").fadeIn(); // Use fadeIn for a smooth appearance
        } else {
            backToTopBtn.fadeOut().addClass("d-none"); // Use fadeOut for a smooth disappearance
        }
    });

    // Smooth scroll to top when button is clicked
    backToTopBtn.click(function() {
        $("html, body").animate({ scrollTop: 0 }, 100); // Smooth animation over 800ms
        return false; // Prevent default link behavior if using an anchor tag
    });
});

$("#logout_btn").click(function(){
	console.log("logout");
	$.cookie("accessToken",null);
	$.cookie("email",null);
	var ref_url = contextPath +"index";
    console.log("ref_url=" + ref_url);
    window.location.href= ref_url;
});
