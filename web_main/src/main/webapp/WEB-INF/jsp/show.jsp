<%--
  Created by IntelliJ IDEA.
  User: BMF
  Date: 2017/11/18
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${user.id}
${user.name}
</body>
<script type="text/javascript" src="/resources/libs/jQuery-2.2.4/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
    console.log(document.cookie)

    function getRequest() {
        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        } else {
            xmlhttp = new ActiveXObject();
        }
        //每当readyState 改变就会调用这个函数
        xmlhttp.onreadystatechange = function () {
            console.log(xmlhttp.readyState + " " + xmlhttp.status);
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                //请求成功后操作
                console.log(xmlhttp.responseText);
            }
        }
        xmlhttp.open("GET", "/sv1/mvc/mutli.json", true);
        xmlhttp.send();
    }

    //    getRequest();

    function postRequest() {
        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        } else {
            xmlhttp = new ActiveXObject();
        }
        //每当readyState 改变就会调用这个函数
        xmlhttp.onreadystatechange = function () {
            console.log(xmlhttp.readyState + " " + xmlhttp.status);
            if (xmlhttp.readyState == 3) {
//                xmlhttp.abort();
                console.log(xmlhttp.get)
                console.log(xmlhttp.responseText);
            }
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                //请求成功后操作
                console.log(xmlhttp.responseText);
                console.log(xmlhttp.getAllResponseHeaders());
            }
        }
        console.log(xmlhttp.readyState + " " + xmlhttp.status);
        xmlhttp.open("POST", "/sv1/mvc/mutli.json", true);
        console.log(xmlhttp.readyState + " " + xmlhttp.status);
        xmlhttp.setRequestHeader("a", "1");
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("fname=Bill&lname=Gates");
        console.log(xmlhttp.readyState + " " + xmlhttp.status);
    }

    /**
     * 跨域请求
     * 服务器daunt需要在响应中设置以下参数
     * Access-Control-Allow-Origin: http://localhost 请求得来源
     * Access-Control-Allow-Credentials：true 允许客户端发送cookie
     * Access-Control-Allow-Method:‘*’ 用来列出浏览器的CORS请求会用到哪些HTTP方法
     */
    // postRequest();
    $.ajax("http://localhost:8070/sv1/launch/launch", {
        type: 'post',
        dataType: 'json',
        headers: {
            // "Access-Control-Allow-Origin":"*"
        },
        timeout: 60000,//请求超时时间 毫秒
        xhrFields: { // 设置XHR的属性
            withCredentials:true//浏览器范松cookie到服务器
        },
        // crossDomain:true,
        statusCode: {
            404: function () {
                alert("page not found");
            },
            500: function () {
                alert("server error");
            }
        },
        global: true, //是否使用aja全局设置 ike ajaxStart or ajaxStop
        beforeSend: function (jqXHR, setting) { //发送ajax请求前
            console.log(jqXHR);
            console.log(setting);
        },
        data: {
            // videoId: 690
        },
        success: function (data, textStatus, jqXHR) {//请求成功后
            console.log(jqXHR);
            console.log(textStatus);
            console.log(data);
        },
        complete: function (jqXHR, textStatus) { //请求完成 after success and error callbacks are executed
            console.log(jqXHR);
            console.log(textStatus);
        },
        error: function (jqXHR, textStatus, errorThrown) { //请求出错
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
</script>
</html>
