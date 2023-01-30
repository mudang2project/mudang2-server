window.addEventListener('DOMContentLoaded', event => {
    $('#datatablesSimple').DataTable({
        ajax : {
            "serverSide" : true,
            "processing" : true,
            "destroy" : true,
            "type" : "GET",
            "url":"/admins/data",
            "dataSrc": ''
        },
        columns : [
            {data: "busIdx"},
            {data: "dayData"},
            {data: "monthData"}
        ],
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    });

    const datatablesSimple = document.getElementById('datatablesSimple');

    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

});