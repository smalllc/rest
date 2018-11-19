<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <script type="text/javascript" src="js/jquery/jquery.min.js" ></script>
    <script type="text/javascript">
        $(function(){
            uploadFile();
        });
        function uploadFile(){
            $(".file").change(function(){
                var formData2 = $(this).parent(); //得到表单
                $.ajax({
                    url: '/rest/upload/image?fn=upload&r=' + Math.random(),
                    type: 'POST',
                    Accept:'text/html;charset=UTF-8',
                    cache: false,
                    contentType:"multipart/form-data",
                    data: new FormData(formData2[0]),
                    processData: false,
                    contentType: false,
                    xhr:function(){
                        myXhr = $.ajaxSettings.xhr();
                        if(myXhr.upload){ // check if upload property exists
                            myXhr.upload.addEventListener('progress',function(e){
                                var loaded = e.loaded;//已经上传大小情况
                                var tot = e.total;//附件总大小
                                var per = Math.floor(100*loaded/tot);  //已经上传的百分比
                                $("#son").html( per +"%" );
                                $("#son").css("width" , per +"%");
                                console.log('附件总大小 = ' + loaded);
                                console.log('已经上传大小 = ' + tot);
                            }, false); // for handling the progress of the upload
                        }
                        return myXhr;
                    },
                    success:function(data){
                        console.log(data);
                        alert("上传"+data);

                    },
                    error:function(){
                        alert("上传失败！");

                    }
                });
            });

        }
    </script>
    <style>

        #son{
            width:0px;
            height:30px;
            background:blue;
        }

    </style>

    <title>上传图片</title>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data">
    AppId:<input id="appid" type="text" name="appid" />
    logo:<input id="logo" type="file" name="file" class="logo"/>
    img:<input id="file" tabindex="4" size="4" type="file" name="testFile"  class="file" multiple="multiple"/>

</form>
<div id="son"></div>
</body>
</html>