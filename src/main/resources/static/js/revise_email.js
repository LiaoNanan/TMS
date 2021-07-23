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
($("#next1")).click(
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
        $("#select").val("")
        $("#before-mail").css("display", "block");
        $("#mail-block").css("display", "none")
        $('.layui-select-title>input').val('请选择');
        $('.layui-select-title>input').css("color", "grey")
        $("#error-msg-2").text("")
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
        time(document.getElementById("sendCode"));
        $.ajax(
            {
                url: "sendCode",
                data: {email: $("#email").val()}
            }
        )
    }
)

$("#next-2").click(function () {
    $.ajax(
        {
            data: {id: $("#id").val(), pwd: $("#pwd").val()},
            url: "judgePwd",
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
                    $("#error-msg-2").text("密码有误");
                }
            }
        }
    )
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
var flag_2 = 0;//是否有尝试过
$("#email").on("input propertychange", function () {
    var rule = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
    var email = $("#email").val()
    if (email.match(rule) != null) {
        $.ajax(
            {
                url: "falseCode"
            }
        )
        $("#sendCode").removeAttr('disabled');
        if (flag_2 >= 1) {
            flag_2 = 0;
            $("#error-msg-3").text("");
        }
    } else {
        $("#sendCode").attr("disabled", "true");
    }
}).on("blur", function () {
        var rule = /^\d{5,12}@([q]{2}|[Q]{2})\.(com|cn)$/
        var email = $("#email").val()
        if (email.match(rule) == null) {
            flag_2++;
            $("#sendCode").attr("disabled", "true");
            if (flag == 1) {
                $("#error-msg-3").text("邮箱有误");
            }

        }
    }
)

$("#next-3").click(function () {
    $.ajax(
        {
            data: {code: $("#code").val()},
            url: "judgeMailCode",
            success: function (data) {
                if (data == "true") {
                    $.ajax(
                        {
                            data: {email: $("#email").val()},
                            url: "changeEmail",
                        })
                    $("#link-3").css("border", "1px solid #5ca9e7")
                    $("#icon-3").css("background-color", "white")
                    $("#text-3").text("✔")
                    $("#text-3").css("color", "#5ca9e7");
                    $("#label-3").css("font-weight", "300")
                    $("#third").css("display", "none");
                    $("#success").css("display", "block")
                } else {
                    $("#error-msg-4").text("验证码有误");
                }
            }

        })
})

