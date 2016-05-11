$.getJSON(window.location.origin + "/races/available").done(function(data) {
    $.each(data,function(index,value) {
        var raceName = value.name.substr(0, 1).toUpperCase() + value.name.substr(1).toLowerCase();
        $("ul.dropdown-menu-race").append("<li><a href='#'>" + raceName + " " + value.year + "</a></li>");
        $("#races-modal").find(".list-group").append('<a href="' + window.location.origin + '/admin/' + raceName + '/' +
         value.year + '" class="list-group-item">'+ raceName + ' ' + value.year +'</a>');
    });
});

$(document).on("click","#cyclist-race-link",function(event){
    event.preventDefault();
    $("#races-modal").modal("show");
});