package com.nacos.alibaba.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nacos.alibaba.utils.*;
import com.nacos.alibaba.vo.WechatMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/wechatMenu")
public class WechatMenuController {

    private static Log logger = LogFactory.getLog(WechatMenuController.class);

    @Value("${services.url}")
    private String services_url;
    @Autowired
    private RestTemplate resttemplate;
    private static String access_token = "";


    @RequestMapping(value="/menuAdd")
    public String menuAdd(){
        return "/wechatmenu/addMenu";
    }

    @RequestMapping(value="/menuEdit/{id}")
    public String menuEdit(@PathVariable("id") int id){
        return "/wechatmenu/editMenu";
    }

    @RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response) {
        // 菜单状态
        String status = request.getParameter("status");
        // 当前页数
        int pages = Integer.parseInt(request.getParameter("page"));
        // 每页的总数
        int row = Integer.parseInt(request.getParameter("rows"));
        // 总页数
        int total = 0;
        // 分页查询limit后的第一个参数
        int currentTotal = 0;
        // 请求的页数大于第一页时向后台传递分页查询limit后的第一个参数
        if (pages > 1) {
            currentTotal = ((pages - 1) * row);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", currentTotal);
        map.put("rows", row);
        map.put("status", status);
        String surl = services_url + "/dsbservice/wechatMenu/getWechatMenuList";
        ServiceMap resultmap = resttemplate.postForObject(surl, map, ServiceMap.class);
        List<WechatMenu> list = (List<WechatMenu>) resultmap.getBody().get("list");
        // 返回的查询总数
        int ct = (int) resultmap.getBody().get("cnt");
        // 返回总页数判断
        if (ct % row == 0) {
            total = ct / row;
        } else {
            total = ct / row + 1;
        }
        // 返回页面jgrid表格分页所需参数
        Map<String, Object> backmap = new HashMap<String, Object>();
        backmap.put("rows", list);
        backmap.put("page", pages);
        backmap.put("records", ct);
        backmap.put("total", total);
        JsonViewUtils.render(backmap, response);
    }

    /**
     * 新增菜单
     *
     * @param wechatMenu
     * @param response
     */
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public void articleAdd(@RequestBody WechatMenu wechatMenu, HttpServletResponse response) {
        try {
            String surl = services_url + "/dsbservice/wechatMenu/addMenu";
            ServiceMap resultmap = resttemplate.postForObject(surl, wechatMenu, ServiceMap.class);
            JsonViewUtils.render(resultmap.getBody().get("msg"), response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @RequestMapping(value = "/delMenu/{id}", method = RequestMethod.POST)
    public void delMenu(@PathVariable(name = "id") Integer id, HttpServletResponse response) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            if(id == null) {
                logger.error("未获取到菜单id");
                JsonViewUtils.render("删除失败", response);
                return;
            }

            map.put("id", id);
            String surl = services_url + "/dsbservice/wechatMenu/delMenu";
            ServiceMap resultmap = resttemplate.postForObject(surl, map, ServiceMap.class);
            JsonViewUtils.render(resultmap.getBody().get("msg"), response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    /**
     * 修改菜单时获取菜单原有内容填充Input
     * @param id
     * @param response
     */
    @RequestMapping(value="/getMenuById")
    public void getMenuById(@RequestParam("id") Integer id, HttpServletResponse response){
        if(id == null) {
            logger.error("未获取到菜单id");
            JsonViewUtils.render("获取菜单失败", response);
            return;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        String surl = services_url + "/dsbservice/wechatMenu/getMenuById";
        ServiceMap resultmap = resttemplate.postForObject(surl, map, ServiceMap.class);
        WechatMenu menu = JSON.parseObject(JSON.toJSONString(resultmap.getBody().get("menu")), WechatMenu.class);
        JsonViewUtils.render(menu, response);
    }

    /**
     * 修改菜单
     *
     * @param wechatMenu
     */
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public void updateMenu(@RequestBody WechatMenu wechatMenu, HttpServletResponse response) {
        try {
            String surl = services_url + "/dsbservice/wechatMenu/updateMenu";
            ServiceMap resultmap = resttemplate.postForObject(surl, wechatMenu, ServiceMap.class);
            JsonViewUtils.render(resultmap.getBody().get("msg"), response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    /**
     * 获取父级菜单下拉列表
     *
     * @param response
     */
    @RequestMapping(value = "/getParentMenu")
    public void getParentMenu(HttpServletResponse response) {
        try {
            String surl = services_url + "/dsbservice/wechatMenu/getParentMenu";
            ServiceMap resultmap = resttemplate.postForObject(surl,null, ServiceMap.class);
            List<WechatMenu> list = (List<WechatMenu>) resultmap.getBody().get("list");
            JsonViewUtils.render(list, response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    /**
     * 生成自定义菜单
     *
     * @param response
     */
    @RequestMapping(value = "/generateMenu", method = RequestMethod.POST)
    public void generateMenu(HttpServletResponse response) {
        try {
            List<WechatMenu> list = getWechatMenu();
            Map<String, Object> wechatMenuMap = convertToJson(list);
            JsonViewUtils.render(wechatMenuMap, response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    /**
     * 发送自定义菜单
     *
     * @param response
     */
    @RequestMapping(value = "/sendMenu", method = RequestMethod.POST)
    public void sendMenu(HttpServletResponse response) {
        try {
            List<WechatMenu> list = getWechatMenu();
            Map<String, Object> wechatMenuMap = convertToJson(list);
            String json = JSONObject.toJSONString(wechatMenuMap);
            AjaxJson result = WechatUtils.createCustomMenu(json);
            logger.info("自定义菜单已发送公众号");
            JsonViewUtils.render(result, response);
        } catch (Exception e) {
            JsonViewUtils.renderException(e, response);
        }
    }

    public List<WechatMenu> getWechatMenu() {
        String surl = services_url + "/dsbservice/wechatMenu/getWechatMenu";
        ServiceMap resultmap = resttemplate.postForObject(surl, null, ServiceMap.class);
        List<WechatMenu> list = JSON.parseArray(JSON.toJSONString(resultmap.getBody().get("list")), WechatMenu.class);
        return list;
    }

    public  Map<String, Object>  convertToJson(List<WechatMenu> list) {
        // 获取一级菜单
        List<WechatMenu> listA = list.
                stream().
                filter(menu -> menu.getParentId() == 0)
                .collect(Collectors.toList());

        // 最外一层大括号
        Map<String, Object> wechatMenuMap = new HashMap<String, Object>();

        //包装button的List
        List<Map<String, Object>> wechatMenuMapList = new ArrayList<Map<String, Object>>();

        //  像菜单中添加子菜单
        for(WechatMenu parent:listA){
            // 获得二级菜单
            List<WechatMenu> listASon =  list.
                    stream().
                    filter(m -> m.getParentId() == parent.getId())
                    .collect(Collectors.toList());

            // 如果没有下一级菜单
            if(listASon.size() == 0){
                Map<String, Object> menuMap = getBtnMenu(parent);
                wechatMenuMapList.add(menuMap);
                continue;
            }
            // 存放一级菜单
            Map<String, Object> menuMap1 = new HashMap<String, Object>();
            // 存放三级菜单列表
            List<Map<String, Object>> subMenuMapList1 = new ArrayList<Map<String, Object>>();
            for(WechatMenu son:listASon){
                List<WechatMenu> listAGrandson =  list.
                        stream().
                        filter(m -> m.getParentId() == son.getId())
                        .collect(Collectors.toList());

                // 如果没有下一级菜单
                if(listAGrandson.size() == 0){
                    Map<String, Object> menuMap = getBtnMenu(son);
                    subMenuMapList1.add(menuMap);
                    continue;
                }
                Map<String, Object> menuMap2 = new HashMap<String, Object>();
                List<Map<String, Object>> subMenuMapList2 = menuList(listAGrandson);
                menuMap2.put(WechatParams.MENU_BUTTON_NAME,son.getName());
                menuMap2.put(WechatParams.MENU_SUB_BUTTON, subMenuMapList2);
                subMenuMapList1.add(menuMap2);

            }
            menuMap1.put(WechatParams.MENU_BUTTON_NAME,parent.getName());
            menuMap1.put(WechatParams.MENU_SUB_BUTTON, subMenuMapList1);
            wechatMenuMapList.add(menuMap1);
        }
        wechatMenuMap.put(WechatParams.MENU_BUTTON,wechatMenuMapList);

        return  wechatMenuMap;
    }

    public static List<Map<String, Object>> menuList(List<WechatMenu> list){
        List<Map<String, Object>> menuMapList = new ArrayList<Map<String, Object>>();
        for(WechatMenu menu:list){
            Map<String, Object> menuMap = getBtnMenu(menu);
            menuMapList.add(menuMap);
        }
        return  menuMapList;
    }

    public static Map<String, Object> getBtnMenu(WechatMenu menu) {
        Map<String, Object> menuMap = new HashMap<String, Object>();
        if(WechatParams.MENU_TYPE_VIEW.equals(menu.getType())){ // 跳转页面url
            menuMap.put(WechatParams.MENU_BUTTON_TYPE, WechatParams.MENU_TYPE_VIEW);
            menuMap.put(WechatParams.MENU_BUTTON_NAME, menu.getName());
            menuMap.put(WechatParams.MENU_BUTTON_URL, menu.getUrl());
        } else if(WechatParams.MENU_TYPE_CLICK.equals(menu.getType())){ // 点击事件
            menuMap.put(WechatParams.MENU_BUTTON_TYPE, WechatParams.MENU_TYPE_CLICK);
            menuMap.put(WechatParams.MENU_BUTTON_NAME, menu.getName());
            menuMap.put(WechatParams.MENU_BUTTON_KEY, menu.getEventKey());
        } else { // 跳转小程序
            menuMap.put(WechatParams.MENU_BUTTON_TYPE, WechatParams.MENU_TYPE_MINIPROGRAM);
            menuMap.put(WechatParams.MENU_BUTTON_NAME, menu.getName());
            menuMap.put(WechatParams.MENU_BUTTON_URL, menu.getUrl());
            menuMap.put(WechatParams.MENU_BUTTON_APPID, menu.getAppId());
            menuMap.put(WechatParams.MENU_BUTTON_PAGEPATH, menu.getPagePath());
        }
        return menuMap;
    }
}