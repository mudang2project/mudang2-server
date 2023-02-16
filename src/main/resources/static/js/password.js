var pw = "mudang2";

function login() {
        let lpw = document.querySelector('#inputPassword');

        if (lpw.value == "") {
            alert("로그인할 수 없습니다.")
        }
        else if (pw == lpw.value) {
            location.href = "../main.html";

        } else {
                alert("비밀번호가 틀렸습니다.")
        }


}

function change() {
        let npw = document.querySelector('#inputPassword');
        let rpw = document.querySelector('#inputPasswordConfirm');

        if (npw.value == "" | rpw.value == "") {
            alert("비밀번호를 바꿀 수 없습니다.")
        } else if (npw.value == rpw.value) {

            location.href = "../index.html";
        } else {
            alert("비밀번호가 틀렸습니다.")
        }

}