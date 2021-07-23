var flag = $("#select").val();
layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on("select", function (data) {
        if (flag == '1') {
            $('.layui-select-title>input').css("color", "black")
            $("#mail-block").css("display", "none");
            $("#before-mail").css("display", "block");
        }
        if (data.value == '1') {
            $('.layui-select-title>input').css("color", "black")
            $("#mail-block").css("display", "block");
            $("#before-mail").css("display", "none");
        }
        flag = data.value;

    });

});
$("#before-mail").click(function () {
    $("#text-1").text("1")
    $("#text-1").css("color", "white")
    $("#text-2").css("color", "#bec9d2")
    $("#label-1").css({
        "font-weight": "bolder",
        "color": "black"
    })
    $("#label-2").css({
        "color": "#bec9d2",
        "font-weight": "300"
    })
    $("#icon-1").css({
        "background-color": "#5ca9e7",
        "border-color": "#5ca9e7"
    })
    $("#icon-2").css({
        "background-color": "white",
        "border-color": "#bec9d2"
    })
    $("#link-1").css("border", "1px solid #bec9d2")
    $("#first").css("display", "block");
    $("#second").css("display", "none");
});
$("#next1").click(
    function () {
        $.ajax(
            {
                data: {id: $("#id").val()},
                url: "judgeId",
                success: function (data) {
                    if (data == "true") {
                        $("#error-msg").text("");
                        $("#icon-1").css("background-color", "white")
                        $("#icon-2").css({
                            "background-color": "#5ca9e7",
                            "border-color": "#5ca9e7"
                        })
                        $("#text-1").text("✔")
                        $("#text-1").css("color", "#5ca9e7")
                        $("#text-2").css("color", "white")
                        $("#label-1").css("font-weight", "300")
                        $("#label-2").css({
                            "font-weight": "bolder",
                            "color": "black"
                        })
                        $("#link-1").css("border", "1px solid #5ca9e7")
                        $("#first").css("display", "none");
                        $("#second").css("display", "block");
                    } else {
                        $("#error-msg").text("账号不存在");
                    }
                }

            }
        )
    }
)
$("#before-1").click
(
    function () {
        $.ajax(
            {
                url: "falseCode"
            }
        )
        $("#text-1").text("1")
        $("#text-1").css("color", "white")
        $("#text-2").css("color", "#bec9d2")
        $("#label-1").css({
            "font-weight": "bolder",
            "color": "black"
        })
        $("#label-2").css({
            "color": "#bec9d2",
            "font-weight": "300"
        })
        $("#icon-1").css({
            "background-color": "#5ca9e7",
            "border-color": "#5ca9e7"
        })
        $("#icon-2").css({
            "background-color": "white",
            "border-color": "#bec9d2"
        })
        $("#before-mail").css("display", "block");
        $("#select").val("")
        $('.layui-select-title>input').val('请选择');
        $('.layui-select-title>input').css("color", "grey")
        $("#mail-block").css("display", "none")
        $("#error-msg-2").text("")
        $("#sendCode").removeAttr("disabled");
        $("#link-1").css("border", "1px solid #bec9d2")
        $("#first").css("display", "block");
        $("#second").css("display", "none");
    }
)
var waitTime = 60;

function time(ele) {
    if (waitTime == 0) {
        ele.disabled = false;
        ele.value = "发送动态码";
        waitTime = 60;// 恢复计时
        return;
    } else {
        ele.disabled = true;
        ele.value = "重新发送(" + waitTime + ")";
        waitTime--;
        setTimeout(function () {
            time(ele)// 关键处-定时循环调用
        }, 1000)
    }
}

$("#sendCode").click(function () {
        $("#sendCode").attr("disabled", "true");
        $.ajax(
            {
                url: "sendCode",
                success: function (data) {
                    if (data == "true") {
                        time(document.getElementById("sendCode"));
                    } else {
                        $("#error-msg-2").text("用户未绑定邮箱");
                    }
                }
            }
        )
    }
)
$("#next-2").click(function () {
    if ($("#error-msg-2").text() != "用户未绑定邮箱") {
        $.ajax(
            {
                data: {code: $("#code").val()},
                url: "judgeMailCode",
                success: function (data) {
                    if (data == "true") {
                        $("#error-msg-2").text("");
                        $("#icon-2").css("background-color", "white")
                        $("#icon-3").css({
                            "background-color": "#5ca9e7",
                            "border-color": "#5ca9e7"
                        })
                        $("#text-2").text("✔")
                        $("#text-2").css("color", "#5ca9e7")
                        $("#text-3").css("color", "white")
                        $("#label-2").css("font-weight", "300")
                        $("#label-3").css({
                            "font-weight": "bolder",
                            "color": "black"
                        })
                        $("#link-2").css("border", "1px solid #5ca9e7")
                        $("#second").css("display", "none");
                        $("#third").css("display", "block");
                    } else {
                        $("#error-msg-2").text("验证码有误");
                    }
                }
            }
        )
    }
})
$("#before-2").click
(
    function () {
        $("#text-2").text("2")
        $("#text-2").css("color", "white")
        $("#text-3").css("color", "#bec9d2")
        $("#label-2").css({
            "font-weight": "bolder",
            "color": "black"
        })
        $("#label-3").css({
            "color": "#bec9d2",
            "font-weight": "300"
        })
        $("#icon-2").css({
            "background-color": "#5ca9e7",
            "border-color": "#5ca9e7"
        })
        $("#icon-3").css({
            "background-color": "white",
            "border-color": "#bec9d2"
        })
        $("#link-2").css("border", "1px solid #bec9d2")
        $("#second").css("display", "block");
        $("#third").css("display", "none");
    }
)

function validateTip(element, css, tipString, status) {
    element.css(css);
    element.html(tipString);
    element.prev().attr("validateStatus", status);
}

$("#password").on("focus", function () {
    validateTip($("#password").next(), {"color": "#666666"}, "    密码长度必须是大于6小于20", false);
}).on("blur", function () {
    if ($("#password").val() != null && $("#password").val().length > 6
        && $("#password").val().length < 20) {
        validateTip($("#password").next(), {"color": "green"}, "", true);
    } else {
        validateTip($("#password").next(), {"color": "red"}, " 密码输入不符合规范，请重新输入", false);
    }
});

$("#password-2").on("focus", function () {
    validateTip($("#password-2").next(), {"color": "#666666"}, " 请输入与上面一致的密码", false);
}).on("blur", function () {
    if ($("#password-2").val() != null && $("#password-2").val().length > 6
        && $("#password-2").val().length < 20 && $("#password-2").val() == $("#password-2").val()) {
        validateTip($("#password-2").next(), {"color": "green"}, "", true);
    } else {
        validateTip($("#password-2").next(), {"color": "red"}, " 两次密码输入不一致，请重新输入", false);
    }
});
$("#next-3").click(function () {
        if ($("#password").attr("validateStatus") == "true" && $("#password-2").attr("validateStatus") == "true") {
            $.ajax(
                {
                    data: {password: $("#password").val()},
                    url: "changePwd",
                })
            $("#link-3").css("border", "1px solid #5ca9e7")
            $("#icon-3").css("background-color", "white")
            $("#text-3").text("✔")
            $("#text-3").css("color", "#5ca9e7");
            $("#label-3").css("font-weight", "300")
            $("#third").css("display", "none");
            $("#success").css("display", "block")
        }
    }
)

