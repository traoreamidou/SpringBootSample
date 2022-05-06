'use strict';

jQuery(function ($){
    $("#btn-update").click(function (event) { 
        updateUser();
    });
    $("#btn-delete").click(function (event) { 
        deteleUser();
    });
});

function updateUser() {
    var formData = $("#user-detail-form").serializeArray();

    //ajax communication
    $.ajax({
        type: "PUT",
        cache: false,
        url: "/user/update",
        data: formData,
        dataType: "json",
    }).done(function(data) {
        alert("Updated user");
        window.location.href="/user/list"
    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("Failed to update user");
    }).always(function() {

    });
}

function deteleUser() {
    var formData = $("#user-detail-form").serializeArray();
    $.ajax({
        type: "DELETE",
        cache: false,
        url: "/user/delete",
        data: formData,
        dataType: "json"
    }).done(function(data) {
        alert("Deleted user");
        window.location.href="/user/list"
    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("Failed to delete user");
    }).always(function() {

    });
}
