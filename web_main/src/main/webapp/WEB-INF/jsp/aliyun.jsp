<%--
  Created by IntelliJ IDEA.
  User: BMF
  Date: 2018/3/9
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aliyun</title>
</head>
<body>

<div>
    <input name="file" class="file" type="file"/>
    <button id="submit">提交</button>
</div>

</body>
<script type="text/javascript" src="/resources/libs/jQuery-2.2.4/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
    var result;
    function getKeys() {
        $.ajax({
            url: 'http://localhost:8080/sv1/fileUpload/getKey',
            type: 'POST',
            dataType: 'json',
            data: {
                dir: "aliyun",
                aspect: 0
            },
            success: function (data) {
                console.log(data);
                result = data.data;
            }
        });
    }

    $(function () {
        getKeys();
        setInterval(getKeys, 89500);

        $("#submit").click(function () {
            var file = $(".file").val();
            console.log(file);
            if (!file) {
                alert("文件为空");
                return;
            }
            var fileName = result.dir + "/" + new Date().getTime() + ".jpg";
            var formData = new FormData();
//                formData.append("Host", result.host);
            formData.append("key", fileName);
            formData.append("success_action_status", 200);
            for (var e in result) {
                formData.append(e, result[e]);
            }
//            formData.append("OSSAccessKeyId", result.OSSAccessKeyId);
//            formData.append("policy", result.policy);
//            formData.append("Signature", result.Signature);
//                formData.append("Host", result.host);
            formData.append("file", $(".file").get(0).files[0]);
//        var request = new XMLHttpRequest();
//        request.onreadystatechange = function () {
//            console.log(request.readyState + " " + request.status);
//            if (request.readyState == 4 && request.status == 200) {
//                //请求成功后操作
//                console.log(request.responseText);
//                console.log(request.getAllResponseHeaders());
//            }
//        }
//        request.open("POST", result.host);
//        request.send(formData);

            $.ajax({
                url: result.host,
                type: 'POST',
//                dataType: 'json',
                contentType: false,
                processData: false,
                cache: false,
                data: formData,
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    console.log(textStatus);
                    console.log(jqXHR);
                    if (textStatus == "success") {
                        console.log(result.host + "/" + fileName);
                    }
                },
                complete: function (jqXHR, textStatus) {
                    console.log(jqXHR);
                    console.log(textStatus);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });

        });
    })
</script>
</html>
