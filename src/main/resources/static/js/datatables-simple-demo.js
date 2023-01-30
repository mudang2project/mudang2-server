window.addEventListener('DOMContentLoaded', event => {
    $('#datatablesSimple1').DataTable({
        ajax : {
            "serverSide" : true,
            "processing" : true,
            "destroy" : true,
            "type" : "GET",
            "url":"/admins/recent/gps",
            "dataSrc": ''
        },
        columns : [
            {data: "busIdx"},
            {data: "lat"},
            {data: "lon"},
            {data: "createdAt"},
            {data: "update"}
        ],
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    });

    $('#datatablesSimple2').DataTable({
        ajax : {
            "serverSide" : true,
            "processing" : true,
            "destroy" : true,
            "type" : "GET",
            "url":"/admins/recent/camera",
            "dataSrc": ''
        },
        columns : [
            {data: "location"},
            {data: "headCount"},
            {data: "createdAt"},
            {data: "update"}
        ],
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error)
        }
    });

    const datatablesSimple = document.getElementById('datatablesSimple1');
    const datatablesSimple2 = document.getElementById('datatablesSimple2');

    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple1);
    }
    if (datatablesSimple2) {
        new simpleDatatables.DataTable(datatablesSimple2);
    }
});