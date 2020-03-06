package com.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.project.entity.*;
import com.project.service.*;
import com.project.tools.Constants;
import com.project.tools.Pages;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dev")
public class DevUserController {
    @Resource
    private DevUserService devUserService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppVersionService appVersionService;

    /**
     * @return
     */
    @RequestMapping("/list")
    public String getAppInfoList(Model model, HttpSession session, Pages pages,
                                 @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
                                 @RequestParam(value = "queryStatus", required = false) String _queryStatus,
                                 @RequestParam(value = "queryFlatformId", required = false) String _queryFlatformId,
                                 @RequestParam(value = "queryCategoryLevel1", required = false) String _queryCategoryLevel1,
                                 @RequestParam(value = "queryCategoryLevel2", required = false) String _queryCategoryLevel2,
                                 @RequestParam(value = "queryCategoryLevel3", required = false) String _queryCategoryLevel3,
                                 @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        try {
            Integer devId = ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId();
            List<AppInfo> appInfoList = null;
            List<DataDictionary> statusList = null;
            List<DataDictionary> flatFormList = null;
            List<AppCategory> categoryLevel1List = null;
            List<AppCategory> categoryLevel2List = null;
            List<AppCategory> categoryLevel3List = null;

            Integer queryStatus = null;
            if (_queryStatus != null && !_queryStatus.equals("")) {
                queryStatus = Integer.parseInt(_queryStatus);
            }
            Integer queryFlatformId = null;
            if (_queryFlatformId != null && !_queryFlatformId.equals("")) {
                queryFlatformId = Integer.parseInt(_queryFlatformId);
            }
            statusList = dataDictionaryService.getDataDictionaryList("APP_STATUS");
            flatFormList = dataDictionaryService.getDataDictionaryList("APP_FLATFORM");

            model.addAttribute("statusList", statusList);
            model.addAttribute("flatFormList", flatFormList);
            model.addAttribute("queryStatus", queryStatus);
            model.addAttribute("queryFlatformId", queryFlatformId);
            Integer queryCategoryLevel1 = null;
            if (_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")) {
                queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
            }
            Integer queryCategoryLevel2 = null;
            if (_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")) {
                queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
            }
            Integer queryCategoryLevel3 = null;
            if (_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")) {
                queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
            }
            categoryLevel1List = getCategoryList(null);
            //当前页码
            Integer currentPageNo = 1;
            if (pageIndex != null) {
                currentPageNo = Integer.valueOf(pageIndex);
            }
            pages.setPageSize(Constants.pageSize);
            Integer totalCount = appInfoService.getAppInfoCount(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId);
            pages.setTotalCount(totalCount);
            pages.setCurrentPageNo(currentPageNo);
            int totalPageCount = pages.getTotalPageCount();
            //控制首页和尾页
            if (currentPageNo < 1) {
                currentPageNo = 1;
            } else if (currentPageNo > totalPageCount) {
                currentPageNo = totalPageCount;
            }
            appInfoList = appInfoService.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId, currentPageNo, pages.getPageSize());
            model.addAttribute("categoryLevel1List", categoryLevel1List);
            //二级分类列表和三级分类列表---回显
            if (queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")) {
                categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
                model.addAttribute("categoryLevel2List", categoryLevel2List);
            }
            if (queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")) {
                categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
                model.addAttribute("categoryLevel3List", categoryLevel3List);
            }
            model.addAttribute("appInfoList", appInfoList);
            model.addAttribute("pages", pages);
            model.addAttribute("querySoftwareName", querySoftwareName);
            model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
            model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
            model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/developer/appinfolist";
    }

    public List<AppCategory> getCategoryList(String pid) {
        List<AppCategory> categoryLevelList = null;
        try {

            categoryLevelList = appCategoryService.getAppCategoryListByParentId(pid == null ? null : Integer.parseInt(pid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryLevelList;
    }

    /**
     * 根据parentId查询出相应的分类级别列表
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object getAppCategoryList(String pid) {
        if (pid.equals("")) pid = null;
        return JSON.toJSONString(getCategoryList(pid));
    }

    /**
     * 查询所属平台
     *
     * @return
     */
    @RequestMapping(value = "/datadictionarylist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object datadictionarylist() {
        return JSON.toJSONString(getDataDictionary());
    }

    private List<DataDictionary> getDataDictionary() {
        List<DataDictionary> flatFormList = null;
        try {
            flatFormList = dataDictionaryService.getDataDictionaryList("APP_FLATFORM");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flatFormList;
    }

    /**
     * 登录到主页
     *
     * @param devCode
     * @param devPassword
     * @param session
     * @return
     */
    @RequestMapping("/main")
    public String main(String devCode, String devPassword, HttpSession session) {
        try {
            DevUser devUser = devUserService.login(devCode, devPassword);
            if (devUser != null) {
                session.setAttribute(Constants.DEV_USER_SESSION, devUser);
                session.removeAttribute(Constants.SYS_MESSAGE);
                return "/developer/main";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute(Constants.SYS_MESSAGE, "用户名或密码错误！");
        return "redirect:/page/devlogin";
    }

    /**
     * 注销登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/loginout")
    public String todevlogin(HttpSession session) {
        session.removeAttribute(Constants.DEV_USER_SESSION);
        session.invalidate();
        return "redirect:/page/devlogin";
    }

    /**
     * 跳转到主页
     *
     * @return
     */
    @RequestMapping("/flatform/main")
    public String toMain() {
        return "developer/main";
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @RequestMapping("/flatform/app/appinfoadd")
    public String toAppinfoAdd() {
        return "developer/appinfoadd";
    }

    /**
     * 保存添加的appInfo信息
     *
     * @param appInfo
     * @param session
     * @return
     */
    @RequestMapping(value = "/appinfoaddsave", method = RequestMethod.POST)
    public String addSave(AppInfo appInfo, HttpSession session, HttpServletRequest request,
                          @RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {

        String logoPicPath = null;
        String logoLocPath = null;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + java.io.File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 500000;
            if (attach.getSize() > filesize) {//上传大小不得超过 50k
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                return "/developer/appinfoadd";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")) {//上传图片格式
                String fileName = appInfo.getAPKName() + ".jpg";//上传LOGO图片命名:apk名称.apk
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
                    return "/developer/appinfoadd";
                }
                logoPicPath = request.getContextPath() + "/statics/uploadfiles/" + fileName;
                logoLocPath = path + File.separator + fileName;
            } else {
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
                return "/developer/appinfoadd";
            }
        }
        appInfo.setCreatedBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appInfo.setCreationDate(new Date());
        appInfo.setLogoPicPath(logoPicPath);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setDevId(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appInfo.setStatus(1);
        try {
            if (appInfoService.add(appInfo)) {
                return "redirect:/dev/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/developer/appinfoadd";
    }

    /**
     * ajax验证APKName是否唯一
     *
     * @param APKName
     * @return
     */
    @RequestMapping(value = "/apkexist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object apkNameIsExit(@RequestParam String APKName) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(APKName)) {
            resultMap.put("APKName", "empty");
        } else {
            AppInfo appInfo = null;
            try {
                appInfo = appInfoService.getAppInfo(null, APKName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != appInfo)
                resultMap.put("APKName", "exist");
            else
                resultMap.put("APKName", "noexist");
        }
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * 跳转到修改appInfomodify页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/appinfomodify", method = RequestMethod.GET)
    public String modifyAppInfo(@RequestParam("id") String id,
                                @RequestParam(value = "error", required = false) String fileUploadError,
                                Model model) {
        AppInfo appInfo = null;
        if (null != fileUploadError && fileUploadError.equals("error1")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_1;
        } else if (null != fileUploadError && fileUploadError.equals("error2")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_2;
        } else if (null != fileUploadError && fileUploadError.equals("error3")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_3;
        } else if (null != fileUploadError && fileUploadError.equals("error4")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_4;
        }
        try {
            appInfo = appInfoService.getAppInfo(Integer.parseInt(id), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(appInfo);
        model.addAttribute("fileUploadError", fileUploadError);
        return "developer/appinfomodify";
    }

    /**
     * 删除图片或版本
     *
     * @param flag
     * @param id
     * @return
     */
    @RequestMapping(value = "/delfile", method = RequestMethod.GET)
    @ResponseBody
    public Object delFile(@RequestParam(value = "flag", required = false) String flag,
                          @RequestParam(value = "id", required = false) String id) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        String fileLocPath = null;
        if (flag == null || flag.equals("") ||
                id == null || id.equals("")) {
            resultMap.put("result", "failed");
        } else if (flag.equals("logo")) {//删除logo图片（操作app_info）
            try {
                fileLocPath = (appInfoService.getAppInfo(Integer.parseInt(id), null)).getLogoLocPath();
                File file = new File(fileLocPath);
                if (file.exists())
                    if (file.delete()) {//删除服务器存储的物理文件
                        if (appInfoService.deleteAppLogo(Integer.parseInt(id))) {//更新表
                            resultMap.put("result", "success");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag.equals("apk")) {//删除apk文件（操作app_version）
            try {
                fileLocPath = (appVersionService.getAppVersionById(Integer.parseInt(id))).getApkLocPath();
                File file = new File(fileLocPath);
                if (file.exists())
                    if (file.delete()) {//删除服务器存储的物理文件
                        if (appVersionService.deleteApkFile(Integer.parseInt(id))) {//更新表
                            resultMap.put("result", "success");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * 保存修改后的appInfo
     *
     * @param appInfo
     * @param session
     * @return
     */
    @RequestMapping(value = "/appinfomodifysave", method = RequestMethod.POST)
    public String modifySave(AppInfo appInfo, HttpSession session, HttpServletRequest request,
                             @RequestParam(value = "attach", required = false) MultipartFile attach) {
        String logoPicPath = null;
        String logoLocPath = null;
        String APKName = appInfo.getAPKName();
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 500000;
            if (attach.getSize() > filesize) {//上传大小不得超过 50k
                return "redirect:/dev/appinfomodify?id=" + appInfo.getId()
                        + "&error=error4";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")) {//上传图片格式
                String fileName = APKName + ".jpg";//上传LOGO图片命名:apk名称.apk
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "redirect:/dev/appinfomodify?id=" + appInfo.getId()
                            + "&error=error2";
                }
                logoPicPath = request.getContextPath() + "/statics/uploadfiles/" + fileName;
                logoLocPath = path + File.separator + fileName;
            } else {
                return "redirect:/dev/appinfomodify?id=" + appInfo.getId()
                        + "&error=error3";
            }
        }
        appInfo.setModifyBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appInfo.setModifyDate(new Date());
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setLogoPicPath(logoPicPath);
        try {
            if (appInfoService.modify(appInfo)) {
                return "redirect:/dev/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "developer/appinfomodify";
    }

    /**
     * 增加appversion信息（跳转到新增app版本页面）
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "/appversionadd", method = RequestMethod.GET)
    public String addVersion(@RequestParam(value = "id") String appId,
                             @RequestParam(value = "error", required = false) String fileUploadError,
                             AppVersion appVersion, Model model) {
        if (null != fileUploadError && fileUploadError.equals("error1")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_1;
        } else if (null != fileUploadError && fileUploadError.equals("error2")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_2;
        } else if (null != fileUploadError && fileUploadError.equals("error3")) {
            fileUploadError = Constants.FILEUPLOAD_ERROR_3;
        }
        appVersion.setAppId(Integer.parseInt(appId));
        List<AppVersion> appVersionList = null;
        try {
            appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
            appVersion.setAppName((appInfoService.getAppInfo(Integer.parseInt(appId), null)).getSoftwareName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute(appVersion);
        model.addAttribute("fileUploadError", fileUploadError);
        return "developer/appversionadd";
    }

    /**
     * 保存新增appversion数据（子表）-上传该版本的apk包
     *
     * @param appVersion
     * @param session
     * @param request
     * @param attach
     * @return
     */
    @RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
    public String addVersionSave(AppVersion appVersion, HttpSession session, HttpServletRequest request,
                                 @RequestParam(value = "a_downloadLink", required = false) MultipartFile attach) {
        String downloadLink = null;
        String apkLocPath = null;
        String apkFileName = null;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            if (prefix.equalsIgnoreCase("apk")) {//apk文件命名：apk名称+版本号+.apk
                String apkName = null;
                try {
                    apkName = appInfoService.getAppInfo(appVersion.getAppId(), null).getAPKName();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (apkName == null || "".equals(apkName)) {
                    return "redirect:/dev/appversionadd?id=" + appVersion.getAppId()
                            + "&error=error1";
                }
                apkFileName = apkName + "-" + appVersion.getVersionNo() + ".apk";
                File targetFile = new File(path, apkFileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "redirect:/dev/appversionadd?id=" + appVersion.getAppId()
                            + "&error=error2";
                }
                downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
                apkLocPath = path + File.separator + apkFileName;
            } else {
                return "redirect:/dev/appversionadd?id=" + appVersion.getAppId()
                        + "&error=error3";
            }
        }
        appVersion.setCreatedBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appVersion.setCreationDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);
        try {
            if (appVersionService.appsysadd(appVersion)) {
                return "redirect:/dev/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dev/appversionadd?id=" + appVersion.getAppId();
    }
    /**
     * 修改最新的appVersion信息（跳转到修改appVersion页面）
     * @param versionId
     * @param appId
     * @param model
     * @return
     */
    @RequestMapping(value="/appversionmodify",method=RequestMethod.GET)
    public String modifyAppVersion(@RequestParam("vid") String versionId,
                                   @RequestParam("aid") String appId,
                                   @RequestParam(value="error",required= false)String fileUploadError,
                                   Model model){
        AppVersion appVersion = null;
        List<AppVersion> appVersionList = null;
        if(null != fileUploadError && fileUploadError.equals("error1")){
            fileUploadError = Constants.FILEUPLOAD_ERROR_1;
        }else if(null != fileUploadError && fileUploadError.equals("error2")){
            fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
        }else if(null != fileUploadError && fileUploadError.equals("error3")){
            fileUploadError = Constants.FILEUPLOAD_ERROR_3;
        }
        try {
            appVersion = appVersionService.getAppVersionById(Integer.parseInt(versionId));
            appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
        }catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(appVersion);
        model.addAttribute("appVersionList",appVersionList);
        model.addAttribute("fileUploadError",fileUploadError);
        return "developer/appversionmodify";
    }
    /**
     * 保存修改后的appVersion
     * @param appVersion
     * @param session
     * @return
     */
    @RequestMapping(value="/appversionmodifysave",method=RequestMethod.POST)
    public String modifyAppVersionSave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
                                       @RequestParam(value="attach",required= false) MultipartFile attach){

        String downloadLink =  null;
        String apkLocPath = null;
        String apkFileName = null;
        if(!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
                String apkName = null;
                try {
                    apkName = appInfoService.getAppInfo(appVersion.getAppId(),null).getAPKName();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if(apkName == null || "".equals(apkName)){
                    return "redirect:/dev/appversionmodify?vid="+appVersion.getId()
                            +"&aid="+appVersion.getAppId()
                            +"&error=error1";
                }
                apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
                File targetFile = new File(path,apkFileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "redirect:/dev/appversionmodify?vid="+appVersion.getId()
                            +"&aid="+appVersion.getAppId()
                            +"&error=error2";
                }
                downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
                apkLocPath = path+File.separator+apkFileName;
            }else{
                return "redirect:/dev/appversionmodify?vid="+appVersion.getId()
                        +"&aid="+appVersion.getAppId()
                        +"&error=error3";
            }
        }
        appVersion.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appVersion.setModifyDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);
        try {
            if(appVersionService.modify(appVersion)){
                return "redirect:/dev/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "developer/appversionmodify";
    }
    /**
     * 查看app信息，包括app基本信息和版本信息列表（跳转到查看页面）
     * @param
     * @return
     */
    @RequestMapping(value="/appview/{id}",method=RequestMethod.GET)
    public String view(@PathVariable String id, Model model){
        AppInfo appInfo = null;
        List<AppVersion> appVersionList = null;
        try {
            appInfo = appInfoService.getAppInfo(Integer.parseInt(id),null);
            appVersionList = appVersionService.getAppVersionList(Integer.parseInt(id));
        }catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute(appInfo);
        return "developer/appinfoview";
    }

    /**
     * 删除app信息及版本信息
     * @param id
     * @return
     */
    @RequestMapping("/delapp.json")
    @ResponseBody
    public Object delApp(@RequestParam String id){
        Map<String,Object>resultMap = new HashMap<String, Object>();
        if (StringUtils.isNullOrEmpty(id)){
            resultMap.put("delResult","notexist");
        }else {
            try {
                if (appInfoService.appsysdeleteAppById(Integer.parseInt(id))){
                    resultMap.put("delResult","true");
                }else {
                    resultMap.put("delResult","false");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSONArray.toJSONString(resultMap);
    }
    //上架下架App
    @RequestMapping(value="/sale/{appid}",method=RequestMethod.PUT)
    @ResponseBody
    public Object sale(@PathVariable String appid,HttpSession session){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Integer appIdInteger = 0;
        try{
            appIdInteger = Integer.parseInt(appid);
        }catch(Exception e){
            appIdInteger = 0;
        }
        resultMap.put("errorCode", "0");
        resultMap.put("appId", appid);
        if(appIdInteger>0){
            try {
                DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
                AppInfo appInfo = new AppInfo();
                appInfo.setId(appIdInteger);
                appInfo.setModifyBy(devUser.getId());
                if(appInfoService.appsysUpdateSaleStatusByAppId(appInfo)){
                    resultMap.put("resultMsg", "success");
                }else{
                    resultMap.put("resultMsg", "success");
                }
            } catch (Exception e) {
                resultMap.put("errorCode", "exception000001");
            }
        }else{
            //errorCode:0为正常
            resultMap.put("errorCode", "param000001");
        }

        /*
         * resultMsg:success/failed
         * errorCode:exception000001
         * appId:appId
         * errorCode:param000001
         */
        return resultMap;
    }

}
