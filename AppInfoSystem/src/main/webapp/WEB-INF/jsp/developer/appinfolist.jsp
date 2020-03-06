<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>
                    APP 信息管理系统维护 <i class="fa fa-user"></i>
                    <small>${devUserSession.devName}
                        - 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^
                    </small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form method="post" action="${ctx}/dev/list">
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input name="querySoftwareName" type="text" class="form-control col-md-7 col-xs-12"
                                           value="${querySoftwareName }">
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="queryStatus" class="form-control">
                                        <c:if test="${statusList != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="dataDictionary" items="${statusList}">
                                                <option
                                                        <c:if test="${dataDictionary.valueId == queryStatus }">selected="selected"</c:if>
                                                        value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="queryFlatformId" class="form-control">
                                        <c:if test="${flatFormList != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="dataDictionary" items="${flatFormList}">
                                                <option
                                                        <c:if test="${dataDictionary.valueId == queryFlatformId }">selected="selected"</c:if>
                                                        value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select id="queryCategoryLevel1" name="queryCategoryLevel1" class="form-control">
                                        <c:if test="${categoryLevel1List != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${categoryLevel1List}">
                                                <option
                                                        <c:if test="${appCategory.id == queryCategoryLevel1 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
                                    <select name="queryCategoryLevel2" id="queryCategoryLevel2" class="form-control">
                                        <c:if test="${categoryLevel2List != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${categoryLevel2List}">
                                                <option
                                                        <c:if test="${appCategory.id == queryCategoryLevel2 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="queryCategoryLevel3" id="queryCategoryLevel3" class="form-control">
                                        <c:if test="${categoryLevel3List != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${categoryLevel3List}">
                                                <option
                                                        <c:if test="${appCategory.id == queryCategoryLevel3 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <p class="text-muted font-13 m-b-30"></p>
                <div id="datatable-responsive_wrapper"
                     class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-12">
                            <a href="${pageContext.request.contextPath}/dev/flatform/app/appinfoadd"
                               class="btn btn-success btn-sm">新增APP基础信息</a>
                            <table id="datatable-responsive"
                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
                                   cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info"
                                   style="width: 100%;">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="First name: activate to sort column descending"
                                        aria-sort="ascending">软件名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        APK名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        软件大小(单位:M)
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属平台
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属分类(一级分类、二级分类、三级分类)
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        状态
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        下载次数
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        最新版本号
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 124px;"
                                        aria-label="Last name: activate to sort column ascending">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${appInfoList.size()>0 }">
                                    <c:forEach var="appInfo" items="${appInfoList }" varStatus="status">
                                        <tr role="row" class="odd">
                                            <td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
                                            <td>${appInfo.APKName }</td>
                                            <td>${appInfo.softwareSize }</td>
                                            <td>${appInfo.flatformName }</td>
                                            <td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name }
                                                -> ${appInfo.categoryLevel3Name }</td>
                                            <td><span id="appInfoStatus${appInfo.id}">${appInfo.statusName }</span></td>
                                            <td>${appInfo.downloads }</td>
                                            <td>${appInfo.versionNo }</td>
                                            <td>
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-danger">点击操作</button>
                                                    <button type="button" class="btn btn-danger dropdown-toggle"
                                                            data-toggle="dropdown" aria-expanded="false">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li>
                                                            <c:choose>
                                                                <c:when test="${appInfo.status == 2 || appInfo.status == 5}">
                                                                    <a class="saleSwichOpen" saleSwitch="open"
                                                                       appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                                       data-toggle="tooltip" data-placement="top"
                                                                       title=""
                                                                       data-original-title="恭喜您，您的审核已经通过，您可以点击上架发布您的APP">上架</a>
                                                                </c:when>
                                                                <c:when test="${appInfo.status == 4}">
                                                                    <a class="saleSwichClose" saleSwitch="close"
                                                                       appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                                       data-toggle="tooltip" data-placement="top"
                                                                       title=""
                                                                       data-original-title="您可以点击下架来停止发布您的APP，市场将不提供APP的下载">下架</a>
                                                                </c:when>
                                                            </c:choose>
                                                        </li>
                                                        <li><a class="addVersion" appinfoid="${appInfo.id }"
                                                               data-toggle="tooltip" data-placement="top" title=""
                                                               data-original-title="新增APP版本信息">新增版本</a>
                                                        </li>
                                                        <c:if test="${appInfo.status == 1 || appInfo.status == 3}">
                                                        <li><a class="modifyVersion"
                                                               appinfoid="${appInfo.id }"
                                                               versionid="${appInfo.versionId }"
                                                               status="${appInfo.status }"
                                                               statusname="${appInfo.statusName }"
                                                               data-toggle="tooltip" data-placement="top" title=""
                                                               data-original-title="修改APP最新版本信息">修改版本</a>
                                                        </li>
                                                        </c:if>
                                                        <c:if test="${appInfo.status == 1 || appInfo.status == 3}">
                                                        <li><a class="modifyAppInfo"
                                                               appinfoid="${appInfo.id }" status="${appInfo.status }"
                                                               statusname="${appInfo.statusName }"
                                                               data-toggle="tooltip" data-placement="top" title=""
                                                               data-original-title="修改APP基础信息">修改</a></li>
                                                        </c:if>
                                                        <li><a class="viewApp"
                                                               appinfoid=${appInfo.id }  data-toggle="tooltip"
                                                               data-placement="top" title=""
                                                               data-original-title="查看APP基础信息以及全部版本信息">查看</a></li>
                                                        <li><a class="deleteApp"
                                                               appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                               data-toggle="tooltip" data-placement="top" title=""
                                                               data-original-title="删除APP基础信息以及全部版本信息">删除</a></li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="dataTables_info" id="datatable-responsive_info"
                                 role="status" aria-live="polite">共${pages.totalCount }条记录
                                ${pages.currentPageNo }/${pages.totalPageCount }页
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="dataTables_paginate paging_simple_numbers"
                                 id="datatable-responsive_paginate">
                                <ul class="pagination">
                                    <c:if test="${pages.currentPageNo > 1}">
                                        <li class="paginate_button previous"><a
                                                href="javascript:page_nav(document.forms[0],1);"
                                                aria-controls="datatable-responsive" data-dt-idx="0"
                                                tabindex="0">首页</a>
                                        </li>
                                        <li class="paginate_button "><a
                                                href="javascript:page_nav(document.forms[0],${pages.currentPageNo-1})" ;
                                                aria-controls="datatable-responsive" data-dt-idx="1"
                                                tabindex="0">上一页</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pages.currentPageNo < pages.totalPageCount }">
                                        <li class="paginate_button "><a
                                                href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1 });"
                                                aria-controls="datatable-responsive" data-dt-idx="1"
                                                tabindex="0">下一页</a>
                                        </li>
                                        <li class="paginate_button next"><a
                                                href="javascript:page_nav(document.forms[0],${pages.totalPageCount });"
                                                aria-controls="datatable-responsive" data-dt-idx="7"
                                                tabindex="0">最后一页</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script>
    $(function () {
        $("#queryCategoryLevel1").change(function () {
            var queryCategoryLevel1 = $("#queryCategoryLevel1").val();
            if (queryCategoryLevel1 != '' && queryCategoryLevel1 != null) {
                $.ajax({
                    type: "GET",//请求类型
                    url: "${pageContext.request.contextPath }/dev/categorylevellist.json",//请求的url
                    data: {pid: queryCategoryLevel1},//请求参数
                    dataType: "json",//ajax接口（请求url）返回的数据类型
                    success: function (data) {//data：返回数据（json对象）
                        $("#queryCategoryLevel2").html("");
                        var options = "<option value=\"\">--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                        }
                        $("#queryCategoryLevel2").html(options);
                    },
                    error: function (data) {//当访问时候，404，500 等非200的错误状态码
                        alert("加载二级分类失败！");
                    }
                });
            } else {
                $("#queryCategoryLevel2").html("");
                var options = "<option value=\"\">--请选择--</option>";
                $("#queryCategoryLevel2").html(options);
            }
            $("#queryCategoryLevel3").html("");
            var options = "<option value=\"\">--请选择--</option>";
            $("#queryCategoryLevel3").html(options);
        });

        $("#queryCategoryLevel2").change(function () {
            var queryCategoryLevel2 = $("#queryCategoryLevel2").val();
            if (queryCategoryLevel2 != '' && queryCategoryLevel2 != null) {
                $.ajax({
                    type: "GET",//请求类型
                    url: "${pageContext.request.contextPath }/dev/categorylevellist.json",//请求的url
                    data: {pid: queryCategoryLevel2},//请求参数
                    dataType: "json",//ajax接口（请求url）返回的数据类型
                    success: function (data) {//data：返回数据（json对象）
                        $("#queryCategoryLevel3").html("");
                        var options = "<option value=\"\">--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            //alert(data[i].id);
                            //alert(data[i].categoryName);
                            options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                        }
                        $("#queryCategoryLevel3").html(options);
                    },
                    error: function (data) {//当访问时候，404，500 等非200的错误状态码
                        alert("加载三级分类失败！");
                    }
                });
            } else {
                $("#queryCategoryLevel3").html("");
                var options = "<option value=\"\">--请选择--</option>";
                $("#queryCategoryLevel3").html(options);
            }
        });
    })
    //提交跳转分页请求
    function page_nav(frm, num) {
        $("[name='pageIndex']").val(num);
        frm.submit();
    }
    //跳转到修改app信息页面
    $(".modifyAppInfo").on("click",function(){
        var obj = $(this);
        var status = obj.attr("status");
        if(status == "1" || status == "3"){
            window.location.href="${ctx}/dev/appinfomodify?id="+ obj.attr("appinfoid");
        }else{
            //alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改！");
        }
    });
    //跳转到添加app信息页面
    $(".addVersion").on("click",function () {
       window.location.href="${ctx}/dev/appversionadd?id="+$(this).attr("appinfoid");
    });
    //跳转到修改版本页面
    $(".modifyVersion").on("click",function(){
        var obj = $(this);
        var status = obj.attr("status");
        var versionid = obj.attr("versionid");
        var appinfoid = obj.attr("appinfoid");
        if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
            if(versionid == null || versionid == ""){
                alert("该APP应用无版本信息，请先增加版本信息！");
            }else{
                window.location.href="${ctx}/dev/appversionmodify?vid="+ versionid + "&aid="+ appinfoid;
            }
        }else{
           // alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改其版本信息，只可进行【新增版本】操作！");
        }
    });
    //查看app信息以及版本信息
    $(".viewApp").click(function () {
        var obj = $(this);
        var appinfoid = obj.attr("appinfoid");
        window.location.href="${ctx}/dev/appview/"+ appinfoid;
    });
    //删除app基本信息和版本信息
    $(".deleteApp").click(function () {
        var obj = $(this);
        if (confirm("确定要删除app应用"+obj.attr("appsoftwarename")+"及其所有的版本信息吗？")){
            $.ajax({
                type:"Get",
                url:"${ctx}/dev/delapp.json",
                data:{id:obj.attr("appinfoid")},
                dataType:"json",
                success:function (data) {
                    if (data.delResult=="true"){
                        alert("删除成功！");
                        location.href="${ctx}/dev/list";
                    }else if (data.delResult=="false") {
                        alert("删除app应用"+obj.attr("appsoftwarename")+"失败！");
                    }else if (data.delResult=="noexist") {
                        alert("app应用"+obj.attr("appsoftwarename")+"不存在！");
                    }
                },
                error:function (data) {
                    alert("删除失败！");
                }
            });
        }
    });
    $(document).on("click",".saleSwichOpen,.saleSwichClose",function(){
        var obj = $(this);
        var appinfoid = obj.attr("appinfoid");
        var saleSwitch = obj.attr("saleSwitch");
        if("open" === saleSwitch){
            saleSwitchAjax(appinfoid,obj);
        }else if("close" === saleSwitch){
            if(confirm("你确定要下架您的APP应用【"+obj.attr("appsoftwarename")+"】吗？")){
                saleSwitchAjax(appinfoid,obj);
            }
        }
    });
   function saleSwitchAjax(appId,obj){
       $.ajax({
           type:"PUT",
           url:"${ctx}/dev/sale/"+appId,
           dataType:"json",
           success:function(data){
               /*
                * resultMsg:success/failed
                * errorCode:exception000001
                * appId:appId
                * errorCode:param000001
                */
               if(data.errorCode === '0'){
                   if(data.resultMsg === "success"){//操作成功
                       if("open" === obj.attr("saleSwitch")){
                           //alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【上架】操作成功");
                           $("#appInfoStatus" + obj.attr("appinfoid")).html("已上架");
                           obj.className="saleSwichClose";
                           obj.html("下架");
                           obj.attr("saleSwitch","close");
                           $("#appInfoStatus" + obj.attr("appinfoid")).css({
                               'background':'green',
                               'color':'#fff',
                               'padding':'3px',
                               'border-radius':'3px'
                           });
                           $("#appInfoStatus" + obj.attr("appinfoid")).hide();
                           $("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
                       }else if("close" === obj.attr("saleSwitch")){
                           //alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【下架】操作成功");
                           $("#appInfoStatus" + obj.attr("appinfoid")).html("已下架");
                           obj.className="saleSwichOpem";
                           obj.html("上架");
                           obj.attr("saleSwitch","open");
                           $("#appInfoStatus" + obj.attr("appinfoid")).css({
                               'background':'red',
                               'color':'#fff',
                               'padding':'3px',
                               'border-radius':'3px'
                           });
                           $("#appInfoStatus" + obj.attr("appinfoid")).hide();
                           $("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
                       }
                   }else if(data.resultMsg === "failed"){//删除失败
                       if("open" === obj.attr("saleSwitch")){
                           alert("【"+obj.attr("appsoftwarename")+"】的【上架】操作失败");
                       }else if("close" === obj.attr("saleSwitch")){
                           alert("【"+obj.attr("appsoftwarename")+"】的【下架】操作失败");
                       }
                   }
               }else{
                   if(data.errorCode === 'exception000001'){
                       alert("对不起，系统出现异常，请联系IT管理员");
                   }else if(data.errorCode === 'param000001'){
                       alert("对不起，参数出现错误，您可能在进行非法操作");
                   }
               }
           },
           error:function(data){
               if("open" === obj.attr("saleSwitch")){
                   alert("【"+obj.attr("appsoftwarename")+"】的【上架】操作成功");
               }else if("close" === obj.attr("saleSwitch")){
                   alert("【"+obj.attr("appsoftwarename")+"】的【下架】操作成功");
               }
           }
       });
   };
</script>