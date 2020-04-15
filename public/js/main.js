function sendDeleteRequest(url , rUrl) {
    $.ajax({
        url: url,
        method: "DELETE",
        success: function () {
            window.location =  rUrl;
        },
        error: function() {
            window.location.reload();
        }
    });
}

function sendEditRequest(searchParam , rUrl) {
    var form = $('#'+formId);
    $.ajax({
        url: form.attr( 'action' ),
        method: "POST",
        data: searchParam,
        success: function () {
            window.location =  rUrl;
        },
        error: function() {
            window.location.reload();
        }
    });
}