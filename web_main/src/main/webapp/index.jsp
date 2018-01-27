<%--
  Created by IntelliJ IDEA.
  User: BMF
  Date: 2017/5/14
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Map" %>
<html>
<head>
    <title>哈哈</title>
    <link href="/resources/libs/dist/min/dropzone.min.css" rel="stylesheet">
    <%--<meta http-equiv="refresh" content="5">--%>
</head>
<body>

<pre>
    <%
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = stackTrace.getKey();
            StackTraceElement[] stack = stackTrace.getValue();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            out.print("\n线程：" + thread.getName() + "\n");
            for (StackTraceElement element : stack) {
                out.print("\t" + element + "\n");
            }
        }
    %>
</pre>

<form action="/sv1/upload/picture" method="post"
      class="dropzone"
      id="myAwesomeDropzone">
    <div class="fallback">
        <%--<input id="multipartFile" name="multipartFile" type="file" multiple/>--%>
    </div>

</form>

<button id="add">点击</button>
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
        init: function () {
            $("#myAwesomeDropzone").append('<button id="uploadBtn" type="submit" class="btn btn-primary pull-right">确认上传</button>');
            $("#uploadBtn").on("click", function (e) {
                myAwesomeDropzone.processQueue();
            });
        },
        dictDefaultMessage: "拖动文件至此或点击此区域",
        dictCancelUpload: "取消",
        dictRemoveFile: "删除",
        dictFileTooBig: "文件尺寸为{{filesize}}，超过了最大限制：{{maxFilesize}}"
    };

    //    $("#add").click(function () {
    //        $.ajax({
    //            url:'/sv1/mvc/test',
    //            type:'post',
    //            contentType:"application/json",
    //            data:JSON.stringify({
    //                Id:1,
    //                name:"test"
    //            }),
    //            success:function (data) {
    //                console.log(data);
    //            }
    //        });
    //    });

        $("#add").click(function () {

            $.ajax({
                url: "https://test123w.oss-cn-qingdao.aliyuncs.com/multipart.data?partNumber=0&uploadId=1BD4E4328E1944609A90CC95754A4E16",
                type: 'PUT',
                dataType: 'json',
                //headers: {
                //    Authorization: "OSS LTAIELdnUQGZOXRB:WGeKzldmxJvz8V6jVaSsRon5ndg=",
                //    //"x-oss-server-side-encryption": "AES256",
                //    "x-oss-date": (new Date()).toGMTString()
                //},
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "OSS LTAIELdnUQGZOXRB:faNPqnTZCnJgUK4u1utCZ/jM/Cg=");
                    xhr.setRequestHeader("x-oss-date", "Sat, 27 Jan 2018 03:30:14 GMT");
                    xhr.setRequestHeader("Date", "Sat, 27 Jan 2018 03:30:14 GMT");
                    xhr.setRequestHeader("Content-Length", "6291456");
                    console.log(xhr)
                    console.log(xhr)
                },
                data: {
                    "data": new File(["First Line Text", "Second Line Text"], "part1")
                },
                success: function (data) {
                    console.log(data);
                }
            });
//            $.ajax({
////                url:'/sv1/mvc/pages/1/show/haha',
//                url:'/sv1/mvc/pages.xml',
//                type:'post',
//                data:{
//                    id:1,
//                    name:"haha"
//                },
//                success:function (data) {
//                    console.log(data);
//                }
//            });
        });

//    $("#add").click(function () {
//        $.ajax({
//            url: '/sv1/mvc/page/{"Id":1,"name":"test"}',
//            type: 'post',
////            data:JSON.stringify({
////                Id:1,
////                name:"test"
////            }),
//            success: function (data) {
//                console.log(data);
//            }
//        });
//    });

</script>
</body>
</html>
