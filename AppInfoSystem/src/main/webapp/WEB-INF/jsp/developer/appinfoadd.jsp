<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>新增APP基础信息 <i class="fa fa-user"></i>
                    <small>${devUserSession.devName}</small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                 <<%--div class="item form-group">
                         <label class="control-label col-md-3 col-sm-3 col-xs-12" ></label>
                         <div class="col-md-6 col-sm-6 col-xs-12">
                           <form action="uploadlogo" class="dropzone" style="height:100px;">
                           </form>
                      <div class="clearfix"></div>
                   </div>
                 </div> --%>
                <div class="clearfix"></div>
                <form class="form-horizontal form-label-left" action="${ctx}/dev/appinfoaddsave" method="post"
                      enctype="multipart/form-data">
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="softwareName" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="softwareName"
                                   required="required"
                                   placeholder="请输入软件名称" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="APKName" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="APKName"
                                   required="required"
                                   placeholder="请输入APK名称" type="text">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="supportROM" class="form-control col-md-7 col-xs-12" name="supportROM"
                                   data-validate-length-range="20" data-validate-words="1" required="required"
                                   placeholder="请输入支持的ROM" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="interfaceLanguage"
                                   required="required"
                                   placeholder="请输入软件支持的界面语言" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="number" id="softwareSize" name="softwareSize" required="required"
                                   onkeyup="value=value.replace(/[^\d]/g,'')"
                                   data-validate-minmax="10,500" placeholder="请输入软件大小，单位为Mb"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="number" id="downloads" name="downloads" required="required"
                                   data-validate-minmax="10,500" placeholder="请输入下载次数"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">所属平台 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="flatformId" id="flatformId" class="form-control" required="required">
                                <c:if test="${flatFormList != null }">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="dataDictionary" items="${flatFormList}">
                                        <option value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">一级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel1" id="categoryLevel1" class="form-control"
                                    required="required"> </select>
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">二级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel2" id="categoryLevel2" class="form-control"
                                    required="required"></select>
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">三级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel3" id="categoryLevel3" class="form-control"
                                    required="required"></select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="hidden" name="status" id="status" value="1">待审核
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo" required="required"
                        placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。"
                        class="form-control col-md-7 col-xs-12"></textarea>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="file" class="form-control col-md-7 col-xs-12" name="a_logoPicPath"
                                   required="required" id="a_logoPicPath"/>
                            ${fileUploadError }
                        </div>
                    </div>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <button id="send" type="submit" class="btn btn-success">保存</button>
                            <button type="button" class="btn btn-primary" id="back">返回</button>
                            <br/><br/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script>
    $(function () {
        //动态加载所属平台列表
        $.ajax({
            type: "GET",//请求类型
            url: "${ctx}/dev/datadictionarylist.json",//请求的url
            data: {tcode: "APP_FLATFORM"},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                $("#flatformId").html("");
                var options = "<option value=\"\">--请选择--</option>";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value=\"" + data[i].valueId + "\">" + data[i].valueName + "</option>";
                }
                $("#flatformId").html(options);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载平台列表失败！");
            }
        });
        //动态加载一级分类列表
        $.ajax({
            type: "GET",//请求类型
            url: "${ctx}/dev/categorylevellist.json",//请求的url
            data: {pid: null},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                $("#categoryLevel1").html("");
                var options = "<option value=\"\">--请选择--</option>";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                }
                $("#categoryLevel1").html(options);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载一级分类列表失败！");
            }
        });
        //动态加载二级分类列表
        $("#categoryLevel1").change(function () {
            var categoryLevel1 = $("#categoryLevel1").val();
            if (categoryLevel1 != '' && categoryLevel1 != null) {
                $.ajax({
                    type: "GET",//请求类型
                    url: "${ctx}/dev/categorylevellist.json",//请求的url
                    data: {pid: categoryLevel1},//请求参数
                    dataType: "json",//ajax接口（请求url）返回的数据类型
                    success: function (data) {//data：返回数据（json对象）
                        $("#categoryLevel2").html("");
                        var options = "<option value=\"\">--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                        }
                        $("#categoryLevel2").html(options);
                    },
                    error: function (data) {//当访问时候，404，500 等非200的错误状态码
                        alert("加载二级分类失败！");
                    }
                });
            } else {
                $("#categoryLevel2").html("");
                var options = "<option value=\"\">--请选择--</option>";
                $("#categoryLevel2").html(options);
            }
            $("#categoryLevel3").html("");
            var options = "<option value=\"\">--请选择--</option>";
            $("#categoryLevel3").html(options);
        });
        //动态加载三级分类列表
        $("#categoryLevel2").change(function () {
            var categoryLevel2 = $("#categoryLevel2").val();
            if (categoryLevel2 != '' && categoryLevel2 != null) {
                $.ajax({
                    type: "GET",//请求类型
                    url: "${ctx}/dev/categorylevellist.json",//请求的url
                    data: {pid: categoryLevel2},//请求参数
                    dataType: "json",//ajax接口（请求url）返回的数据类型
                    success: function (data) {//data：返回数据（json对象）
                        $("#categoryLevel3").html("");
                        var options = "<option value=\"\">--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                        }
                        $("#categoryLevel3").html(options);
                    },
                    error: function (data) {//当访问时候，404，500 等非200的错误状态码
                        alert("加载三级分类失败！");
                    }
                });
            } else {
                $("#categoryLevel3").html("");
                var options = "<option value=\"\">--请选择--</option>";
                $("#categoryLevel3").html(options);
            }
        });

        $("#back").on("click", function () {
            window.location.href = "${ctx}/dev/list";
        });

        $("#APKName").bind("blur", function () {
            //ajax后台验证--APKName是否已存在
            $.ajax({
                type: "GET",//请求类型
                url: "${ctx}/dev/apkexist.json",//请求的url
                data: {APKName: $("#APKName").val()},//请求参数
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {//data：返回数据（json对象）
                    if (data.APKName == "empty") {//参数APKName为空，错误提示
                        alert("APKName为不能为空！");
                    } else if (data.APKName == "exist") {//账号不可用，错误提示
                        alert("该APKName已存在，不能使用！");
                    } else if (data.APKName == "noexist") {//账号可用，正确提示
                        //alert("该APKName可以使用！");
                    }
                },
                error: function (data) {//当访问时候，404，500 等非200的错误状态码
                    alert("请求错误！");
                }
            });
        });

    });
</script>