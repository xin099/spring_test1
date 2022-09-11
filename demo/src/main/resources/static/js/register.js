
let Reg = document.querySelector('#Reg');
Reg.onclick = function() {
    let username = document.querySelector('#RegUsername');
    let password1 = document.querySelector('#RegPassword1');
    let password2 = document.querySelector('#RegPassword2');
    if(!$('#checkbox').is(':checked')) {
        alert("请勾选条款");
        return;
    }
    if(username.value.trim() == ""){
        alert("请先输入用户名!");
        username.focus();
        return;
    }
    if(password1.value.trim() == ""){
        alert('请先输入密码!');
        password1.focus();
        return;
    }
    if(password2.value.trim() == ""){
        alert('请再次输入密码!');
        password2.focus();
        return;
    }
    if(username.value.trim().length > 20) {
        alert("用户名长度过长");
        username.value="";
        username.focus();
        return;
    }
    if(password1.value.trim() != password2.value.trim()) {
        alert('两次输入的密码不同!');
        passwrod1.value="";
        password2.value="";
        return;
    }
    if(password1.value.trim().length > 255) {
        alert("当前密码长度过长!");
        password1.value="";
        password2.value="";
        password1.focus();
        return;
    }
    $.ajax({
        url: "/register",
        method: "POST",
        data: JSON.stringify({username: username.value.trim(), password: password1.value.trim()}),
        contentType: "application/json;charset=utf-8",
        success: function(data,status){
            if(data.status == 1) {
                alert(data.message);
                location.assign("login.html");
            }else{
                alert(data.message);
                username.value="";
                password1.value="";
                password2.value="";
                username.focus();
            }
        }
    })
}
