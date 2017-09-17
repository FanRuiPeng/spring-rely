<%--
  Created by IntelliJ IDEA.
  User: BMF
  Date: 2017/5/14
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>哈哈</title>
    <link href="/resources/libs/dist/min/dropzone.min.css" rel="stylesheet">
</head>
<body>
<form action="/sv1/upload/picture" method="post"
      class="dropzone"
      id="myAwesomeDropzone">
    <div class="fallback">
        <%--<input id="multipartFile" name="multipartFile" type="file" multiple/>--%>
    </div>

</form>
<script src="/resources/libs/dist/min/dropzone.min.js"></script>
<script src="/resources/libs/jQuery-2.2.4/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
    Dropzone.options.myAwesomeDropzone = false;
    Dropzone.options.myAwesomeDropzone = {
        paramName: "multipartFile", // The name that will be used to transfer the file
        maxFilesize: 20, // MB
        uploadMultiple: true,
        autoProcessQueue: false,
        addRemoveLinks: true,
        maxFiles: 1,
        parallelUploads: 1,
        accept: function (file, done) {
            done();
        },
        init:function () {
            $("#myAwesomeDropzone").append('<button id="uploadBtn" type="submit" class="btn btn-primary pull-right">确认上传</button>');
            $("#uploadBtn").on("click",function (e) {
                myAwesomeDropzone.processQueue();
            });
        },
        dictDefaultMessage: "拖动文件至此或点击此区域",
        dictCancelUpload: "取消",
        dictRemoveFile: "删除",
        dictFileTooBig: "文件尺寸为{{filesize}}，超过了最大限制：{{maxFilesize}}"
    };
</script>
</body>
</html>
