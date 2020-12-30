function checkname(){
    $.ajax({
        type:"POST",
        cache:false,
        url : "/checkName",
        dataType : "text",
        data:"uName="+$("#user-name").val(),
        beforeSend:function(XMLHttpRequest){$("#showResultUser").text("正在查询");},
        success:function(msg){$("#showResultUser").html(msg);},
        complete:function(XMLHttpRequest,textStatus){},
        error:function(){}
    });
}

function checkTel(){
    $.ajax({
        type:"POST",
        cache:false,
        url : "/checkTel",
        dataType : "text",
        data:"uPhone="+$("#user-phone").val(),
        beforeSend:function(XMLHttpRequest){$("#showResultTel").text("正在查询");},
        success:function(msg){$("#showResultTel").html(msg);},
        complete:function(XMLHttpRequest,textStatus){},
        error:function(){}
    });
}

function checkEmail(){
    $.ajax({
        type:"POST",
        cache:false,
        url : "/checkEmail",
        dataType : "text",
        data:"uEmail="+$("#user-email").val(),
        beforeSend:function(XMLHttpRequest){$("#showResultEmail").text("正在查询");},
        success:function(msg){$("#showResultEmail").html(msg);},
        complete:function(XMLHttpRequest,textStatus){},
        error:function(){}
    });
}

function CheckPass() {
    /*<![CDATA[*/
    var pwd1 = document.getElementById("pass").value;
    var pwd2 = document.getElementById("rpass").value;
    if(pwd1 != pwd2){
        $("#checkpass").html("<font color='red'>两次输入的密码不一致</font>");
    }
    /*]]>*/
}