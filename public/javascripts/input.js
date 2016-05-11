var TEAM_URL = "/team";
var CYCLIST_URL = "/cyclist";
var RACE_URL = "/race";
var STAGE_URL = "/stage";

$.ajax({
    url: window.location.origin + TEAM_URL + "s",
    type: "GET"
}).done(function(data){
    $.each(data,function(index,value){
        $("#team-select").append("<option value='" + value.id + "'>" + value.name + "</option>");
    });
});

$.ajax({
    url: window.location.origin + RACE_URL + "s",
    type: "GET"
}).done(function(data){
    $.each(data,function(index,value){
        $("#stage-race").append("<option value='" + value.id + "'>" + value.name + " " + value.year + "</option>");
    });
});

//---------------------------- TEAM -----------------------------------------//

$(document).on("click","#create-team",function(event){
    event.preventDefault();
    if (!empty($("#uciCode"),$("#teamName")) && $("#uciCode").val().trim().length == 3) {
        var data = {};
         data.name = $("#teamName").val().trim();
         data.uciCode = $("#uciCode").val().trim().toUpperCase();
        $.ajax({
             url: window.location.origin + TEAM_URL,
             data: data,
             type: "POST"
        }).done(function(data){
            $("#teamName").val("");
            $("#uciCode").val("");
        }).fail(function(a,b,error){
            alert(error);
        });
    } else {
        alert("Team name empty or UCI Code empty ")
    }
});

//---------------------------- CYCLIST -----------------------------------------//

$(document).on("click","#create-cyclist",function(event){
    event.preventDefault();
    if (!empty($("#firstName"),$("#country"),$("#lastName"),$("#country"),$("#birthday")) && $("#country").val().length == 3 ) {
        var data = {};
        data.firstName = $("#firstName").val().trim();
        data.lastName = $("#lastName").val().trim();
        data.country = $("#country").val().trim().toUpperCase();
        data.birthday = new Date($("#birthday").val()).getTime();
        data.teamId = Number($("#team-select").val());
        $.ajax({
             url: window.location.origin + CYCLIST_URL,
             data: data,
             type: "POST"
        }).done(function(data){
            $("#firstName").val("");
            $("#lastName").val("");
            $("#country").val("");
            $("#birthday").val("");
        }).fail(function(a,b,error){
            alert(error);
        });
    } else {
        alert("Empty input fields");
    }
});

//---------------------------- RACE -----------------------------------------//

$(document).on("click","#create-race",function(event){
    event.preventDefault();
    var data = {};
    data.year = Number($("#race-year").val());
    data.name = $("#race-type").val();
    $.ajax({
        type: "POST",
        url: window.location.origin + RACE_URL,
        data: data
    }).done(function(data){
        alert("Race created");
    }).fail(function(a,b,error){
        alert(error);
    });
});

//---------------------------- STAGE -----------------------------------------//

$("#is-ttt").on("change",function(event){
    if ($(this).prop("checked")) {
        $(".not-ttt").hide();
    } else {
        $(".not-ttt").show();
    }
});

$(document).on("click","#create-stage",function(event){
    event.preventDefault();
     var data = {};
    data.isTTT = $("#is-ttt").prop("checked");
    data.hasMC = $("#has-mc").prop("checked");
    data.hasRC = $("#has-rc").prop("checked");
    data.stageNumber = Number($("#stage-number").val());
    data.description = $("#stage-description").val().trim();
    data.raceId = Number($("#stage-race").val());
    data.mHC = Number($("#m-hc").val());
    data.m1 = Number($("#m-1").val());
    data.m2 = Number($("#m-2").val());
    data.m3 = Number($("#m-3").val());
    data.m4 = Number($("#m-4").val());
    data.sInt = Number($("#s-int").val());
    $.ajax({
        type: "POST",
        url: window.location.origin + STAGE_URL,
        data: data
    }).done(function(data){
        alert("Stage created");
    }).fail(function(a,b,error){
        alert(error);
    });
});

//---------------------------- HELPER METHODS -----------------------------------------//

var empty = function() {
    var result = false;
    for (var i = 1; i < arguments.length; i++)
    {
       result = result || $(arguments[i]).val().trim() == "";
    }
    return result;
};


