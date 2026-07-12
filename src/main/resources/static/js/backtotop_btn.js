$(document).ready(function() {
    // 1. 动态创建按钮的 HTML 结构（直接把你的代码复制进来作为字符串）
    var $backToTopBtn = $(
        '<button type="button" id="backToTopBtn" class="btn position-fixed bottom-0 d-none end-0 p-3 m-3 border-0 shadow-none">' +
        '  <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-arrow-up-square" viewBox="0 0 16 16">' +
        '    <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm8.5 9.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707z"/>' +
        '  </svg>' +
        '</button>'
    );

    // 2. 将创建好的按钮追加到 body 标签的最底部
    $('.container').append($backToTopBtn);

    // 3. 绑定点击事件（点击后平滑滚动回顶部）
    $backToTopBtn.click(function() {
        $("html, body").animate({ scrollTop: 0 }, 100); 
        return false; 
    });

    // 4. 智能显隐逻辑：因为按钮有 d-none 类，默认是看不见的
    // 当页面向下滚动超过 300 像素时，移除 d-none 显现；滚回顶部时重新隐藏
    $(window).scroll(function() {
        if ($(this).scrollTop() > 300) {
            $backToTopBtn.removeClass('d-none');
        } else {
            $backToTopBtn.addClass('d-none');
        }
    });
});