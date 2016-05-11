var stage = new Stage();
$(document).on("change","#stages select",function(event) {
    stage = new Stage();
    var stageId = $(this).find("option:selected").attr("data-stage-number");
    stage.setId(stageId);
    $("input").val("");
    $("div.empty div.list-group").addClass("invisible");
    $("div.empty div.list-group." + stageId).removeClass("invisible");
});

$(document).on("ready",function(){
    $(".select-cyclists:not(.ttt) input").autocomplete({
      source: riders
    });
    $(".select-cyclists.ttt input").autocomplete({
      source: teams
    });
    stage.setId($("#stages select option:selected").val());
});

$(document).on("click","div.empty .list-group-item",function(event) {
    event.preventDefault();
    var stageNumber = Number($("#stages select option:selected").attr("data-stage-number"));
    var stageId = Number($("#stages select option:selected").val());

    if ($(this).hasClass("gc")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.gc").show("slow");
    } else if ($(this).hasClass("rc")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.rc").show("slow");
    } else if ($(this).hasClass("mc")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.mc").show("slow");
    } else if ($(this).hasClass("stage")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.stage").show("slow");
    } else if ($(this).hasClass("ttt")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.ttt").show("slow");
    } else if ($(this).hasClass("sprint")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.sprint").show("slow");
    } else if ($(this).hasClass("m1")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.m1").show("slow");
    } else if ($(this).hasClass("m2")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.m2").show("slow");
    } else if ($(this).hasClass("m3")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.m3").show("slow");
    } else if ($(this).hasClass("m4")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.m4").show("slow");
    } else if ($(this).hasClass("mhc")) {
        $("div.completed,div.select-cyclists").hide("fast");
        $("div.select-cyclists.mhc").show("slow");
    }
});

var getClassificationType = function(elem) {
    var type = "";
    if ($(elem).hasClass("gc")) {
        type = "gc";
    } else if ($(elem).hasClass("mc")) {
        type = "mc";
    } else if ($(elem).hasClass("rc")) {
        type = "rc";
    } else if ($(elem).hasClass("ttt")) {
        type = "ttt";
    } else if ($(elem).hasClass("stage")) {
        type = "stage";
    } else if ($(elem).hasClass("s-int")) {
        type = "s-int";
    } else if ($(elem).hasClass("m1")) {
        type = "m1";
    } else if ($(elem).hasClass("m2")) {
        type = "m2";
    } else if ($(elem).hasClass("m3")) {
        type = "m3";
    } else if ($(elem).hasClass("m4")) {
        type = "m4";
    } else if ($(elem).hasClass("mhc")) {
        type = "mhc";
    }
    return type;
};

$(document).on("click","div.completed .list-group-item:visible",function(event){
    var type = getClassificationType($(this));
    $("div.completed").hide();
    $(".select-cyclists." + type).show("fast");
});

$(document).on("click","#save-stage",function(event){
    $.ajax({
        type: "POST",
        url: window.location.origin + "/classification",
        data: JSON.stringify(stage.toJSON()),
        contentType: "application/json"
    }).done(function(result){
        alert(result);
    }).fail(function(a,b,c){
        alert("Error");
    });
});

$(document).on("click", ".select-cyclists button", function(event){
    event.preventDefault();
    $(".select-cyclists:not(.ttt) input:visible").each(function(index,value){
        var name = $(this).val().trim();
        if ($("span.name").text().indexOf(name) == -1 || name == "") {
            $(this).parent().addClass("has-error");
        } else {
            $(this).parent().removeClass("has-error");
        }
    });
    $(".select-cyclists.ttt input:visible").each(function(index,value){
            var name = $(this).val().trim();
            if ($("span.team-name").text().indexOf(name) == -1 || name == "") {
                $(this).parent().addClass("has-error");
            } else {
                $(this).parent().removeClass("has-error");
            }
        });
    if ($(".has-error:visible").length != 0) {
        alert("Hay errores en la elección de corredores");
    } else {
        var currentListGroup = $(this).parent();
        var type = getClassificationType(currentListGroup);
        switch(type) {
        case "gc":
            stage.setGC(getCyclistsArray());
            break;
        case "rc":
            stage.setRC(getCyclistsArray());
            break;
        case "mc":
            stage.setMC(getCyclistsArray());
            break;
        case "stage":
            stage.setStage(getCyclistsArray());
            break;
        case "ttt":
            stage.setTTT(getTeamsArray());
            break;
        case "s-int":
            stage.setSInt(getCyclistsArray());
            break;
        case "m1":
            stage.setM1(getCyclistsArray());
            break;
        case "m2":
            stage.setM2(getCyclistsArray());
            break;
        case "m3":
            stage.setM3(getCyclistsArray());
            break;
        case "m4":
            stage.setM4(getCyclistsArray());
            break;
        case "mhc":
            stage.setMHC(getCyclistsArray());
            break;
        }
        var element = $("div.empty a.list-group-item." + type);
        element.remove();
        $("div.completed div.list-group").append(element);
        $(currentListGroup).hide();
        $("div.completed").show("slow");
        if ($("div.empty .list-group-item").length == 0) {
            $("#save-stage").show();
            $("#save-stage").css("display","block");
        } else {
            $("#save-stage").hide();
        }
    }
});

var getCyclistsArray = function(){
    var array = [];
    $(".select-cyclists:visible input").each(function(index,value){
        array.push(getCyclistRaceId($(this).val()));
    });
    return array;
}

var getTeamsArray = function(){
    var array = [];
    $(".select-cyclists:visible input").each(function(index,value){
        array.push(getTeamId($(this).val()));
    });
    return array;
}

var getCyclistRaceId = function(name) {
    return Number($("#modal-cyclists-classification .list-group-item:contains(" + name +")").attr("data-cyclist-race-id"));
}

var getTeamId = function(name) {
    return Number($(".hidden-teams .list-group-item:contains(" + name +")").attr("data-team-id"));
}

var getCyclistName = function(cyclistRaceId) {
    return $("#modal-cyclists-classification .list-group-item[data-cyclist-race-id=" + cyclistRaceId).text();
}