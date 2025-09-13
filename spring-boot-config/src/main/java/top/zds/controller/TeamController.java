package top.zds.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.zds.Team;

@RestController
public class TeamController {
//    新增团队
    @PostMapping("/team")
    public ResponseEntity<Team> addTeam(@Validated @RequestBody Team team){
        return ResponseEntity.ok(team);
    }
}