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
            }
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                //请求成功后操作
                console.log(xmlhttp.responseText);
                console.log(xmlhttp.getAllResponseHeaders());
            }
        }
        xmlhttp.open("POST", "/sv1/mvc/mutli.json", true);
        xmlhttp.setRequestHeader("a", "1");
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("fname=Bill&lname=Gates");
    }

//    postRequest();
</script>
</html>
