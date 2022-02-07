package com.bruce.demo.gateway.controller;

import com.bruce.demo.gateway.model.dto.CustomRouteDefinitionDTO;
import com.bruce.demo.gateway.route.RouteRefresher;
import com.bruce.demo.gateway.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Copyright Copyright © 2022 fanzh . All rights reserved.
 * @Desc
 * @ProjectName demo-server
 * @Date 2022/2/7 15:40
 * @Author fzh
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteRefresher routeRefresher;

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @GetMapping("/refresh")
    public void refresh() {
        routeRefresher.refresh();
    }

    @PostMapping("/save")
    public long save(@RequestBody CustomRouteDefinitionDTO dto) {
        long id = gatewayRouteService.save(dto);
        refresh();
        return id;
    }

    @PostMapping("/update")
    public void update(@RequestBody CustomRouteDefinitionDTO dto) {
        gatewayRouteService.update(dto);
        refresh();
    }

    @PostMapping("/remove/{id}")
    public void remove(@PathVariable String id) {
        gatewayRouteService.remove(id);
        refresh();
    }


    @GetMapping("/routes")
    public List<CustomRouteDefinitionDTO> routes() {
        return gatewayRouteService.queryAll();
    }

}
