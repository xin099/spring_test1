let loginButton = document.querySelector('#loginButton');
loginButton.onclick = function () {
    let username = document.querySelector('#loginUsername');
    let password = document.querySelector('#loginPassword');
    if (username.value.trim() == "") {
        alert('请先输入用户名！');
        username.focus();
        return;
    }
    if (password.value.trim() == "") {
        alert('请先输入密码！');
        password.focus();
        return;
    }
    $.ajax({
        url: "/login",
        method: "POST",
        data: JSON.stringify({username:username.value.trim(), password:password.value.trim()}),
        contentType: "application/json;charset=utf-8",
        success: function (data, status) {
            if (data.status == 1) {
                location.assign("index.html");
            } else {
                alert(data.message);
                username.value = "";
                password.value = "";
                username.focus();
            }
        }
    })
}