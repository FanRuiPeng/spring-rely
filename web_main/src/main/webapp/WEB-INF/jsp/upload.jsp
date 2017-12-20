<%--
  Created by IntelliJ IDEA.
  User: BMF
  Date: 2017/11/26
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<div>
    <input name="file" class="file" type="file"/>
    <input name="file" class="file" type="file"/>
    <button id="submit">提交</button>
</div>
<script src="/resources/libs/jQuery-2.2.4/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
    $("#submit").click(function () {
        var file = $(".file");
        if (null == file || file == "" || typeof (file) == "undefined") {
            alert("文件为空");
        } else {
            var formData = new FormData();
            var files = $(".file");
            console.log(files);
            files.each(function (t) {
                console.log($(this).files);
                formData.append("multipartFile", $(this).get(0).files[0]);
            });
            var request = new XMLHttpRequest();
//            request.open("POST", "/sv1/upload/picture");
//            request.send(formData);
            $.ajax({
                url: "/sv1/upload/picture",
                type: "post",
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                dataType: "json",
                success: function (data) {
                    console.log(data);
                }
            });
        }
    });
</script>
</body>
</html>

