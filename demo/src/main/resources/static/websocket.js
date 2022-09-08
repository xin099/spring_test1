// 此处进行初始化 websocket, 并且实现前端的匹配逻辑.
// 此处的路径必须写作 /findMatch, 千万不要写作 /findMatch/
let websocketUrl = 'ws://' + location.host + '/findMatch';
let websocket = new WebSocket(websocketUrl);
websocket.onopen = function() {
    console.log("onopen");
}
websocket.onclose = function() {
    console.log("onclose");
}
websocket.onerror = function() {
    console.log("onerror");
}
// 监听页面关闭事件. 在页面关闭之前, 手动调用这里的 websocket 的 close 方法.
window.onbeforeunload = function() {
    websocket.close();
}

// 一会重点来实现, 要处理服务器返回的响应
websocket.onmessage = function(e) {
    // 处理服务器返回的响应数据. 这个响应就是针对 "开始匹配" / "结束匹配" 来对应的
    // 解析得到的响应对象. 返回的数据是一个 JSON 字符串, 解析成 js 对象
    let resp = JSON.parse(e.data);
    let matchButton = document.querySelector('#match-button');
    if (!resp.ok) {
        console.log("游戏大厅中接收到了失败响应! " + resp.reason);
        return;
    }
    if (resp.message == 'startMatch') {
        // 开始匹配请求发送成功
        console.log("进入匹配队列成功!");
        matchButton.innerHTML = '匹配中...(点击停止)'
    } else if (resp.message == 'stopMatch') {
        // 结束匹配请求发送成功
        console.log("离开匹配队列成功!");
        matchButton.innerHTML = '开始匹配';
    } else if (resp.message == 'matchSuccess') {
        // 已经匹配到对手了.
        console.log("匹配到对手! 进入游戏房间!");
        // location.assign("/game_room.html");
        location.replace("/game_room.html");
    } else if (resp.message == 'repeatConnection') {
        alert("当前检测到多开! 请使用其他账号登录!");
        location.replace("/login.html");
    } else {
        console.log("收到了非法的响应! message=" + resp.message);
    }
}

// 给匹配按钮添加一个点击事件
let matchButton = document.querySelector('#match-button');
matchButton.onclick = function() {
    // 在触发 websocket 请求之前, 先确认下 websocket 连接是否好着呢~~
    if (websocket.readyState == websocket.OPEN) {
        // 如果当前 readyState 处在 OPEN 状态, 说明连接好着的~
        // 这里发送的数据有两种可能, 开始匹配/停止匹配~
        if (matchButton.innerHTML == '开始匹配') {
            console.log("开始匹配");
            websocket.send(JSON.stringify({
                message: 'startMatch',
            }));
        } else if (matchButton.innerHTML == '匹配中...(点击停止)') {
            console.log("停止匹配");
            websocket.send(JSON.stringify({
                message: 'stopMatch',
            }));
        }
    } else {
        // 这是说明连接当前是异常的状态
        alert("当前您的连接已经断开! 请重新登录!");
        location.replace('/login.html');
    }
}
