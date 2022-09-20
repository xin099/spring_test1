let gameInfo = {
    roomId: null,
    thisUserId: null,
    thatUserId: null,
    isWhite: true,
}

//
// 设定界面显示相关操作
//

function setScreenText(me) {
    let screen = document.querySelector('#screen');
    if (me) {
        screen.innerHTML = "轮到你落子了!";
    } else {
        screen.innerHTML = "轮到对方落子了!";
    }
}

//
// 初始化 websocket
//
// TODO

//
// 初始化一局游戏
//
function initGame() {
    // 是我下还是对方下. 根据服务器分配的先后手情况决定
    let me = gameInfo.isWhite;
    // 游戏是否结束
    let over = false;
    let chessBoard = [];
    //初始化chessBord数组(表示棋盘的数组)
    for (let i = 0; i < 15; i++) {
        chessBoard[i] = [];
        for (let j = 0; j < 15; j++) {
            chessBoard[i][j] = 0;
        }
    }
    let chess = document.querySelector('#chess');
    let context = chess.getContext('2d');
    context.strokeStyle = "#BFBFBF";
    // 背景图片
    let logo = new Image();
    // logo.src = "image/sky.jpeg";
    logo.onload = function () {
        context.drawImage(logo, 0, 0, 450, 450);
        initChessBoard();
    }

    // 绘制棋盘网格
    function initChessBoard() {
        for (let i = 0; i < 15; i++) {
            context.moveTo(15 + i * 30, 15);
            context.lineTo(15 + i * 30, 430);
            context.stroke();
            context.moveTo(15, 15 + i * 30);
            context.lineTo(435, 15 + i * 30);
            context.stroke();
        }
    }

    // 绘制一个棋子, me 为 true
    function oneStep(i, j, isWhite) {
        context.beginPath();
        context.arc(15 + i * 30, 15 + j * 30, 13, 0, 2 * Math.PI);
        context.closePath();
        var gradient = context.createRadialGradient(15 + i * 30 + 2, 15 + j * 30 - 2, 13, 15 + i * 30 + 2, 15 + j * 30 - 2, 0);
        if (!isWhite) {
            gradient.addColorStop(0, "#0A0A0A");
            gradient.addColorStop(1, "#636766");
        } else {
            gradient.addColorStop(0, "#D1D1D1");
            gradient.addColorStop(1, "#F9F9F9");
        }
        context.fillStyle = gradient;
        context.fill();
    }

    chess.onclick = function (e) {
        if (over) {
            return;
        }
        if (!me) {
            return;
        }
        let x = e.offsetX;
        let y = e.offsetY;
        // 注意, 横坐标是列, 纵坐标是行
        let col = Math.floor(x / 30);
        let row = Math.floor(y / 30);
        if (chessBoard[row][col] == 0) {
            // TODO 发送坐标给服务器, 服务器要返回结果

            send(row,col);
            // oneStep(col, row, gameInfo.isWhite);
            // chessBoard[row][col] = 1;
        }
    }

    // TODO 实现发送落子请求逻辑, 和处理落子响应逻辑.
}

initGame();



let websocketUrl = 'ws://'+ location.host +'/game';
let websocket = new WebSocket(websocketUrl);

websocket.onopen = function() {
    console.log("房间链接成功!");
}
websocket.onclose = function() {
    console.log("房间断开链接");
}
websocket.onerror = function() {
    console.log("房间出现异常");
}
window.onbeforeunload = function() {
    websocket.close();
}
websocket.onmessage = function(e) {
    console.log(e.data);
    let resp = JSON.parse(e.data);

    if(resp.message != 'gameReady') {
        console.log("响应类型错误");
        location.assign("index.html");
        return;
    }
    if(resp.status == -1) {
        alert("游戏链接失败!");
        location.assign("index.html");
        return;
    }

    gameInfo.roomId == resp.roomId;
    gameInfo.thisUserId = resp.thisUserId;
    gameInfo.thatUserId = resp.thatUserId;
    gameInfo.isWhite = resp.whiteUser == resp.thisUserId;

    // 初始化棋盘
    initGame();

    // 设置显示内容
    setScreenText(gameInfo.isWhite);
}


function send(row,col) {
    let req = {
        message: 'putChess',
        userId: gameInfo.thisUserId,
        row: row,
        col: col
    };

    websocket.send(JSON.stringify(req));
}

