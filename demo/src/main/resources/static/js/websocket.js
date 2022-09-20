// // 此处进行初始化 websocket, 并且实现前端的匹配逻辑.
// // 此处的路径必须写作 /findMatch, 千万不要写作 /findMatch/
// let websocketUrl = 'ws://' + location.host + '/findMatch';
// let websocket = new WebSocket(websocketUrl);
// websocket.onopen = function() {
//     console.log("onopen");
// }
// websocket.onclose = function() {
//     console.log("onclose");
// }
// websocket.onerror = function() {
//     console.log("onerror");
// }
// // 监听页面关闭事件. 在页面关闭之前, 手动调用这里的 websocket 的 close 方法.
// window.onbeforeunload = function() {
//     websocket.close();
// }
//
// // 一会重点来实现, 要处理服务器返回的响应
// websocket.onmessage = function(e) {
//     // 处理服务器返回的响应数据. 这个响应就是针对 "开始匹配" / "结束匹配" 来对应的
//     // 解析得到的响应对象. 返回的数据是一个 JSON 字符串, 解析成 js 对象
//     let resp = JSON.parse(e.data);
//     let matchButton = document.querySelector('#match-button');
//     if (!resp.ok) {
//         console.log("游戏大厅中接收到了失败响应! " + resp.reason);
//         return;
//     }
//     if (resp.message == 'startMatch') {
//         // 开始匹配请求发送成功
//         console.log("进入匹配队列成功!");
//         matchButton.innerHTML = '匹配中...(点击停止)'
//     } else if (resp.message == 'stopMatch') {
//         // 结束匹配请求发送成功
//         console.log("离开匹配队列成功!");
//         matchButton.innerHTML = '开始匹配';
//     } else if (resp.message == 'matchSuccess') {
//         // 已经匹配到对手了.
//         console.log("匹配到对手! 进入游戏房间!");
//         // location.assign("/game_room.html");
//         location.replace("/game_room.html");
//     } else if (resp.message == 'repeatConnection') {
//         alert("当前检测到多开! 请使用其他账号登录!");
//         location.replace("/login.html");
//     } else {
//         console.log("收到了非法的响应! message=" + resp.message);
//     }
// }
//
// // 给匹配按钮添加一个点击事件
// let matchButton = document.querySelector('#match-button');
// matchButton.onclick = function() {
//     // 在触发 websocket 请求之前, 先确认下 websocket 连接是否好着呢~~
//     if (websocket.readyState == websocket.OPEN) {
//         // 如果当前 readyState 处在 OPEN 状态, 说明连接好着的~
//         // 这里发送的数据有两种可能, 开始匹配/停止匹配~
//         if (matchButton.innerHTML == '开始匹配') {
//             console.log("开始匹配");
//             websocket.send(JSON.stringify({
//                 message: 'startMatch',
//             }));
//         } else if (matchButton.innerHTML == '匹配中...(点击停止)') {
//             console.log("停止匹配");
//             websocket.send(JSON.stringify({
//                 message: 'stopMatch',
//             }));
//         }
//     } else {
//         // 这是说明连接当前是异常的状态
//         alert("当前您的连接已经断开! 请重新登录!");
//         location.replace('/login.html');
//     }
// }


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
