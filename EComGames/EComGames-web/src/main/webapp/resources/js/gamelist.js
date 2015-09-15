jQuery(document).ready(function($) {
    $(".clickable-row td:not(:last-child)").click(function() {
        window.document.location = $(this).parent().data("href");
    });
});