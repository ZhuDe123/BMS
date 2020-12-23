var isEmailcode = false;
function checkUsername() {
    var username = $('#username').val();
    $.ajax({
        //请求路径
        url: '../findUser?fun=checkusername',
        //要发给服务器端的值
        data: {"fun": "checkusername", "username": username},
        //请求方式:默认为get
        type: 'GET',
        //回调函数，服务器端响应回来执行的函数
        success: function (data) {//函数的参数就是服务器响应的数据
            if (data == 'yes') {
                $('#usernamespan').css('color', 'green');
                $('#usernamespan').text('✔用户名可用');
                return true;
            } else {
                $('#usernamespan').css('color', 'red');
                $('#usernamespan').text('✖用户名重复');
                return false;
            }
        }
    });
}

function identifyEmailCode() {
    var checkCode = $('#checkCode').val();
    $.ajax({
        //请求路径
        url: '../identifyEmailCode',
        //要发给服务器端的值
        data: {"identifyEmailCode": checkCode, "emailCode" : getCookie('emailCode') },
        //请求方式:默认为get
        type: 'GET',
        //回调函数，服务器端响应回来执行的函数
        success: function (data) {//函数的参数就是服务器响应的数据
            if (data == 'yes') {
                $('#emailButtonSpan').css('display','inline');
                $('#emailButtonSpan').css('color', 'green');
                $('#emailButtonSpan').text('✔验证码正确');
                isEmailcode = true;

            } else if(data == 'no') {
                $('#emailButtonSpan').css('display','inline');
                $('#emailButtonSpan').css('color', 'red');
                $('#emailButtonSpan').text('✖验证码错误');
                isEmailcode = false;

            }else {
                $('#emailButtonSpan').css('display','inline');
                $('#emailButtonSpan').css('color', 'red');
                $('#emailButtonSpan').text('✖请重新发送验证码');
                isEmailcode = false;
            }
        }
    });
}
function getCookie(objname){//获取指定名称的cookie的值
    var arrstr = document.cookie.split("; ");
    for(var i = 0;i < arrstr.length;i ++){
        var temp = arrstr[i].split("=");
        if(temp[0] == objname) return unescape(temp[1]);
    }
}
function checkEmailFormat() {
    var email = $('#email').val();
    //是否填值
    if (email == null || email=="") {
        $('#EmailSpan').css('display','inline');
        $('#EmailSpan').css('color', 'red');
        $('#EmailSpan').text('✖请填写邮箱');
        $('#emailButtonSpan').css('display','none');
        return false;
    }
    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if(!myReg.test(email)){
        $('#EmailSpan').css('display','inline');
        $('#EmailSpan').css('color', 'red');
        $('#EmailSpan').text('✖请填写正确的邮箱');
        $('#emailButtonSpan').css('display','none');
        return false;
    }
    return true;
}
function sendEmail() {
    var email = $('#email').val();

    if(!checkEmailFormat()){
        return;
    }
    $.ajax({
        //请求路径
        url: "../SendCodeTOEmail",
        //要发给服务器端的值
        data: {"email": email},
        //请求方式:默认为get
        type: 'GET',
        //回调函数，服务器端响应回来执行的函数
        success: function (data) {//函数的参数就是服务器响应的数据

            if (data == "yes") {
                $('#emailButtonSpan').css('display','inline');
                $('#emailButtonSpan').css('color', 'green');
                $('#emailButtonSpan').text('✔验证码已发送');
                $('#EmailSpan').css('display','none');
            } else{
                $('#emailButtonSpan').css('display','inline');
                $('#emailButtonSpan').css('color', 'red');
                $('#emailButtonSpan').text('✖验证码发送失败');
                $('#EmailSpan').css('display','none');
            }
        }
    });
}
function checkRePassword() {
    var password = getvalue("password");
    var password2 = getvalue("password2");
    if (password !== password2) {
        $('#checkRePassword').css('display','inline');
        $('#checkRePassword').css('color', 'red');
        $('#checkRePassword').text('✖两次输入密码不一致');
        return false;
    } else {
        if (password.length < 6 || password.length > 20) {
            $('#checkRePassword').css('display','inline');
            $('#checkRePassword').css('color', 'red');
            $('#checkRePassword').text('✖密码不符合要求，请重新输入');
            return false;
        }else {
            $('#checkRePassword').css('display','inline');
            $('#checkRePassword').css('color', 'green');
            $('#checkRePassword').text('✔密码符合要求');
            return true;
        }
    }

}
function getvalue(id) {
    return document.getElementById(id).value;
}
function register() {
    // if (checkUsername() && checkRePassword() && isEmailcode) {
    // }else {
    //     alert("填写的信息有问题，请重新填写信息");
    //     window.location.href="../user/register.jsp";
    // }
    alert("点击确定，返回登录界面");
}