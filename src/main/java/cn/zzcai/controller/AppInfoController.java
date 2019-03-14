package cn.zzcai.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.zzcai.pojo.*;
import cn.zzcai.service.AppCategoryService;
import cn.zzcai.service.AppVersionService;
import cn.zzcai.service.DataDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.zzcai.service.AppInfoService;
import cn.zzcai.tools.PageBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月6日 下午1:25:50
 * @remark TODO
 */
@Controller
@RequestMapping("/dev/flatform/app")
public class AppInfoController {

    @Resource
    private AppInfoService appInfoService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppVersionService appVersionService;

    //条件查询AppInfo信息
    @RequestMapping("/list")
    public String findAppInfoList(
            @RequestParam(value = "querySoftwareName", required = false)
                    String softwareName,
            @RequestParam(value = "queryStatus", required = false)
                    Integer status,
            @RequestParam(value = "queryFlatformId", required = false)
                    Integer flatformId,
            @RequestParam(value = "queryCategoryLevel1", required = false)
                    Integer categoryLevel1,
            @RequestParam(value = "queryCategoryLevel2", required = false)
                    Integer categoryLevel2,
            @RequestParam(value = "queryCategoryLevel3", required = false)
                    Integer categoryLevel3,
            @RequestParam(value = "pageIndex", required = false)
                    Integer pageNo,
            HttpSession session,
            Model model) {
        List<AppCategory> categoryLevel1List=null;
        List<AppCategory> categoryLevel2List=null;
        List<AppCategory> categoryLevel3List=null;
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");
        Integer devId = devUser.getId();
        if (pageNo == null) {
            pageNo = 1;
        }
        int pageSize = 5;
        PageBean<AppInfo> pageBean = appInfoService.findByPage(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, devId, pageNo, pageSize);
        model.addAttribute("pageBean", pageBean);

        List<DataDictionary> statusList=dataDictionaryService.findDataDictionaryList("APP_STATUS");
        model.addAttribute("statusList", statusList);
        List<DataDictionary> flatFormList=dataDictionaryService.findDataDictionaryList("APP_FLATFORM");
        model.addAttribute("flatFormList", flatFormList);
        Integer parentId=null;
        categoryLevel1List=appCategoryService.findAppCategoryList(parentId);
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        if(categoryLevel2!=null){
            parentId=categoryLevel2;
            categoryLevel2List=appCategoryService.findAppCategoryList(parentId);
            if (categoryLevel3!=null){
                parentId=categoryLevel3;
                categoryLevel3List=appCategoryService.findAppCategoryList(parentId);
            }
        }
        model.addAttribute("categoryLevel2List", categoryLevel2List);
        model.addAttribute("categoryLevel3List", categoryLevel3List);
        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("queryStatus",status);
        model.addAttribute("queryFlatformId",flatformId);
        model.addAttribute("queryCategoryLevel1",categoryLevel1);
        model.addAttribute("queryCategoryLevel2",categoryLevel2);
        model.addAttribute("queryCategoryLevel3",categoryLevel3);
        return "developer/appinfolist";
    }

    //动态查询一级、二级、或三级分类列表
    @RequestMapping(value = "categorylevellist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object showCategorylevellist(@RequestParam("pid")Integer parentId){
        List<AppCategory> categoryLevelList=appCategoryService.findAppCategoryList(parentId);
        return categoryLevelList;
    }

    //显示新增AppInfo页面
    @RequestMapping("/appinfoadd")
    public String showAddAppInfo(){
        return "developer/appinfoadd";
    }

    //保存新增的AppInfo
    @RequestMapping("/appinfoaddsave")
    public String addAppInfo(AppInfo appInfo, HttpSession session,
                             HttpServletRequest request, Model model,
                             @RequestParam(value = "a_logoPicPath",required = false)
                                     MultipartFile file){
        String logoPicPath=null;
        String logoLocPath=null;
        if(!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            String pathName="/statics/uploadfiles/"+fileName;
            try {
                file.transferTo(new File(session.getServletContext().getRealPath(pathName)));
                logoPicPath=request.getContextPath()+pathName;
                logoLocPath=session.getServletContext().getRealPath(pathName);
                System.out.println(logoPicPath);
                System.out.println(logoLocPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                model.addAttribute("fileUploadError","文件上传失败");
                return "developer/appinfoadd";
            }
        }
        DevUser devUser= (DevUser) session.getAttribute("devUserSession");
        Integer devId=devUser.getId();
        appInfo.setDevId(devId);
        appInfo.setUpdateDate(new Date());
        appInfo.setCreatedBy(devId);
        appInfo.setCreationDate(new Date());
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setLogoPicPath(logoPicPath);
        int ret=appInfoService.addAppInfo(appInfo);
        if (ret>0){
            return "redirect:/dev/flatform/app/list";
        }else {
            return "developer/appinfoadd";
        }

    }

    //根据typeCode查询DataDictionary
    @RequestMapping(value = "datadictionarylist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object showDataDictionaryList(@RequestParam("tcode")String typeCode){
        List<DataDictionary> dataDictionaryList=dataDictionaryService.findDataDictionaryList(typeCode);
        return dataDictionaryList;
    }

    //检查APKName是否已存在
    @RequestMapping(value = "apkexist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object checkAppName(@RequestParam("APKName")String APKName){
        Map<String,String> map=new HashMap<>();
        if (!APKName.equals("")&&APKName!=null){
            AppInfo appInfo=appInfoService.checkAPKName(APKName);
            if(appInfo!=null){
                map.put("APKName","exist");
            }else {
                map.put("APKName","noexist");
            }
        }else {
            map.put("APKName","empty");
        }
        return map;
    }

    //显示新增版本页面
    @RequestMapping("/appversionadd")
    public String showAppVersionAdd(@RequestParam("id")Integer appId,Model model){
        List<AppVersion> appVersionList=appVersionService.findAppVersionList(appId);
        model.addAttribute("appVersionList",appVersionList);
        AppInfo appInfo=appInfoService.findAppinfo(appId);
        Integer id=appInfo.getVersionId();
        AppVersion appVersion=appVersionService.findAppVersionById(id);
        model.addAttribute("appVersion",appVersion);
        return "developer/appversionadd";
    }

    //保存新增版本
    @RequestMapping("/addversionsave")
    public String addVersion(AppVersion appVersion,
                             @RequestParam(value = "a_downloadLink",required = false) MultipartFile file,
                             HttpServletRequest request,HttpSession session,Model model){
        String downloadLink=null;
        String apkLocPath=null;
        String apkFileName=null;
        if(!file.isEmpty()){
            apkFileName=file.getOriginalFilename();
            String path="/statics/uploadfiles/"+apkFileName;
            try {
                file.transferTo(new File(session.getServletContext().getRealPath(apkFileName)));
                downloadLink=request.getContextPath()+path;
                apkLocPath=session.getServletContext().getRealPath(path);
            } catch (IOException e) {
                model.addAttribute("fileUploadError","文件上传失败");
                return "developer/appversionadd";
            }
        }
        DevUser devUser= (DevUser) session.getAttribute("devUserSession");
        Integer createBy=devUser.getId();
        appVersion.setCreatedBy(createBy);
        appVersion.setCreationDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);
        int ret=appVersionService.addAppVersion(appVersion);
        if(ret>0){
            return "developer/appinfolist";
        }else {
            return "developer/appversionadd";
        }
    }

    //显示修改版本页面
    @RequestMapping(value = "/appversionmodify",method = RequestMethod.GET)
    public String showAppVersionModify(@RequestParam(value = "vid",required = false)Integer id,
                                       @RequestParam(value = "aid",required = false)Integer appId,Model model){
        List<AppVersion> appVersionList=appVersionService.findAppVersionList(appId);
        model.addAttribute("appVersionList",appVersionList);
        AppVersion appVersion=appVersionService.findAppVersionById(id);
        model.addAttribute("appVersion",appVersion);
        return "developer/appversionmodify";

    }

    //显示修改AppInfo页面
    @RequestMapping(value = "/appinfomodify",method = RequestMethod.GET)
    public String showAppInfoModify(@RequestParam(value = "id",required = false)Integer id,Model model){
        AppInfo appInfo=appInfoService.findAppinfo(id);
        model.addAttribute("appInfo",appInfo);

        return "developer/appinfomodify";

    }

    //显示AppInfo详情页面
    @RequestMapping("/appview/{id}")
    public String showAppview(@PathVariable("id")Integer id,Model model){
        AppInfo appInfo=appInfoService.findAppinfo(id);
        model.addAttribute("appInfo",appInfo);
        List<AppVersion> appVersionList=appVersionService.findAppVersionList(id);
        model.addAttribute("appVersionList",appVersionList);
        return "developer/appinfoview";
    }
}
