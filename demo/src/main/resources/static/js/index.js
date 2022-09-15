load();
function load() {
    $.ajax({
        url:"/userInfo",
        method:"GET",
        success:function(data){
            if (data.status == 1){
                let h2 = document.querySelector('#myname');
                h2.innerHTML = "你好！" + data.data.username;
                let game = document.querySelector('#gameMes');
                game.innerHTML = "天梯分数："+data.data.score + " | " +" 场数："+data.data.totalCount + " | " +" 获胜场数："+data.data.winCount;

            }else{
                alert(data.message);
                location.assign("login.html");
            }
        }
    })
}

let websocketUrl = 'ws://'+ location.host +'/findMatch';
let websocket = new WebSocket(websocketUrl);

// 连接成功的时候调用的方法
websocket.onopen = function() {
    console.log("onopen");
}

// 连接关闭的时候调用的方法
websocket.onclose = function() {
    console.log("onclose");
}

// 连接异常的时候调用的方法
websocket.onerror = function() {
    console.log("onerrot");
}

// 监听整个窗口关闭的事件, 当窗口关闭, 主动的去关闭websocket连接
window.onbeforeunload = function() {
    websocket.close();
}

// 连接成功收到的响应
websocket.onmessage = function(e) {
    // 先将Json格式 e 化为 响应对象
    let resp = JSON.parse(e.data);
    // 获取到 匹配按钮
    let play = document.querySelector('#beginPlay');
    // 等于-1是错误的起来, 打印错误的信息, 并跳转到登录页面
    if (resp.status == -1) {
        alert(resp.message);
        location.assign("login.html");
        return;
    }
    // 这里就都是正常的响应, 那么就判断是开始匹配, 还是结束匹配
    if (resp.message == 'startMatch') {
        //开始匹配
        console.log("开始匹配");
        play.innerHTML = '匹配中...(点击停止)';
    }else if(resp.message == 'stopMatch') {
        //结束匹配
        console.log("结束匹配");
        play.innerHTML = '开始匹配';
    }else if(resp.message == 'matchSuccess') {
        //匹配成功
        console.log("匹配成功");
        location.assign('room.html');
    }else{
        // 按理不会触发这个else
        alert(resp.message);
        console.log("收到非法响应");
    }
}

// 获取到匹配按钮
let play = document.querySelector('#beginPlay');
// 匹配按钮点击事件
play.onclick = function() {
    // 判断当前 readyState 是否是OPEN状态的
    if (websocket.readyState == websocket.OPEN) {
        // 当前 readyState 处于OPEN 状态, 说明链接是好的
        if (play.innerHTML == '开始匹配') {
            // 发送开始匹配的请求
            websocket.send(JSON.stringify(
                {
                    message: 'startMatch',
                }
            ))
        }else if(play.innerHTML == '匹配中...(点击停止)'){
            // 发送停止匹配的请求
            websocket.send(JSON.stringify(
                {
                    message: 'stopMatch',
                }
            ))
        }
    }else{
        // 这里就是链接异常的情况
        alert('当前您的链接已经断开, 请重新登录');
        location.assign("login.html");
    }
}
